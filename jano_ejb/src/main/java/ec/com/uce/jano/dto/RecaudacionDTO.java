/**
 * 
 */
package ec.com.uce.jano.dto;

import java.util.Date;

import ec.com.uce.jano.entities.Afectacion;
import ec.com.uce.jano.entities.Partida;

/**
 * <b> DTO para almacenar la informacion de cada una de las recaudaciones obtenidas de la BD. </b>
 * 
 * @author Paul Jimenez
 * @version 1.0,09/06/2016
 * @since JDK1.6
 */
public class RecaudacionDTO {

	private Integer idRecaudacion;
	private String beneficiario;
	private String comprobante;
	private Date fechaRecaudacion;
	private String observacion;
	private double valorRecaudacion;
	private Afectacion afectacion;
	private Partida partida;
	private String cur;
	private String periodo;
	private String codigoIngreso;
	private String estado;

	/**
	 * @param idRecaudacion
	 * @param beneficiario
	 * @param comprobante
	 * @param fechaRecaudacion
	 * @param observacion
	 * @param valorRecaudacion
	 * @param afectacion
	 * @param partida
	 * @param cur
	 * @param periodo
	 * @param codigoIngreso
	 * @param estado
	 */
	public RecaudacionDTO(Integer idRecaudacion, String beneficiario, String comprobante, Date fechaRecaudacion, String observacion, double valorRecaudacion,
			Afectacion afectacion, Partida partida, String cur, String periodo, String codigoIngreso, String estado) {
		super();
		this.idRecaudacion = idRecaudacion;
		this.beneficiario = beneficiario;
		this.comprobante = comprobante;
		this.fechaRecaudacion = fechaRecaudacion;
		this.observacion = observacion;
		this.valorRecaudacion = valorRecaudacion;
		this.afectacion = afectacion;
		this.partida = partida;
		this.cur = cur;
		this.periodo = periodo;
		this.codigoIngreso = codigoIngreso;
		this.estado = estado;
	}

	public RecaudacionDTO(String beneficiario, String comprobante, Date fechaRecaudacion, String observacion, double valorRecaudacion, Afectacion afectacion, Partida partida,
			String periodo) {

		this.beneficiario = beneficiario;
		this.comprobante = comprobante;
		this.fechaRecaudacion = fechaRecaudacion;
		this.observacion = observacion;
		this.valorRecaudacion = valorRecaudacion;
		this.afectacion = afectacion;
		this.partida = partida;
		this.periodo = periodo;
	}

	/**
	 * 
	 */
	public RecaudacionDTO() {
		super();
		// TODO Auto-generated constructor stub
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
	 * @return the fechaRecaudacion
	 */
	public Date getFechaRecaudacion() {
		return fechaRecaudacion;
	}

	/**
	 * @param fechaRecaudacion
	 *            the fechaRecaudacion to set
	 */
	public void setFechaRecaudacion(Date fechaRecaudacion) {
		this.fechaRecaudacion = fechaRecaudacion;
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
	 * @return the valorRecaudacion
	 */
	public double getValorRecaudacion() {
		return valorRecaudacion;
	}

	/**
	 * @param valorRecaudacion
	 *            the valorRecaudacion to set
	 */
	public void setValorRecaudacion(double valorRecaudacion) {
		this.valorRecaudacion = valorRecaudacion;
	}

	/**
	 * @return the afectacion
	 */
	public Afectacion getAfectacion() {
		return afectacion;
	}

	/**
	 * @param afectacion
	 *            the afectacion to set
	 */
	public void setAfectacion(Afectacion afectacion) {
		this.afectacion = afectacion;
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
	 * @return the idRecaudacion
	 */
	public Integer getIdRecaudacion() {
		return idRecaudacion;
	}

	/**
	 * @param idRecaudacion
	 *            the idRecaudacion to set
	 */
	public void setIdRecaudacion(Integer idRecaudacion) {
		this.idRecaudacion = idRecaudacion;
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

	/**
	 * @return the codigoIngreso
	 */
	public String getCodigoIngreso() {
		return codigoIngreso;
	}

	/**
	 * @param codigoIngreso
	 *            the codigoIngreso to set
	 */
	public void setCodigoIngreso(String codigoIngreso) {
		this.codigoIngreso = codigoIngreso;
	}

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
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @param estado
	 *            the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

}
