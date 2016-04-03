package ec.com.uce.jano.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the afectacion database table.
 * 
 */
@Entity
public class Afectacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_afectacion")
	private Long idAfectacion;

	@Column(name="desc_afectacion")
	private String descAfectacion;

	@Column(name="id_dependencia")
	private Integer idDependencia;

	@Column(name="id_facultad")
	private Integer idFacultad;

	//bi-directional many-to-one association to Egreso
	@OneToMany(mappedBy="afectacion")
	private List<Egreso> egresos;

	public Afectacion() {
	}

	public Long getIdAfectacion() {
		return this.idAfectacion;
	}

	public void setIdAfectacion(Long idAfectacion) {
		this.idAfectacion = idAfectacion;
	}

	public String getDescAfectacion() {
		return this.descAfectacion;
	}

	public void setDescAfectacion(String descAfectacion) {
		this.descAfectacion = descAfectacion.toUpperCase();
	}

	public Integer getIdDependencia() {
		return this.idDependencia;
	}

	public void setIdDependencia(Integer idDependencia) {
		this.idDependencia = idDependencia;
	}

	public Integer getIdFacultad() {
		return this.idFacultad;
	}

	public void setIdFacultad(Integer idFacultad) {
		this.idFacultad = idFacultad;
	}

	public List<Egreso> getEgresos() {
		return this.egresos;
	}

	public void setEgresos(List<Egreso> egresos) {
		this.egresos = egresos;
	}

	public Egreso addEgreso(Egreso egreso) {
		getEgresos().add(egreso);
		egreso.setAfectacion(this);

		return egreso;
	}

	public Egreso removeEgreso(Egreso egreso) {
		getEgresos().remove(egreso);
		egreso.setAfectacion(null);

		return egreso;
	}

}