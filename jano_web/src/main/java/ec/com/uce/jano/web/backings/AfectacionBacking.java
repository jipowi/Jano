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
import ec.com.uce.jano.servicio.AfectacionService;
import ec.com.uce.jano.servicio.CatalogoService;
import ec.com.uce.jano.servicio.DetalleCatalogoService;
import ec.com.uce.jano.web.beans.AfectacionBean;
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
public class AfectacionBacking implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{afectacionBean}")
	private AfectacionBean afectacionBean;

	@EJB
	private CatalogoService catalogoService;
	@EJB
	private AfectacionService afectacionService;

	@EJB
	private DetalleCatalogoService detalleCatalogoService;

	private List<SelectItem> periodoItems;
	private List<SelectItem> facultadItems;
	private List<SelectItem> dependenciaItems;
	private List<SelectItem> departamentoItems;
	private String tipoAfectacion;

	private boolean activarFacultad = false;
	private boolean activarDependencia = false;
	private boolean activarDepartamento = false;

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
	 * @throws HiperionException
	 * 
	 */
	public void seleccionarAfectacion() throws HiperionException {
		if (tipoAfectacion.equals("Facultad")) {
			activarFacultad = true;
			activarDependencia = false;
			activarDepartamento = false;

		} else if (tipoAfectacion.equals("Dependencia")) {
			activarDependencia = true;
			activarFacultad = false;
			activarDepartamento = false;
			obtenerFacultades();
		} else if (tipoAfectacion.equals("Departamento")) {
			activarDepartamento = true;
			activarDependencia = false;
			activarFacultad = false;
			obtenerFacultades();
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

			List<Afectacion> dependencias = afectacionService.obtenerDependencias(afectacionBean.getFacultad());

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
	 * <b> Permite registrar una afectación en la base de datos. </b>
	 * <p>
	 * [Author: Paul Jimenez, Date: 31/03/2016]
	 * </p>
	 * 
	 * @throws HiperionException
	 * 
	 */
	public void guardarAfectacion() throws HiperionException {

		try {

			if (tipoAfectacion.equals("Facultad")) {

				Afectacion afectacion = new Afectacion();

				if (!afectacionBean.getAfectacion().equals("")) {

					afectacion.setDescAfectacion(afectacionBean.getAfectacion());
					afectacion.setIdDependencia(null);

					afectacionService.guardarAfectacion(afectacion);
					MessagesController.addInfo(null, HiperionMensajes.getInstancia().getString("hiperion.mensaje.exito.save"));
					afectacionBean.setAfectacion("");
				} else {
					MessagesController.addWarn(null, "El nombre de la afectacion es requerido.");
				}

			} else if (tipoAfectacion.equals("Dependencia")) {

				if (afectacionBean.getAfectacion().equals("") || afectacionBean.getFacultad().equals("")) {
					MessagesController.addWarn(null, "Todos los campos son requeridos.");
				} else {
					Afectacion afectacion = new Afectacion();

					afectacion.setDescAfectacion(afectacionBean.getAfectacion());
					afectacion.setIdFacultad(afectacionBean.getFacultad());

					afectacionService.guardarAfectacion(afectacion);
					MessagesController.addInfo(null, HiperionMensajes.getInstancia().getString("hiperion.mensaje.exito.save"));
					afectacionBean.setAfectacion("");
				}

			} else if (tipoAfectacion.equals("Departamento")) {

				if (afectacionBean.getAfectacion().equals("") || afectacionBean.getFacultad().equals("")
						|| afectacionBean.getDependencia().equals("")) {
					MessagesController.addWarn(null, "Todos los campos son requeridos.");
				} else {
					Afectacion afectacion = new Afectacion();

					afectacion.setDescAfectacion(afectacionBean.getAfectacion());
					afectacion.setIdFacultad(afectacionBean.getFacultad());
					afectacion.setIdDependencia(afectacionBean.getDependencia());

					afectacionService.guardarAfectacion(afectacion);
					MessagesController.addInfo(null, HiperionMensajes.getInstancia().getString("hiperion.mensaje.exito.save"));
					afectacionBean.setAfectacion("");
				}
			}
		} catch (HiperionException e) {

			MessagesController.addError(null, HiperionMensajes.getInstancia().getString("hiperion.mensaje.error.save"));
			throw new HiperionException(e);
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

}
