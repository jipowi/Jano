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
import ec.com.uce.jano.entities.Afectacion;
import ec.com.uce.jano.entities.Catalogo;
import ec.com.uce.jano.entities.DetalleCatalogo;
import ec.com.uce.jano.entities.Partida;
import ec.com.uce.jano.servicio.CatalogoService;
import ec.com.uce.jano.servicio.DetalleCatalogoService;
import ec.com.uce.jano.servicio.EgresoService;
import ec.com.uce.jano.web.beans.AfectacionBean;
import ec.com.uce.jano.web.util.HiperionMensajes;

/**
 * <b> Incluir aqui la descripcion de la clase. </b>
 * 
 * @author HIPERION
 * @version 1.0,23/02/2016
 * @since JDK1.6
 */
@ManagedBean
@ViewScoped
public class AfectacionBacking implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{afectacionBean}")
	private AfectacionBean afectacionBean;

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
	private String tipoAfectacion;

	private boolean activarFacultad = false;
	private boolean activarDependencia = false;
	private boolean activarDepartamento = false;

	/**
	 * @return the periodoItems
	 * @throws HiperionException
	 */
	public List<SelectItem> getPartidaItems() throws HiperionException {
		try {
			this.partidaItems = new ArrayList<SelectItem>();

			List<Partida> partidas = egresoService.obtenerPartidas("Egreso");

			for (Partida partida : partidas) {
				SelectItem selectItem = new SelectItem(partida.getIdPartida(), partida.getPartida());
				partidaItems.add(selectItem);
			}

		} catch (HiperionException e) {
			throw new HiperionException(e);
		}
		return partidaItems;
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
	 * <b> Permite activar los distintos paneles dependinedo el tipo de dependencia. </b>
	 * <p>
	 * [Author: Paul Jimenez, Date: 31/03/2016]
	 * </p>
	 * 
	 */
	public void seleccionarAfectacion() {
		if (tipoAfectacion.equals("Facultad")) {
			activarFacultad = true;
			activarDependencia = false;
			activarDepartamento = false;
		} else if (tipoAfectacion.equals("Dependencia")) {
			activarDependencia = true;
			activarFacultad = false;
			activarDepartamento = false;
		} else if (tipoAfectacion.equals("Departamento")) {
			activarDepartamento = true;
			activarDependencia = false;
			activarFacultad = false;
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
	 * @return the facultadItems
	 * @throws HiperionException
	 */
	public List<SelectItem> getFacultadItems() throws HiperionException {
		try {
			this.facultadItems = new ArrayList<SelectItem>();
			Catalogo catalogo = catalogoService.consultarCatalogoById(HiperionMensajes.getInstancia().getLong("ec.gob.jano.catalogo.facultad"));

			List<DetalleCatalogo> facultades = catalogo.getDetalleCatalogos();

			for (DetalleCatalogo detalle : facultades) {
				SelectItem selectItem = new SelectItem(detalle.getCodDetalleCatalogo(), detalle.getDescDetCatalogo());
				facultadItems.add(selectItem);
			}

		} catch (HiperionException e) {
			throw new HiperionException(e);
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
	 * @throws HiperionException
	 */
	public List<SelectItem> getDependenciatems() throws HiperionException {
		try {
			this.dependenciatems = new ArrayList<SelectItem>();
			Catalogo catalogo = catalogoService.consultarCatalogoById(HiperionMensajes.getInstancia().getLong("ec.gob.jano.catalogo.dependencia"));

			List<DetalleCatalogo> dependencias = catalogo.getDetalleCatalogos();

			for (DetalleCatalogo detalle : dependencias) {
				SelectItem selectItem = new SelectItem(detalle.getCodDetalleCatalogo(), detalle.getDescDetCatalogo());
				dependenciatems.add(selectItem);
			}

		} catch (HiperionException e) {
			throw new HiperionException(e);
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
	 * @throws HiperionException
	 */
	public List<SelectItem> getDepartamentoItems() throws HiperionException {
		try {
			this.departamentoItems = new ArrayList<SelectItem>();
			Catalogo catalogo = catalogoService.consultarCatalogoById(HiperionMensajes.getInstancia().getLong("ec.gob.jano.catalogo.departamento"));

			List<DetalleCatalogo> departamentos = catalogo.getDetalleCatalogos();

			for (DetalleCatalogo detalle : departamentos) {
				SelectItem selectItem = new SelectItem(detalle.getCodDetalleCatalogo(), detalle.getDescDetCatalogo());
				departamentoItems.add(selectItem);
			}

		} catch (HiperionException e) {
			throw new HiperionException(e);
		}
		return departamentoItems;
	}

	/**
	 * 
	 * <b> Permite registrar una afectación en la base de datos. </b>
	 * <p>
	 * [Author: Paul Jimenez, Date: 31/03/2016]
	 * </p>
	 * 
	 */
	public void guardarAfectacion() {
		Afectacion afectacion = new Afectacion();
		
		if (tipoAfectacion.equals("Facultad")) {
			afectacion.setDescAfectacion(descAfectacion)
		} else if (tipoAfectacion.equals("Dependencia")) {
			activarDependencia = true;
			activarFacultad = false;
			activarDepartamento = false;
		} else if (tipoAfectacion.equals("Departamento")) {
			activarDepartamento = true;
			activarDependencia = false;
			activarFacultad = false;
		}
	}

	/**
	 * @param departamentoItems
	 *            the departamentoItems to set
	 */
	public void setDepartamentoItems(List<SelectItem> departamentoItems) {
		this.departamentoItems = departamentoItems;
	}

	/**
	 * @param partidaItems
	 *            the partidaItems to set
	 */
	public void setPartidaItems(List<SelectItem> partidaItems) {
		this.partidaItems = partidaItems;
	}

	/**
	 * @return the tipoAfectacion
	 */
	public String getTipoAfectacion() {
		return tipoAfectacion;
	}

	/**
	 * @param tipoAfectacion
	 *            the tipoAfectacion to set
	 */
	public void setTipoAfectacion(String tipoAfectacion) {
		this.tipoAfectacion = tipoAfectacion;
	}

	/**
	 * @return the afectacionBean
	 */
	public AfectacionBean getAfectacionBean() {
		return afectacionBean;
	}

	/**
	 * @param afectacionBean
	 *            the afectacionBean to set
	 */
	public void setAfectacionBean(AfectacionBean afectacionBean) {
		this.afectacionBean = afectacionBean;
	}

	/**
	 * @return the activarFacultad
	 */
	public boolean isActivarFacultad() {
		return activarFacultad;
	}

	/**
	 * @param activarFacultad
	 *            the activarFacultad to set
	 */
	public void setActivarFacultad(boolean activarFacultad) {
		this.activarFacultad = activarFacultad;
	}

	/**
	 * @return the activarDependencia
	 */
	public boolean isActivarDependencia() {
		return activarDependencia;
	}

	/**
	 * @param activarDependencia
	 *            the activarDependencia to set
	 */
	public void setActivarDependencia(boolean activarDependencia) {
		this.activarDependencia = activarDependencia;
	}

	/**
	 * @return the activarDepartamento
	 */
	public boolean isActivarDepartamento() {
		return activarDepartamento;
	}

	/**
	 * @param activarDepartamento
	 *            the activarDepartamento to set
	 */
	public void setActivarDepartamento(boolean activarDepartamento) {
		this.activarDepartamento = activarDepartamento;
	}

}
