package ec.com.uce.jano.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the partida database table.
 * 
 */
@Entity
public class Partida implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_partida")
	private Long idPartida;

	private String partida;

	@Column(name="tipo_partida")
	private String tipoPartida;

	//bi-directional many-to-one association to DetalleEgreso
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_det_egreso")
	private DetalleEgreso detalleEgreso;

	public Partida() {
	}

	public Long getIdPartida() {
		return this.idPartida;
	}

	public void setIdPartida(Long idPartida) {
		this.idPartida = idPartida;
	}

	public String getPartida() {
		return this.partida;
	}

	public void setPartida(String partida) {
		this.partida = partida;
	}

	public String getTipoPartida() {
		return this.tipoPartida;
	}

	public void setTipoPartida(String tipoPartida) {
		this.tipoPartida = tipoPartida;
	}

	public DetalleEgreso getDetalleEgreso() {
		return this.detalleEgreso;
	}

	public void setDetalleEgreso(DetalleEgreso detalleEgreso) {
		this.detalleEgreso = detalleEgreso;
	}

}