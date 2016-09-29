/*
 * Copyright 2014 JIPOVI Solutions - ECUADOR 
 * Todos los derechos reservados
 */
package ec.com.uce.jano.web.backings;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.primefaces.event.RowEditEvent;

import ec.com.uce.jano.comun.HiperionException;
import ec.com.uce.jano.dto.CompromisoDTO;
import ec.com.uce.jano.dto.RecaudacionDTO;
import ec.com.uce.jano.entities.Afectacion;
import ec.com.uce.jano.entities.Gasto;
import ec.com.uce.jano.servicio.AfectacionService;
import ec.com.uce.jano.servicio.RecaudacionService;
import ec.com.uce.jano.web.beans.ReformaBean;
import ec.com.uce.jano.web.util.MessagesController;

/**
 * <b> Incluir aqui la descripcion de la clase. </b>
 * 
 * @author kruger
 * @version 1.0,01/08/2016
 * @since JDK1.6
 */
@ManagedBean
@ViewScoped
public class ReformaBacking implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{reformaBean}")
	private ReformaBean reformaBean;

	@EJB
	private AfectacionService afectacionService;

	@EJB
	private RecaudacionService recaudacionService;

	private List<SelectItem> periodoItems;
	private List<SelectItem> facultadItems;
	private List<SelectItem> dependenciaItems;
	private List<SelectItem> departamentoItems;
	private List<SelectItem> partidasItems;
	private List<RecaudacionDTO> recaudacionDTOs = new ArrayList<>();
	private List<CompromisoDTO> compromisosDTO;
	private Long idGasto;
	private Gasto gastoObtenido;

	/**
	 * 
	 * <b> Permite buscar los compromisos por los filtros ingresados. </b>
	 * <p>
	 * [Author: kruger, Date: 05/09/2016]
	 * </p>
	 * 
	 * @throws HiperionException
	 */
	public void buscarCompromisos() throws HiperionException {

		try {

			if (!reformaBean.getEstado().equals("")) {
				compromisosDTO = recaudacionService.buscarGastosByEstado(reformaBean.getEstado());
				if (reformaBean.getEstado().equals("T")) {
					List<Gasto> gastos = recaudacionService.obtenerGastos();

					for (Gasto gasto : gastos) {
						CompromisoDTO compromisoDTO = new CompromisoDTO();

						compromisoDTO.setIdGasto(gasto.getIdGastos());
						compromisoDTO.setBeneficiario(gasto.getBeneficiarioGasto());
						compromisoDTO.setEstado(gasto.getEstadoGasto());
						compromisoDTO.setPeriodo(gasto.getPeriodoGasto());
						compromisoDTO.setValor(gasto.getValorGasto());
						compromisoDTO.setFecha(gasto.getFechaGasto());
						compromisoDTO.setComprobante(gasto.getComprobanteGasto());
						compromisoDTO.setCur(gasto.getCur());

						compromisosDTO.add(compromisoDTO);
					}

				}
			} else {
				MessagesController.addWarn(null, "Se deben ingresar todos los parametros de busqueda.");
			}

		} catch (HiperionException e) {
			throw new HiperionException(e);
		}
	}

	/**
	 * 
	 * <b> Permite obtener las facultades registradas y transformar a una lista de seleccion. </b>
	 * <p>
	 * [Author: Paul Jimenez, Date: 31/03/2016]
	 * </p>
	 * 
	 * @throws HiperionException
	 */
	public void obtenerFacultades() throws HiperionException {

		try {
			this.facultadItems = new ArrayList<SelectItem>();

			List<Afectacion> facultades = afectacionService.obtenerFacultades();

			for (Afectacion facultad : facultades) {
				SelectItem selectItem = new SelectItem(facultad.getIdAfectacion(), facultad.getDescAfectacion());
				facultadItems.add(selectItem);
			}

		} catch (HiperionException e) {
			throw new HiperionException(e);
		}
	}

	/**
	 * 
	 * <b> Permite obtener las dependencias registradas y transformar a una lista de seleccion. </b>
	 * <p>
	 * [Author: Paul Jimenez, Date: 31/03/2016]
	 * </p>
	 * 
	 * @throws HiperionException
	 */
	public void obtenerDependencias() throws HiperionException {

		try {
			this.dependenciaItems = new ArrayList<SelectItem>();

			List<Afectacion> dependencias = afectacionService.obtenerDependencias(reformaBean.getFacultad());

			for (Afectacion dependencia : dependencias) {
				SelectItem selectItem = new SelectItem(dependencia.getIdAfectacion(), dependencia.getDescAfectacion());
				dependenciaItems.add(selectItem);
			}

		} catch (HiperionException e) {
			throw new HiperionException(e);
		}
	}

	/**
	 * 
	 * <b> Permite obtener los departamentos registradas y transformar a una lista de seleccion. </b>
	 * <p>
	 * [Author: Paul Jimenez, Date: 31/03/2016]
	 * </p>
	 * 
	 * @throws HiperionException
	 */
	public void obtenerDepartamentos() throws HiperionException {

		try {
			this.departamentoItems = new ArrayList<SelectItem>();

			List<Afectacion> departamentos = afectacionService.obtenerDepartamentos(reformaBean.getFacultad(), reformaBean.getDependencia());

			for (Afectacion departamento : departamentos) {
				SelectItem selectItem = new SelectItem(departamento.getIdAfectacion(), departamento.getDescAfectacion());
				departamentoItems.add(selectItem);
			}

		} catch (HiperionException e) {
			throw new HiperionException(e);
		}
	}

	/**
	 * 
	 * <b> Permite editar un registro de la base de datos. </b>
	 * <p>
	 * [Author: kruger, Date: 19/09/2016]
	 * </p>
	 * 
	 * @param event
	 * @throws HiperionException
	 */
	public void editarCompromiso(RowEditEvent event) throws HiperionException {
		FacesMessage msg = new FacesMessage("Item Editado", ((CompromisoDTO) event.getObject()).getComprobante());
		recaudacionService.editarCompromiso((CompromisoDTO) event.getObject());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	/**
	 * 
	 * <b> Permite imprimir el compromiso que busca mediente el codigo de gasto. </b>
	 * <p>
	 * [Author: kruger, Date: 16/09/2016]
	 * </p>
	 * 
	 * @throws HiperionException
	 */
	public void reformarCompromiso() throws HiperionException {

		gastoObtenido = recaudacionService.buscarGastoById(idGasto);
		gastoObtenido.getAfectacion();
		

	}

	/**
	 * @return the reformaBean
	 */
	public ReformaBean getReformaBean() {
		return reformaBean;
	}

	/**
	 * @param reformaBean
	 *            the reformaBean to set
	 */
	public void setReformaBean(ReformaBean reformaBean) {
		this.reformaBean = reformaBean;
	}

	/**
	 * @return the periodoItems
	 */
	public List<SelectItem> getPeriodoItems() {
		return periodoItems;
	}

	/**
	 * @param periodoItems
	 *            the periodoItems to set
	 */
	public void setPeriodoItems(List<SelectItem> periodoItems) {
		this.periodoItems = periodoItems;
	}

	/**
	 * @return the facultadItems
	 */
	public List<SelectItem> getFacultadItems() {
		return facultadItems;
	}

	/**
	 * @param facultadItems
	 *            the facultadItems to set
	 */
	public void setFacultadItems(List<SelectItem> facultadItems) {
		this.facultadItems = facultadItems;
	}

	/**
	 * @return the dependenciaItems
	 */
	public List<SelectItem> getDependenciaItems() {
		return dependenciaItems;
	}

	/**
	 * @param dependenciaItems
	 *            the dependenciaItems to set
	 */
	public void setDependenciaItems(List<SelectItem> dependenciaItems) {
		this.dependenciaItems = dependenciaItems;
	}

	/**
	 * @return the departamentoItems
	 */
	public List<SelectItem> getDepartamentoItems() {
		return departamentoItems;
	}

	/**
	 * @param departamentoItems
	 *            the departamentoItems to set
	 */
	public void setDepartamentoItems(List<SelectItem> departamentoItems) {
		this.departamentoItems = departamentoItems;
	}

	/**
	 * @return the partidasItems
	 */
	public List<SelectItem> getPartidasItems() {
		return partidasItems;
	}

	/**
	 * @param partidasItems
	 *            the partidasItems to set
	 */
	public void setPartidasItems(List<SelectItem> partidasItems) {
		this.partidasItems = partidasItems;
	}

	/**
	 * @return the recaudacionDTOs
	 */
	public List<RecaudacionDTO> getRecaudacionDTOs() {
		return recaudacionDTOs;
	}

	/**
	 * @param recaudacionDTOs
	 *            the recaudacionDTOs to set
	 */
	public void setRecaudacionDTOs(List<RecaudacionDTO> recaudacionDTOs) {
		this.recaudacionDTOs = recaudacionDTOs;
	}

	/**
	 * @return the compromisosDTO
	 */
	public List<CompromisoDTO> getCompromisosDTO() {
		return compromisosDTO;
	}

	/**
	 * @param compromisosDTO
	 *            the compromisosDTO to set
	 */
	public void setCompromisosDTO(List<CompromisoDTO> compromisosDTO) {
		this.compromisosDTO = compromisosDTO;
	}

	/**
	 * @return the idGasto
	 */
	public Long getIdGasto() {
		return idGasto;
	}

	/**
	 * @param idGasto
	 *            the idGasto to set
	 */
	public void setIdGasto(Long idGasto) {
		this.idGasto = idGasto;
	}

	/**
	 * @return the gastoObtenido
	 */
	public Gasto getGastoObtenido() {
		return gastoObtenido;
	}

	/**
	 * @param gastoObtenido
	 *            the gastoObtenido to set
	 */
	public void setGastoObtenido(Gasto gastoObtenido) {
		this.gastoObtenido = gastoObtenido;
	}

}
