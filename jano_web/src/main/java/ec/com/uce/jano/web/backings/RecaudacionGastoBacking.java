/*
 * Copyright 2014 JIPOVI Solutions - ECUADOR 
 * Todos los derechos reservados
 */
package ec.com.uce.jano.web.backings;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.primefaces.event.RowEditEvent;

import ec.com.uce.jano.comun.HiperionException;
import ec.com.uce.jano.dto.RecaudacionDTO;
import ec.com.uce.jano.entities.Afectacion;
import ec.com.uce.jano.entities.Catalogo;
import ec.com.uce.jano.entities.DetalleCatalogo;
import ec.com.uce.jano.entities.DetalleEgreso;
import ec.com.uce.jano.entities.Egreso;
import ec.com.uce.jano.entities.Gasto;
import ec.com.uce.jano.entities.Partida;
import ec.com.uce.jano.servicio.AfectacionService;
import ec.com.uce.jano.servicio.CatalogoService;
import ec.com.uce.jano.servicio.DetalleCatalogoService;
import ec.com.uce.jano.servicio.EgresoService;
import ec.com.uce.jano.servicio.RecaudacionService;
import ec.com.uce.jano.web.beans.RecaudacionGastoBean;
import ec.com.uce.jano.web.util.HiperionMensajes;
import ec.com.uce.jano.web.util.JanoUtil;
import ec.com.uce.jano.web.util.MessagesController;

/**
 * <b> Incluir aqui la descripcion de la clase. </b>
 * 
 * @author Paul Jimenez
 * @version 1.0,23/02/2016
 * @since JDK1.6
 */
