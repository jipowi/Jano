/**
 * 
 */
package ec.com.uce.jano.dto;

import java.util.Date;

import ec.com.uce.jano.entities.Afectacion;

/**
 * <b> DTO para almacenar la informacion de cada una de las recaudaciones obtenidas de la BD. </b>
 * 
 * @author Paul Jimenez
 * @version 1.0,09/06/2016
 * @since JDK1.6
 */
public class recaudacionDTO {

	private String beneficiario;
	private String comprobante;
	private Date fechaRecaudacion;
	private String observacion;
	private double valorRecaudacion;
	private Afectacion afectacion;

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

}
