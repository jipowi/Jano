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
import ec.com.uce.jano.dto.RecaudacionDTO;
import ec.com.uce.jano.entities.Catalogo;
import ec.com.uce.jano.entities.DetalleCatalogo;
import ec.com.uce.jano.servicio.CatalogoService;
import ec.com.uce.jano.servicio.DetalleCatalogoService;
import ec.com.uce.jano.servicio.RecaudacionService;
import ec.com.uce.jano.web.beans.BuscarRecaudacionesBean;
import ec.com.uce.jano.web.util.HiperionMensajes;
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
public class BuscarRecaudacionesBacking implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{buscarRecaudacionesBean}")
	private BuscarRecaudacionesBean buscarRecaudacionesBean;

	@EJB
	private CatalogoService catalogoService;

	@EJB
	private DetalleCatalogoService detalleCatalogoService;

	@EJB
	private RecaudacionService recaudacionService;

	private List<SelectItem> periodoItems;

	private List<RecaudacionDTO> recaudacionDTOs;

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

			if (buscarRecaudacionesBean.getPeriodo() != null && buscarRecaudacionesBean.getBeneficiario() != "") {
				recaudacionDTOs = recaudacionService.buscarRecaudaciones(buscarRecaudacionesBean.getPeriodo(),
						buscarRecaudacionesBean.getFechaInicio(), buscarRecaudacionesBean.getFechaFin());
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
	public void editarRecaudacion(RowEditEvent event) throws HiperionException {
		FacesMessage msg = new FacesMessage("Item Editado", ((RecaudacionDTO) event.getObject()).getComprobante());
		recaudacionService.editarRecaudacion((RecaudacionDTO) event.getObject());
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
	public void eliminarRecaudacion(RowEditEvent event) throws HiperionException {

		recaudacionService.eliminarRecaudacion((RecaudacionDTO) event.getObject());

		FacesMessage msg = new FacesMessage("Item Eliminado");
		FacesContext.getCurrentInstance().addMessage(null, msg);

		recaudacionDTOs.remove((RecaudacionDTO) event.getObject());
	}
	
	
	/**
	 * @param periodoItems
	 *            the periodoItems to set
	 */
	public void setPeriodoItems(List<SelectItem> periodoItems) {
		this.periodoItems = periodoItems;
	}

	/**
	 * @return the buscarRecaudacionesBean
	 */
	public BuscarRecaudacionesBean getBuscarRecaudacionesBean() {
		return buscarRecaudacionesBean;
	}

	/**
	 * @param buscarRecaudacionesBean
	 *            the buscarRecaudacionesBean to set
	 */
	public void setBuscarRecaudacionesBean(BuscarRecaudacionesBean buscarRecaudacionesBean) {
		this.buscarRecaudacionesBean = buscarRecaudacionesBean;
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

}
