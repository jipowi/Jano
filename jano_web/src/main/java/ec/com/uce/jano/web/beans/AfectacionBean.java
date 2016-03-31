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
public class AfectacionBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private String afectacion;
	private String facultad;
	private String dependencia;
	private String departamento;

	/**
	 * @return the afectacion
	 */
	public String getAfectacion() {
		return afectacion;
	}

	/**
	 * @param afectacion
	 *            the afectacion to set
	 */
	public void setAfectacion(String afectacion) {
		this.afectacion = afectacion;
	}

	/**
	 * @return the facultad
	 */
	public String getFacultad() {
		return facultad;
	}

	/**
	 * @param facultad
	 *            the facultad to set
	 */
	public void setFacultad(String facultad) {
		this.facultad = facultad;
	}

	/**
	 * @return the dependencia
	 */
	public String getDependencia() {
		return dependencia;
	}

	/**
	 * @param dependencia
	 *            the dependencia to set
	 */
	public void setDependencia(String dependencia) {
		this.dependencia = dependencia;
	}

	/**
	 * @return the departamento
	 */
	public String getDepartamento() {
		return departamento;
	}

	/**
	 * @param departamento
	 *            the departamento to set
	 */
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

}
