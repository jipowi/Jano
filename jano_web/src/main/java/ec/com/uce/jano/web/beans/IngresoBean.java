/*
 * Copyright 2014 JIPOVI Solutions - ECUADOR 
 * Todos los derechos reservados
 */
package ec.com.uce.jano.web.beans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * <b> Incluir aqui la descripcion de la clase. </b>
 * 
 * @author HIPERION
 * @version 1.0,23/02/2016
 * @since JDK1.6
 */
@ManagedBean
@ViewScoped
public class IngresoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private String periodo;
	private Integer facultad;
	private Integer dependencia;
	private Long afectacion;

	/**
	 * @return the periodo
	 */
	public String getPeriodo() {
		return periodo;
	}

	/**
	 * @param periodo
	 *            the periodo to set
	 */
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	/**
	 * @return the facultad
	 */
	public Integer getFacultad() {
		return facultad;
	}

	/**
	 * @param facultad
	 *            the facultad to set
	 */
	public void setFacultad(Integer facultad) {
		this.facultad = facultad;
	}

	/**
	 * @return the dependencia
	 */
	public Integer getDependencia() {
		return dependencia;
	}

	/**
	 * @param dependencia
	 *            the dependencia to set
	 */
	public void setDependencia(Integer dependencia) {
		this.dependencia = dependencia;
	}

	/**
	 * @return the afectacion
	 */
	public Long getAfectacion() {
		return afectacion;
	}

	/**
	 * @param afectacion
	 *            the afectacion to set
	 */
	public void setAfectacion(Long afectacion) {
		this.afectacion = afectacion;
	}

}