@ManagedBean
@ViewScoped
public class RecaudacionGastoBacking implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{recaudacionGastoBean}")
	private RecaudacionGastoBean recaudacionGastoBean;

	@EJB
	private CatalogoService catalogoService;

	@EJB
	private DetalleCatalogoService detalleCatalogoService;

	@EJB
	private AfectacionService afectacionService;

	@EJB
	private RecaudacionService recaudacionService;

	@EJB
	private EgresoService egresoService;

	private List<SelectItem> periodoItems;
	private List<SelectItem> facultadItems;
	private List<SelectItem> dependenciaItems;
	private List<SelectItem> departamentoItems;
	private List<SelectItem> partidasItems;
	private List<RecaudacionDTO> recaudacionesDTO = new ArrayList<>();
	private Partida partida;
	private Long idPartida;
	Afectacion afectacion;

	@PostConstruct
	public void inicializar() throws HiperionException {
		try {

			obtenerFacultades();
			obtenerCodigoComprobante();

		} catch (HiperionException e) {
			throw new HiperionException(e);
		}
	}

	/**
	 * @return the periodoItems
	 * @throws HiperionException
	 */
	public List<SelectItem> getPeriodoItems() throws HiperionException {
		try {
			this.periodoItems = new ArrayList<SelectItem>();
			Catalogo catalogo = catalogoService.consultarCatalogoById(HiperionMensajes.getInstancia().getLong("ec.gob.jano.catalogo.periodo"));

			List<DetalleCatalogo> periodos = catalogo.getDetalleCatalogos();

			for (DetalleCatalogo detalle : periodos) {
				SelectItem selectItem = new SelectItem(detalle.getCodDetalleCatalogo(), detalle.getDescDetCatalogo());
				periodoItems.add(selectItem);
			}

		} catch (HiperionException e) {
			throw new HiperionException(e);
		}
		return periodoItems;
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

			List<Afectacion> dependencias = afectacionService.obtenerDependencias(recaudacionGastoBean.getFacultad());

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

			List<Afectacion> departamentos = afectacionService.obtenerDepartamentos(recaudacionGastoBean.getFacultad(),
					recaudacionGastoBean.getDependencia());

			for (Afectacion departamento : departamentos) {
				SelectItem selectItem = new SelectItem(departamento.getIdAfectacion(), departamento.getDescAfectacion());
				departamentoItems.add(selectItem);
			}

		} catch (HiperionException e) {
			throw new HiperionException(e);
		}
	}

	/**
	 * @param periodoItems
	 *            the periodoItems to set
	 */
	public void setPeriodoItems(List<SelectItem> periodoItems) {
		this.periodoItems = periodoItems;
	}

	/**
	 * 
	 * <b> Permite agregar un gasto. </b>
	 * <p>
	 * [Author: Paul Jimenez, Date: 16/07/2016]
	 * </p>
	 * 
	 * @throws HiperionException
	 */
	public void addGasto() throws HiperionException {

		try {
			this.partida = egresoService.obtenerPartidaById(idPartida);

			String beneficiario = recaudacionGastoBean.getBeneficiario();
			String comprobante = recaudacionGastoBean.getComprobante();
			Date fechaRecaudacion = recaudacionGastoBean.getFecha();
			String observacion = recaudacionGastoBean.getObservacion();
			String periodo = recaudacionGastoBean.getPeriodo();
			double valor = recaudacionGastoBean.getValor();

			if (valor > 0) {

				afectacion = new Afectacion();
				afectacion.setIdFacultad(recaudacionGastoBean.getFacultad());
				afectacion.setIdDependencia(recaudacionGastoBean.getDependencia());
				afectacion.setIdAfectacion(recaudacionGastoBean.getIdAfectacion());

				RecaudacionDTO recaudacionDTO = new RecaudacionDTO(beneficiario, comprobante, fechaRecaudacion, observacion, valor, afectacion,
						partida, periodo);

				Egreso egresoDB = egresoService.buscarEgresos(periodo, afectacion.getIdAfectacion());

				if (egresoDB != null) {
					List<DetalleEgreso> detEgresos = egresoService.buscarEgresos(egresoDB.getIdEgreso());

					for (DetalleEgreso detEgreso : detEgresos) {
						if (detEgreso.getPartida().getPartida().equals(this.partida.getPartida())) {
							if (valor > detEgreso.getPresupuesto()) {
								MessagesController.addWarn(null, "El valor ingresado sobrepasa el PRESUPUESTO!");
								recaudacionDTO.setEstado("I");
							} else {
								recaudacionDTO.setEstado("A");
							}
						}
					}
				}
				boolean validacion1 = true;

				if (!recaudacionesDTO.isEmpty()) {
					for (RecaudacionDTO recDTO : recaudacionesDTO) {
						if (recDTO.getPartida().getPartida().equals(partida.getPartida())) {
							validacion1 = false;
						}
					}
				}

				if (validacion1) {
					recaudacionesDTO.add(recaudacionDTO);
					recaudacionGastoBean.setValor(0.0);
				} else {
					MessagesController.addWarn(null, "Ya existe ingresada una partida similar ");
				}

			} else {
				MessagesController.addWarn(null, "El valor debe ser mayor que cero. ");
			}

		} catch (HiperionException e) {
			MessagesController.addError(null, HiperionMensajes.getInstancia().getString("hiperion.mensaje.error.save"));
			throw new HiperionException(e);
		}
	}

	/**
	 * 
	 * <b> Permite editar un registro. </b>
	 * <p>
	 * [Author: HIPERION, Date: 25/02/2016]
	 * </p>
	 * 
	 * @param event
	 */
	public void onEdit(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Item Edited", ((RecaudacionDTO) event.getObject()).getPartida().toString());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	/**
	 * 
	 * <b> permite eliminar un registro de la tabla</b>
	 * <p>
	 * [Author: Paul Jimenez, Date: Mar 3, 2014]
	 * </p>
	 * 
	 * @param event
	 */
	public void onCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Item Cancelled");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		recaudacionesDTO.remove((RecaudacionDTO) event.getObject());
	}

	/**
	 * 
	 * <b> Permite regsitrar las recaudaciones ingresadas en la base de datos. </b>
	 * <p>
	 * [Author: kruger, Date: 17/07/2016]
	 * </p>
	 * 
	 * @throws HiperionException
	 */
	public void guardarRecaudacion() throws HiperionException {

		try {
			if (recaudacionesDTO.isEmpty()) {

				MessagesController.addWarn(null, HiperionMensajes.getInstancia().getString("hiperion.mensaje.war.detCertifica"));

			} else {
				for (RecaudacionDTO recaudacionDTO : recaudacionesDTO) {
					Gasto gasto = new Gasto();

					gasto.setCodigoGasto(recaudacionGastoBean.getComprobante());

					gasto.setComprobanteGasto(recaudacionGastoBean.getComprobante());
					gasto.setFechaGasto(recaudacionDTO.getFechaRecaudacion());
					gasto.setBeneficiarioGasto(recaudacionDTO.getBeneficiario());
					gasto.setObsGasto(recaudacionDTO.getObservacion());
					gasto.setValorGasto(recaudacionDTO.getValorRecaudacion());
					gasto.setCur(recaudacionGastoBean.getCur());
					gasto.setPeriodoGasto(recaudacionGastoBean.getPeriodo());
					gasto.setEstadoGasto(recaudacionDTO.getEstado());

					afectacion = new Afectacion();
					afectacion.setIdFacultad(recaudacionGastoBean.getFacultad());
					afectacion.setIdDependencia(recaudacionGastoBean.getDependencia());
					afectacion.setIdAfectacion(recaudacionGastoBean.getIdAfectacion());

					gasto.setAfectacion(afectacion);

					gasto.setPartida(recaudacionDTO.getPartida());

					recaudacionService.guardarGastos(gasto);
				}

				MessagesController.addInfo(null, HiperionMensajes.getInstancia().getString("hiperion.mensaje.exito.save"));
				recaudacionGastoBean.setFacultad(null);
				recaudacionGastoBean.setDependencia(null);
				recaudacionGastoBean.setIdAfectacion(null);
				recaudacionGastoBean.setPeriodo(null);
				recaudacionGastoBean.setComprobante(null);
				recaudacionGastoBean.setFecha(null);
				recaudacionGastoBean.setBeneficiario(null);
				recaudacionGastoBean.setCur(null);
				recaudacionGastoBean.setObservacion(null);

			}
			recaudacionesDTO.clear();

		} catch (HiperionException e) {
			MessagesController.addError(null, HiperionMensajes.getInstancia().getString("hiperion.mensaje.error.save"));
			throw new HiperionException(e);
		}
	}

	/**
	 * 
	 * <b> Permite obtener un codigo secuencial para el comprobante. </b>
	 * <p>
	 * [Author: kruger, Date: 21/06/2016]
	 * </p>
	 * 
	 */
	public void obtenerCodigoComprobante() {

		List<Gasto> gastos;
		String codigo = null;

		try {
			gastos = recaudacionService.obtenerGastos();

			JanoUtil.getInstancia();

			codigo = JanoUtil.obtenerCodigoSecuencial(gastos.size() + 1);

		} catch (HiperionException e) {

			e.printStackTrace();
		}
		recaudacionGastoBean.setComprobante(codigo);
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
	 * @return the partidasItems
	 * @throws HiperionException
	 */
	public List<SelectItem> getPartidasItems() throws HiperionException {

		try {

			this.partidasItems = new ArrayList<SelectItem>();

			List<Partida> partidas;

			partidas = egresoService.obtenerPartidas("Egreso");

			for (Partida partida : partidas) {
				SelectItem selectItem = new SelectItem(partida.getIdPartida(), partida.getPartida());
				partidasItems.add(selectItem);
			}
		} catch (HiperionException e) {
			throw new HiperionException(e);
		}
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
	 * @return the idPartida
	 */
	public Long getIdPartida() {
		return idPartida;
	}

	/**
	 * @param idPartida
	 *            the idPartida to set
	 */
	public void setIdPartida(Long idPartida) {
		this.idPartida = idPartida;
	}

	/**
	 * @return the recaudacionGastoBean
	 */
	public RecaudacionGastoBean getRecaudacionGastoBean() {
		return recaudacionGastoBean;
	}

	/**
	 * @param recaudacionGastoBean
	 *            the recaudacionGastoBean to set
	 */
	public void setRecaudacionGastoBean(RecaudacionGastoBean recaudacionGastoBean) {
		this.recaudacionGastoBean = recaudacionGastoBean;
	}

	/**
	 * @return the recaudacionesDTO
	 */
	public List<RecaudacionDTO> getRecaudacionesDTO() {
		return recaudacionesDTO;
	}

	/**
	 * @param recaudacionesDTO
	 *            the recaudacionesDTO to set
	 */
	public void setRecaudacionesDTO(List<RecaudacionDTO> recaudacionesDTO) {
		this.recaudacionesDTO = recaudacionesDTO;
	}

}
