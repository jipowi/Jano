package ec.com.uce.jano.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


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
	@OneToMany(mappedBy="partida")
	private List<DetalleEgreso> detalleEgresos;

	//bi-directional many-to-one association to DetalleIngreso
	@OneToMany(mappedBy="partida")
	private List<DetalleIngreso> detalleIngresos;

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

	public List<DetalleEgreso> getDetalleEgresos() {
		return this.detalleEgresos;
	}

	public void setDetalleEgresos(List<DetalleEgreso> detalleEgresos) {
		this.detalleEgresos = detalleEgresos;
	}

	public DetalleEgreso addDetalleEgreso(DetalleEgreso detalleEgreso) {
		getDetalleEgresos().add(detalleEgreso);
		detalleEgreso.setPartida(this);

		return detalleEgreso;
	}

	public DetalleEgreso removeDetalleEgreso(DetalleEgreso detalleEgreso) {
		getDetalleEgresos().remove(detalleEgreso);
		detalleEgreso.setPartida(null);

		return detalleEgreso;
	}

	public List<DetalleIngreso> getDetalleIngresos() {
		return this.detalleIngresos;
	}

	public void setDetalleIngresos(List<DetalleIngreso> detalleIngresos) {
		this.detalleIngresos = detalleIngresos;
	}

	public DetalleIngreso addDetalleIngreso(DetalleIngreso detalleIngreso) {
		getDetalleIngresos().add(detalleIngreso);
		detalleIngreso.setPartida(this);

		return detalleIngreso;
	}

	public DetalleIngreso removeDetalleIngreso(DetalleIngreso detalleIngreso) {
		getDetalleIngresos().remove(detalleIngreso);
		detalleIngreso.setPartida(null);

		return detalleIngreso;
	}

}