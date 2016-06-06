package ec.com.uce.jano.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the recaudacion database table.
 * 
 */
@Entity
public class Recaudacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_recaudacion")
	private Integer idRecaudacion;

	private String beneficiario;

	private String comprobante;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_recaudacion")
	private Date fechaRecaudacion;

	private String observacion;

	@Column(name="perido_recaudacion")
	private String peridoRecaudacion;

	@Column(name="valor_recaudacion")
	private BigDecimal valorRecaudacion;

	//bi-directional many-to-one association to Afectacion
	@ManyToOne
	@JoinColumn(name="id_afectacion")
	private Afectacion afectacion;

	//bi-directional many-to-one association to Partida
	@ManyToOne
	@JoinColumn(name="id_partida")
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
		this.beneficiario = beneficiario;
	}

	public String getComprobante() {
		return this.comprobante;
	}

	public void setComprobante(String comprobante) {
		this.comprobante = comprobante;
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
		this.observacion = observacion;
	}

	public String getPeridoRecaudacion() {
		return this.peridoRecaudacion;
	}

	public void setPeridoRecaudacion(String peridoRecaudacion) {
		this.peridoRecaudacion = peridoRecaudacion;
	}

	public BigDecimal getValorRecaudacion() {
		return this.valorRecaudacion;
	}

	public void setValorRecaudacion(BigDecimal valorRecaudacion) {
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

}