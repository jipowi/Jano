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

	private double presupuesto;

	//bi-directional many-to-one association to Egreso
	@ManyToOne
	@JoinColumn(name="id_egreso")
	private Egreso egreso;

	//bi-directional many-to-one association to Partida
	@ManyToOne
	@JoinColumn(name="id_partida")
	private Partida partida;

	public DetalleEgreso() {
	}

	public Integer getIdDetEgreso() {
		return this.idDetEgreso;
	}

	public void setIdDetEgreso(Integer idDetEgreso) {
		this.idDetEgreso = idDetEgreso;
	}

	public double getPresupuesto() {
		return this.presupuesto;
	}

	public void setPresupuesto(double presupuesto) {
		this.presupuesto = presupuesto;
	}

	public Egreso getEgreso() {
		return this.egreso;
	}

	public void setEgreso(Egreso egreso) {
		this.egreso = egreso;
	}

	public Partida getPartida() {
		return this.partida;
	}

	public void setPartida(Partida partida) {
		this.partida = partida;
	}

}