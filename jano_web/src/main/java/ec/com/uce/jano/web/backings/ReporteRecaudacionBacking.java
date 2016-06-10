/*
 * Copyright 2014 JIPOVI Solutions - ECUADOR 
 * Todos los derechos reservados
 */
package ec.com.uce.jano.web.backings;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
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
import ec.com.uce.jano.servicio.AfectacionService;
import ec.com.uce.jano.servicio.CatalogoService;
import ec.com.uce.jano.servicio.DetalleCatalogoService;
import ec.com.uce.jano.servicio.EgresoService;
import ec.com.uce.jano.servicio.RecaudacionService;
import ec.com.uce.jano.web.beans.ReporteRecaudacionBean;
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
public class ReporteRecaudacionBacking implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{reporteRecaudacionBean}")
	private ReporteRecaudacionBean reporteRecaudacionBean;

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

	private Long idPartida;

	@PostConstruct
	public void inicializar() throws HiperionException {
		try {

			obtenerFacultades();

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

			List<Afectacion> dependencias = afectacionService.obtenerDependencias(reporteRecaudacionBean.getFacultad());

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

			List<Afectacion> departamentos = afectacionService.obtenerDepartamentos(reporteRecaudacionBean.getFacultad(),
					reporteRecaudacionBean.getDependencia());

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
	 * <b> permite buscar las recaudaciones que fueron ingresadas. </b>
	 * <p>
	 * [Author: kruger, Date: 09/06/2016]
	 * </p>
	 * 
	 */
	public void generarReporte() {

	}

	/**
	 * @param periodoItems
	 *            the periodoItems to set
	 */
	public void setPeriodoItems(List<SelectItem> periodoItems) {
		this.periodoItems = periodoItems;
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
	 * @return the reporteRecaudacionBean
	 */
	public ReporteRecaudacionBean getReporteRecaudacionBean() {
		return reporteRecaudacionBean;
	}

	/**
	 * @param reporteRecaudacionBean
	 *            the reporteRecaudacionBean to set
	 */
	public void setReporteRecaudacionBean(ReporteRecaudacionBean reporteRecaudacionBean) {
		this.reporteRecaudacionBean = reporteRecaudacionBean;
	}

	/**
	 * @return the partidasItems
	 * @throws HiperionException
	 */
	public List<SelectItem> getPartidasItems() throws HiperionException {

		try {

			this.partidasItems = new ArrayList<SelectItem>();

			List<Partida> partidas;

			partidas = egresoService.obtenerPartidas("Ingreso");

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

}
