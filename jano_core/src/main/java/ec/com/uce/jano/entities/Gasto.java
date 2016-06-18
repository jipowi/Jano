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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the gastos database table.
 * 
 */
@Entity
@Table(name = "gastos")
public class Gasto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_gastos")
	private Integer idGastos;

	@Column(name = "beneficiario_gasto")
	private String beneficiarioGasto;

	@Column(name = "comprobante_gasto")
	private String comprobanteGasto;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_gasto")
	private Date fechaGasto;

	@Column(name = "obs_gasto")
	private String obsGasto;

	@Column(name = "periodo_gasto")
	private String periodoGasto;

	@Column(name = "codigo_gasto")
	private String codigoGasto;

	@Column(name = "valor_gasto")
	private double valorGasto;

	// bi-directional many-to-one association to Afectacion
	@ManyToOne
	@JoinColumn(name = "id_afectacion")
	private Afectacion afectacion;

	// bi-directional many-to-one association to Partida
	@ManyToOne
	@JoinColumn(name = "id_partida")
	private Partida partida;

	public Gasto() {
	}

	public Integer getIdGastos() {
		return this.idGastos;
	}

	public void setIdGastos(Integer idGastos) {
		this.idGastos = idGastos;
	}

	public String getBeneficiarioGasto() {
		return this.beneficiarioGasto;
	}

	public void setBeneficiarioGasto(String beneficiarioGasto) {
		this.beneficiarioGasto = beneficiarioGasto;
	}

	public String getComprobanteGasto() {
		return this.comprobanteGasto;
	}

	public void setComprobanteGasto(String comprobanteGasto) {
		this.comprobanteGasto = comprobanteGasto;
	}

	public Date getFechaGasto() {
		return this.fechaGasto;
	}

	public void setFechaGasto(Date fechaGasto) {
		this.fechaGasto = fechaGasto;
	}

	public String getObsGasto() {
		return this.obsGasto;
	}

	public void setObsGasto(String obsGasto) {
		this.obsGasto = obsGasto;
	}

	public String getPeriodoGasto() {
		return this.periodoGasto;
	}

	public void setPeriodoGasto(String periodoGasto) {
		this.periodoGasto = periodoGasto;
	}

	public double getValorGasto() {
		return this.valorGasto;
	}

	public void setValorGasto(double valorGasto) {
		this.valorGasto = valorGasto;
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
	 * @return the codigoGasto
	 */
	public String getCodigoGasto() {
		return codigoGasto;
	}

	/**
	 * @param codigoGasto
	 *            the codigoGasto to set
	 */
	public void setCodigoGasto(String codigoGasto) {
		this.codigoGasto = codigoGasto;
	}

}