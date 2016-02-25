package ec.com.uce.jano.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the detalle_egreso database table.
 * 
 */
@Entity
@Table(name="detalle_egreso")
public class DetalleEgreso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_det_egreso")
	private Integer idDetEgreso;

	@Column(name="cod_partida")
	private Integer codPartida;

	private String partida;

	private BigDecimal presupuesto;

	//bi-directional many-to-one association to Egreso
	@ManyToOne
	@JoinColumn(name="id_egreso")
	private Egreso egreso;

	public DetalleEgreso() {
	}

	public Integer getIdDetEgreso() {
		return this.idDetEgreso;
	}

	public void setIdDetEgreso(Integer idDetEgreso) {
		this.idDetEgreso = idDetEgreso;
	}

	public Integer getCodPartida() {
		return this.codPartida;
	}

	public void setCodPartida(Integer codPartida) {
		this.codPartida = codPartida;
	}

	public String getPartida() {
		return this.partida;
	}

	public void setPartida(String partida) {
		this.partida = partida;
	}

	public BigDecimal getPresupuesto() {
		return this.presupuesto;
	}

	public void setPresupuesto(BigDecimal presupuesto) {
		this.presupuesto = presupuesto;
	}

	public Egreso getEgreso() {
		return this.egreso;
	}

	public void setEgreso(Egreso egreso) {
		this.egreso = egreso;
	}

}