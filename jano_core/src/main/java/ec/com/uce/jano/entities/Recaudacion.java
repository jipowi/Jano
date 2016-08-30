package ec.com.uce.jano.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the recaudacion database table.
 * 
 */
@Entity
public class Recaudacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_recaudacion")
	private Integer idRecaudacion;

	private String beneficiario;

	@Column(name = "codigo_ingreso")
	private String codigoIngreso;

	private String comprobante;

	@Column(name = "estado_recaudacion")
	private String estadoRecaudacion;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_recaudacion")
	private Date fechaRecaudacion;

	private String observacion;

	@Column(name = "perido_recaudacion")
	private String peridoRecaudacion;

	@Column(name = "cur_recaudacion")
	private String cur;

	@Column(name = "valor_recaudacion")
	private double valorRecaudacion;

	// bi-directional many-to-one association to Afectacion
	@ManyToOne
	@JoinColumn(name = "id_afectacion")
	private Afectacion afectacion;

	// bi-directional many-to-one association to Partida
	@ManyToOne
	@JoinColumn(name = "id_partida")
	private Partida partida;

	public Recaudacion() {
	}

	public Integer getIdRecaudacion() {
		return this.idRecaudacion;
	}

	public void setIdRecaudacion(Integer idRecaudacion) {
		this.idRecaudacion = idRecaudacion;
	}

	public String getBeneficiario() {
		return this.beneficiario;
	}

	public void setBeneficiario(String beneficiario) {
		this.beneficiario = beneficiario.toUpperCase();
	}

	public String getCodigoIngreso() {
		return this.codigoIngreso;
	}

	public void setCodigoIngreso(String codigoIngreso) {
		this.codigoIngreso = codigoIngreso;
	}

	public String getComprobante() {
		return this.comprobante;
	}

	public void setComprobante(String comprobante) {
		this.comprobante = comprobante;
	}

	public String getEstadoRecaudacion() {
		return this.estadoRecaudacion;
	}

	public void setEstadoRecaudacion(String estadoRecaudacion) {
		this.estadoRecaudacion = estadoRecaudacion;
	}

	public Date getFechaRecaudacion() {
		return this.fechaRecaudacion;
	}

	public void setFechaRecaudacion(Date fechaRecaudacion) {
		this.fechaRecaudacion = fechaRecaudacion;
	}

	public String getObservacion() {
		return this.observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion.toLowerCase();
	}

	public String getPeridoRecaudacion() {
		return this.peridoRecaudacion;
	}

	public void setPeridoRecaudacion(String peridoRecaudacion) {
		this.peridoRecaudacion = peridoRecaudacion;
	}

	public double getValorRecaudacion() {
		return this.valorRecaudacion;
	}

	public void setValorRecaudacion(double valorRecaudacion) {
		this.valorRecaudacion = valorRecaudacion;
	}

	public Afectacion getAfectacion() {
		return this.afectacion;
	}

	public void setAfectacion(Afectacion afectacion) {
		this.afectacion = afectacion;
	}

	public Partida getPartida() {
		return this.partida;
	}

	public void setPartida(Partida partida) {
		this.partida = partida;
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