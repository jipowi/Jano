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
import ec.com.uce.jano.dto.RecaudacionDTO;
import ec.com.uce.jano.entities.Afectacion;
import ec.com.uce.jano.servicio.AfectacionService;
import ec.com.uce.jano.web.beans.ReformaBean;

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

	private List<SelectItem> periodoItems;
	private List<SelectItem> facultadItems;
	private List<SelectItem> dependenciaItems;
	private List<SelectItem> departamentoItems;
	private List<SelectItem> partidasItems;
	private List<RecaudacionDTO> recaudacionDTOs = new ArrayList<>();

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

}
