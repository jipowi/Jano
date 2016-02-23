package ec.com.avila.hiperion.emision.entities;

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
	private Integer idEgreso;

	private String departamento;

	private String dependencia;

	private String facultad;

	private String periodo;

	//bi-directional many-to-one association to DetalleEgreso
	@OneToMany(mappedBy="egreso", fetch=FetchType.EAGER)
	private List<DetalleEgreso> detalleEgresos;

	public Egreso() {
	}

	public Integer getIdEgreso() {
		return this.idEgreso;
	}

	public void setIdEgreso(Integer idEgreso) {
		this.idEgreso = idEgreso;
	}

	public String getDepartamento() {
		return this.departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public String getDependencia() {
		return this.dependencia;
	}

	public void setDependencia(String dependencia) {
		this.dependencia = dependencia;
	}

	public String getFacultad() {
		return this.facultad;
	}

	public void setFacultad(String facultad) {
		this.facultad = facultad;
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

}