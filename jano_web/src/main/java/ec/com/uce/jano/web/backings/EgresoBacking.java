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
import ec.com.uce.jano.dto.EgresoDTO;
import ec.com.uce.jano.entities.Catalogo;
import ec.com.uce.jano.entities.DetalleCatalogo;
import ec.com.uce.jano.entities.Egreso;
import ec.com.uce.jano.entities.Partida;
import ec.com.uce.jano.servicio.CatalogoService;
import ec.com.uce.jano.servicio.DetalleCatalogoService;
import ec.com.uce.jano.servicio.EgresoService;
import ec.com.uce.jano.web.beans.EgresoBean;
import ec.com.uce.jano.web.util.HiperionMensajes;
import ec.com.uce.jano.web.util.MessagesController;

/**
 * <b> Incluir aqui la descripcion de la clase. </b>
 * 
 * @author HIPERION
 * @version 1.0,23/02/2016
 * @since JDK1.6
 */
@ManagedBean
@ViewScoped
public class EgresoBacking implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{egresoBean}")
	private EgresoBean egresoBean;

	@EJB
	private CatalogoService catalogoService;
	@EJB
	private EgresoService egresoService;
	@EJB
	private DetalleCatalogoService detalleCatalogoService;

	private List<SelectItem> periodoItems;
	private List<SelectItem> facultadItems;
	private List<SelectItem> dependenciatems;
	private List<SelectItem> departamentoItems;
	private List<SelectItem> partidaItems;
	private List<EgresoDTO> egresosDTO = new ArrayList<>();
	private Partida partida;
	private Double presupuesto;
	private Long idPartida;

	/**
	 * @return the periodoItems
	 */
	public List<SelectItem> getPartidaItems() {
		try {
			this.partidaItems = new ArrayList<SelectItem>();

			List<Partida> partidas = egresoService.obtenerPartidasEgreso("Egreso");

			for (Partida partida : partidas) {
				SelectItem selectItem = new SelectItem(partida.getIdPartida(), partida.getPartida());
				partidaItems.add(selectItem);
			}

		} catch (HiperionException e) {
			e.printStackTrace();
		}
		return partidaItems;
	}

	/**
	 * @return the periodoItems
	 */
	public List<SelectItem> getPeriodoItems() {
		try {
			this.periodoItems = new ArrayList<SelectItem>();
			Catalogo catalogo = catalogoService.consultarCatalogoById(HiperionMensajes.getInstancia().getLong("ec.gob.jano.catalogo.periodo"));

			List<DetalleCatalogo> periodos = catalogo.getDetalleCatalogos();

			for (DetalleCatalogo detalle : periodos) {
				SelectItem selectItem = new SelectItem(detalle.getCodDetalleCatalogo(), detalle.getDescDetCatalogo());
				periodoItems.add(selectItem);
			}

		} catch (HiperionException e) {
			e.printStackTrace();
		}
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
		try {
			this.facultadItems = new ArrayList<SelectItem>();
			Catalogo catalogo = catalogoService.consultarCatalogoById(HiperionMensajes.getInstancia().getLong("ec.gob.jano.catalogo.facultad"));

			List<DetalleCatalogo> facultades = catalogo.getDetalleCatalogos();

			for (DetalleCatalogo detalle : facultades) {
				SelectItem selectItem = new SelectItem(detalle.getCodDetalleCatalogo(), detalle.getDescDetCatalogo());
				facultadItems.add(selectItem);
			}

		} catch (HiperionException e) {
			e.printStackTrace();
		}
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
	 * @return the dependenciatems
	 */
	public List<SelectItem> getDependenciatems() {
		try {
			this.dependenciatems = new ArrayList<SelectItem>();
			Catalogo catalogo = catalogoService.consultarCatalogoById(HiperionMensajes.getInstancia().getLong("ec.gob.jano.catalogo.dependencia"));

			List<DetalleCatalogo> dependencias = catalogo.getDetalleCatalogos();

			for (DetalleCatalogo detalle : dependencias) {
				SelectItem selectItem = new SelectItem(detalle.getCodDetalleCatalogo(), detalle.getDescDetCatalogo());
				dependenciatems.add(selectItem);
			}

		} catch (HiperionException e) {
			e.printStackTrace();
		}
		return dependenciatems;
	}

	/**
	 * @param dependenciatems
	 *            the dependenciatems to set
	 */
	public void setDependenciatems(List<SelectItem> dependenciatems) {
		this.dependenciatems = dependenciatems;
	}

	/**
	 * @return the departamentoItems
	 */
	public List<SelectItem> getDepartamentoItems() {
		try {
			this.departamentoItems = new ArrayList<SelectItem>();
			Catalogo catalogo = catalogoService.consultarCatalogoById(HiperionMensajes.getInstancia().getLong("ec.gob.jano.catalogo.departamento"));

			List<DetalleCatalogo> departamentos = catalogo.getDetalleCatalogos();

			for (DetalleCatalogo detalle : departamentos) {
				SelectItem selectItem = new SelectItem(detalle.getCodDetalleCatalogo(), detalle.getDescDetCatalogo());
				departamentoItems.add(selectItem);
			}

		} catch (HiperionException e) {
			e.printStackTrace();
		}
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
	 * 
	 * <b> Permite registrar un egreso. </b>
	 * <p>
	 * [Author: HIPERION, Date: 23/02/2016]
	 * </p>
	 * 
	 * @throws HiperionException
	 * 
	 */
	public void guardarEgreso() throws HiperionException {

		Egreso egreso = new Egreso();

		try {
			egreso.setPeriodo(egresoBean.getPeriodo());
			egreso.setFacultad(egresoBean.getFacultad());
			egreso.setDependencia(egresoBean.getDependencia());
			egreso.setDepartamento(egresoBean.getDepartamento());

			egresoService.guardarEgreso(egreso);
			MessagesController.addInfo(null, HiperionMensajes.getInstancia().getString("hiperion.mensaje.exito.save"));

		} catch (HiperionException e) {
			MessagesController.addError(null, HiperionMensajes.getInstancia().getString("hiperion.mensaje.error.save"));
			throw new HiperionException(e);
		}
	}

	/**
	 * @return the egresoBean
	 */
	public EgresoBean getEgresoBean() {
		return egresoBean;
	}

	/**
	 * @param egresoBean
	 *            the egresoBean to set
	 */
	public void setEgresoBean(EgresoBean egresoBean) {
		this.egresoBean = egresoBean;
	}

	/**
	 * 
	 * <b> Permite agregar un registro a la tabla. </b>
	 * <p>
	 * [Author: HIPERION, Date: 25/02/2016]
	 * </p>
	 * 
	 * @return
	 * @throws HiperionException
	 */
	public void addEgreso() throws HiperionException {

		try {
			this.partida = egresoService.obtenerPartidaById(idPartida);

			EgresoDTO egreso = new EgresoDTO(this.partida, this.presupuesto);
			egresosDTO.add(egreso);

			partida = new Partida();
			presupuesto = 0.0;

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
		FacesMessage msg = new FacesMessage("Item Edited", ((EgresoDTO) event.getObject()).getPartida().toString());
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
		egresosDTO.remove((EgresoDTO) event.getObject());
	}

	/**
	 * @return the egresosDTO
	 */
	public List<EgresoDTO> getEgresosDTO() {
		return egresosDTO;
	}

	/**
	 * @param egresosDTO
	 *            the egresosDTO to set
	 */
	public void setEgresosDTO(List<EgresoDTO> egresosDTO) {
		this.egresosDTO = egresosDTO;
	}

	/**
	 * @return the partida
	 */
	public Partida getPartida() {
		return partida;
	}

	/**
	 * @param partida
	 *            the partida to set
	 */
	public void setPartida(Partida partida) {
		this.partida = partida;
	}

	/**
	 * @return the presupuesto
	 */
	public Double getPresupuesto() {
		return presupuesto;
	}

	/**
	 * @param presupuesto
	 *            the presupuesto to set
	 */
	public void setPresupuesto(Double presupuesto) {
		this.presupuesto = presupuesto;
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
	 * @param partidaItems
	 *            the partidaItems to set
	 */
	public void setPartidaItems(List<SelectItem> partidaItems) {
		this.partidaItems = partidaItems;
	}

}
