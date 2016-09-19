/*
 * Copyright 2014 JIPOVI Solutions - ECUADOR 
 * Todos los derechos reservados
 */
package ec.com.uce.jano.web.backings;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import ec.com.uce.jano.entities.Catalogo;
import ec.com.uce.jano.entities.DetalleCatalogo;
import ec.com.uce.jano.entities.Gasto;
import ec.com.uce.jano.servicio.CatalogoService;
import ec.com.uce.jano.servicio.DetalleCatalogoService;
import ec.com.uce.jano.servicio.RecaudacionService;
import ec.com.uce.jano.web.beans.BuscarCompromisosBean;
import ec.com.uce.jano.web.util.ConstantesUtil;
import ec.com.uce.jano.web.util.GenerarPdfUtil;
import ec.com.uce.jano.web.util.HiperionMensajes;
import ec.com.uce.jano.web.util.JsfUtil;
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
public class BuscarCompromisosBacking implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{buscarCompromisosBean}")
	private BuscarCompromisosBean buscarCompromisosBean;

	@EJB
	private CatalogoService catalogoService;

	@EJB
	private DetalleCatalogoService detalleCatalogoService;

	@EJB
	private RecaudacionService recaudacionService;

	private List<SelectItem> periodoItems;

	private List<CompromisoDTO> compromisosDTO;
	private Long idGasto;
	private String comprobante;

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
	 * <b> Permite buscar los compromisos por los filtros ingresados. </b>
	 * <p>
	 * [Author: kruger, Date: 05/09/2016]
	 * </p>
	 * 
	 * @throws HiperionException
	 */
	public void buscarCompromisos() throws HiperionException {
		try {

			if (buscarCompromisosBean.getPeriodo() != null && buscarCompromisosBean.getBeneficiario() != "") {
				compromisosDTO = recaudacionService.buscarGastos(buscarCompromisosBean.getPeriodo(), buscarCompromisosBean.getBeneficiario(),
						buscarCompromisosBean.getFechaInicio(), buscarCompromisosBean.getFechaFin());
			} else {
				MessagesController.addWarn(null, "Se deben ingresar todos los parametros de busqueda.");
			}

		} catch (HiperionException e) {
			throw new HiperionException(e);
		}
	}

	/**
	 * 
	 * <b> Permite editar una partida. </b>
	 * <p>
	 * [Author: Paul Jimenez, Date: 24/03/2016]
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
	 * <b> Permite eliminar una partida. </b>
	 * <p>
	 * [Author: Paul Jimenez, Date: 24/03/2016]
	 * </p>
	 * 
	 * @param event
	 * @throws HiperionException
	 */
	public void eliminarCompromiso(RowEditEvent event) throws HiperionException {

		recaudacionService.eliminarCompromiso((CompromisoDTO) event.getObject());

		FacesMessage msg = new FacesMessage("Item Eliminado");
		FacesContext.getCurrentInstance().addMessage(null, msg);

		compromisosDTO.remove((CompromisoDTO) event.getObject());
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
	public void imprimirComprobante() throws HiperionException {

		Gasto gasto = recaudacionService.buscarGastoById(idGasto);
		CompromisoDTO compromisoDTO = new CompromisoDTO();
		compromisoDTO.setIdGasto(gasto.getIdGastos());
		compromisoDTO.setBeneficiario(gasto.getBeneficiarioGasto());
		compromisoDTO.setComprobante(gasto.getComprobanteGasto());

		// descargarCompromisoPDF(compromisoDTO);
	}

	/**
	 * 
	 * <b> Permite generar un documento en foramto PDF con los compromisos obtenidos. </b>
	 * <p>
	 * [Author: kruger, Date: 17/09/2016]
	 * </p>
	 * 
	 * @throws HiperionException
	 */
	public void imprimirComprobanteII() throws HiperionException {

		List<Gasto> gastos = recaudacionService.buscaGastosByComprobante(comprobante);

		CompromisoDTO compromisoDTO = new CompromisoDTO();
		compromisoDTO.setIdGasto(gastos.get(0).getIdGastos());
		compromisoDTO.setBeneficiario(gastos.get(0).getBeneficiarioGasto());
		compromisoDTO.setComprobante(gastos.get(0).getComprobanteGasto());
		descargarCompromisoPDF(compromisoDTO, gastos);
	}

	/**
	 * 
	 * <b> Permite generar y descargar el syllabus en formato PDF. </b>
	 * <p>
	 * [Author: Anita Carrera, Date: 01/03/2015]
	 * </p>
	 * 
	 * @throws DioneException
	 */
	public void descargarCompromisoPDF(CompromisoDTO compromisoDTO, List<Gasto> gastos) throws HiperionException {
		try {

			Map<String, Object> parametrosDocumento = new HashMap<String, Object>();

			parametrosDocumento.put(ConstantesUtil.CONTENT_TYPE_IDENTIFICADOR, ConstantesUtil.CONTENT_TYPE_PDF);
			parametrosDocumento.put(ConstantesUtil.NOMBRE_ARCHIVO_IDENTIFICADOR, "compromiso_" + compromisoDTO.getComprobante());

			parametrosDocumento.put(ConstantesUtil.CONTENIDO_BYTES_IDENTIFICADOR, GenerarPdfUtil.generarAchivoPDFCompromiso(compromisoDTO, gastos));

			JsfUtil.setSessionAttribute(ConstantesUtil.PARAMETROS_DESCARGADOR_IDENTIFICADOR, parametrosDocumento);

			JsfUtil.downloadFile();

		} catch (Exception e) {
			MessagesController.addError(null, "Error al momento de generar el compromiso PDF.");
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
	 * @return the buscarCompromisosBean
	 */
	public BuscarCompromisosBean getBuscarCompromisosBean() {
		return buscarCompromisosBean;
	}

	/**
	 * @param buscarCompromisosBean
	 *            the buscarCompromisosBean to set
	 */
	public void setBuscarCompromisosBean(BuscarCompromisosBean buscarCompromisosBean) {
		this.buscarCompromisosBean = buscarCompromisosBean;
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
	 * @return the comprobante
	 */
	public String getComprobante() {
		return comprobante;
	}

	/**
	 * @param comprobante
	 *            the comprobante to set
	 */
	public void setComprobante(String comprobante) {
		this.comprobante = comprobante;
	}

}
