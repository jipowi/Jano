package ec.com.uce.jano.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


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

	private BigDecimal presupuesto;

	//bi-directional many-to-one association to Egreso
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_egreso")
	private Egreso egreso;

	//bi-directional many-to-one association to Partida
	@OneToMany(mappedBy="detalleEgreso")
	private List<Partida> partidas;

	public DetalleEgreso() {
	}

	public Integer getIdDetEgreso() {
		return this.idDetEgreso;
	}

	public void setIdDetEgreso(Integer idDetEgreso) {
		this.idDetEgreso = idDetEgreso;
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

	public List<Partida> getPartidas() {
		return this.partidas;
	}

	public void setPartidas(List<Partida> partidas) {
		this.partidas = partidas;
	}

	public Partida addPartida(Partida partida) {
		getPartidas().add(partida);
		partida.setDetalleEgreso(this);

		return partida;
	}

	public Partida removePartida(Partida partida) {
		getPartidas().remove(partida);
		partida.setDetalleEgreso(null);

		return partida;
	}

}