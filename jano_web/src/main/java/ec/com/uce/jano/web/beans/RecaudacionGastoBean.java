/*
 * Copyright 2016 JIPOVI Solutions - ECUADOR 
 * Todos los derechos reservados
 */
package ec.com.uce.jano.web.beans;

import java.io.Serializable;
import java.util.Date;

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
public class RecaudacionGastoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private String comprobante;
	private Date fecha;
	private String periodo;
	private String observacion;
	private String beneficiario;
	private double valor;
	private Long facultad;
	private Long dependencia;
	private Long idAfectacion;
	private String cur;

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
	 * @return the comprobante
	 */
	public String getComprobante() {
		return comprobante;
	}

	/**
	 * @param comprobante
	 *            the comprobante to set
	 */
	public void setComprobante(String comprobante) {
		this.comprobante = comprobante;
	}

	/**
	 * @return the fecha
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * @param fecha
	 *            the fecha to set
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	/**
	 * @return the observacion
	 */
	public String getObservacion() {
		return observacion;
	}

	/**
	 * @param observacion
	 *            the observacion to set
	 */
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	/**
	 * @return the beneficiario
	 */
	public String getBeneficiario() {
		return beneficiario;
	}

	/**
	 * @param beneficiario
	 *            the beneficiario to set
	 */
	public void setBeneficiario(String beneficiario) {
		this.beneficiario = beneficiario;
	}

	/**
	 * @return the valor
	 */
	public double getValor() {
		return valor;
	}

	/**
	 * @param valor
	 *            the valor to set
	 */
	public void setValor(double valor) {
		this.valor = valor;
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
	 * @return the idAfectacion
	 */
	public Long getIdAfectacion() {
		return idAfectacion;
	}

	/**
	 * @param idAfectacion
	 *            the idAfectacion to set
	 */
	public void setIdAfectacion(Long idAfectacion) {
		this.idAfectacion = idAfectacion;
	}

	/**
	 * @return the cur
	 */
	public String getCur() {
		return cur;
	}

	/**
	 * @param cur
	 *            the cur to set
	 */
	public void setCur(String cur) {
		this.cur = cur;
	}

}
