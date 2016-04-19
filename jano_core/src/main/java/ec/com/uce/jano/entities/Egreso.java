package ec.com.uce.jano.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the egreso database table.
 * 
 */
@Entity
public class Egreso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_egreso")
	private Long idEgreso;

	private String periodo;

	//bi-directional many-to-one association to DetalleEgreso
	@OneToMany(mappedBy="egreso")
	private List<DetalleEgreso> detalleEgresos;

	//bi-directional many-to-one association to Afectacion
	@ManyToOne
	@JoinColumn(name="id_afectacion")
	private Afectacion afectacion;

	public Egreso() {
	}

	public Long getIdEgreso() {
		return this.idEgreso;
	}

	public void setIdEgreso(Long idEgreso) {
		this.idEgreso = idEgreso;
	}

	public String getPeriodo() {
		return this.periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public List<DetalleEgreso> getDetalleEgresos() {
		return this.detalleEgresos;
	}

	public void setDetalleEgresos(List<DetalleEgreso> detalleEgresos) {
		this.detalleEgresos = detalleEgresos;
	}

	public DetalleEgreso addDetalleEgreso(DetalleEgreso detalleEgreso) {
		getDetalleEgresos().add(detalleEgreso);
		detalleEgreso.setEgreso(this);

		return detalleEgreso;
	}

	public DetalleEgreso removeDetalleEgreso(DetalleEgreso detalleEgreso) {
		getDetalleEgresos().remove(detalleEgreso);
		detalleEgreso.setEgreso(null);

		return detalleEgreso;
	}

	public Afectacion getAfectacion() {
		return this.afectacion;
	}

	public void setAfectacion(Afectacion afectacion) {
		this.afectacion = afectacion;
	}

}