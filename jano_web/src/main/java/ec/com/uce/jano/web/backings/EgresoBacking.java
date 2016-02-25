/*
 * Copyright 2014 JIPOVI Solutions - ECUADOR 
 * Todos los derechos reservados
 */
package ec.com.uce.jano.web.backings;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import ec.com.uce.jano.comun.HiperionException;
import ec.com.uce.jano.entities.Catalogo;
import ec.com.uce.jano.entities.DetalleCatalogo;
import ec.com.uce.jano.entities.Egreso;
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
	 */
	public void guardarEgreso() {

		Egreso egreso = new Egreso();

		try {
			egreso.setPeriodo(egresoBean.getPeriodo());
			egreso.setFacultad(egresoBean.getFacultad());
			egreso.setDependencia(egresoBean.getDependencia());
			egreso.setDepartamento(egreso.getDepartamento());

			egresoService.guardarEgreso(egreso);
			MessagesController.addInfo(null, HiperionMensajes.getInstancia().getString("hiperion.mensaje.exito.save"));

		} catch (HiperionException e) {
			e.printStackTrace();
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

}
