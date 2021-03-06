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
 * @author Paul Jimenez
 * @version 1.0,23/02/2016
 * @since JDK1.6
 */
@ManagedBean
@ViewScoped
public class AfectacionBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private String afectacion;
	private Long facultad;
	private Long dependencia;
	private Long departamento;

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
	public Long getFacultad() {
		return facultad;
	}

	/**
	 * @param facultad
	 *            the facultad to set
	 */
	public void setFacultad(Long facultad) {
		this.facultad = facultad;
	}

	/**
	 * @return the dependencia
	 */
	public Long getDependencia() {
		return dependencia;
	}

	/**
	 * @param dependencia
	 *            the dependencia to set
	 */
	public void setDependencia(Long dependencia) {
		this.dependencia = dependencia;
	}

	/**
	 * @return the departamento
	 */
	public Long getDepartamento() {
		return departamento;
	}

	/**
	 * @param departamento
	 *            the departamento to set
	 */
	public void setDepartamento(Long departamento) {
		this.departamento = departamento;
	}

}
