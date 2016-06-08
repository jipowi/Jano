package ec.com.uce.jano.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * The persistent class for the detalle_ingreso database table.
 * 
 */
@Entity
@Table(name="detalle_ingreso")
public class DetalleIngreso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_det_ingreso")
	private Long idDetIngreso;

	@Column(name="presupuesto_ingreso")
	private double presupuestoIngreso;

	//bi-directional many-to-one association to Ingreso
	@ManyToOne
	@JoinColumn(name="id_ingreso")
	private Ingreso ingreso;

	//bi-directional many-to-one association to Partida
	@ManyToOne
	@JoinColumn(name="id_partida")
	private Partida partida;

	public DetalleIngreso() {
	}

	public Long getIdDetIngreso() {
		return this.idDetIngreso;
	}

	public void setIdDetIngreso(Long idDetIngreso) {
		this.idDetIngreso = idDetIngreso;
	}

	public double getPresupuestoIngreso() {
		return this.presupuestoIngreso;
	}

	public void setPresupuestoIngreso(double presupuestoIngreso) {
		this.presupuestoIngreso = presupuestoIngreso;
	}

	public Ingreso getIngreso() {
		return this.ingreso;
	}

	public void setIngreso(Ingreso ingreso) {
		this.ingreso = ingreso;
	}

	public Partida getPartida() {
		return this.partida;
	}

	public void setPartida(Partida partida) {
		this.partida = partida;
	}

}