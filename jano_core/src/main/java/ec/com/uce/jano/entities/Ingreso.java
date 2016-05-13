package ec.com.uce.jano.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the ingreso database table.
 * 
 */
@Entity
public class Ingreso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_ingreso")
	private Long idIngreso;

	@Column(name="periodo_ingreso")
	private String periodoIngreso;

	//bi-directional many-to-one association to DetalleIngreso
	@OneToMany(mappedBy="ingreso")
	private List<DetalleIngreso> detalleIngresos;

	//bi-directional many-to-one association to Afectacion
	@ManyToOne
	@JoinColumn(name="id_afectacion")
	private Afectacion afectacion;

	public Ingreso() {
	}

	public Long getIdIngreso() {
		return this.idIngreso;
	}

	public void setIdIngreso(Long idIngreso) {
		this.idIngreso = idIngreso;
	}

	public String getPeriodoIngreso() {
		return this.periodoIngreso;
	}

	public void setPeriodoIngreso(String periodoIngreso) {
		this.periodoIngreso = periodoIngreso;
	}

	public List<DetalleIngreso> getDetalleIngresos() {
		return this.detalleIngresos;
	}

	public void setDetalleIngresos(List<DetalleIngreso> detalleIngresos) {
		this.detalleIngresos = detalleIngresos;
	}

	public DetalleIngreso addDetalleIngreso(DetalleIngreso detalleIngreso) {
		getDetalleIngresos().add(detalleIngreso);
		detalleIngreso.setIngreso(this);

		return detalleIngreso;
	}

	public DetalleIngreso removeDetalleIngreso(DetalleIngreso detalleIngreso) {
		getDetalleIngresos().remove(detalleIngreso);
		detalleIngreso.setIngreso(null);

		return detalleIngreso;
	}

	public Afectacion getAfectacion() {
		return this.afectacion;
	}

	public void setAfectacion(Afectacion afectacion) {
		this.afectacion = afectacion;
	}

}