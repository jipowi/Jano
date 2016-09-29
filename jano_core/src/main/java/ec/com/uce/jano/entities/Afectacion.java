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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_afectacion")
	private Long idAfectacion;

	@Column(name = "desc_afectacion")
	private String descAfectacion;

	@Column(name = "id_dependencia")
	private Long idDependencia;

	@Column(name = "id_facultad")
	private Long idFacultad;

	// bi-directional many-to-one association to Egreso
	@OneToMany(mappedBy = "afectacion")
	private List<Egreso> egresos;

	// bi-directional many-to-one association to Gasto
	@OneToMany(mappedBy = "afectacion")
	private List<Gasto> gastos;

	// bi-directional many-to-one association to Ingreso
	@OneToMany(mappedBy = "afectacion")
	private List<Ingreso> ingresos;

	// bi-directional many-to-one association to Recaudacion
	@OneToMany(mappedBy = "afectacion")
	private List<Recaudacion> recaudacions;

	// bi-directional many-to-one association to Reforma
	@OneToMany(mappedBy = "afectacion")
	private List<Reforma> reformas;

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

	public List<Gasto> getGastos() {
		return this.gastos;
	}

	public void setGastos(List<Gasto> gastos) {
		this.gastos = gastos;
	}

	public Gasto addGasto(Gasto gasto) {
		getGastos().add(gasto);
		gasto.setAfectacion(this);

		return gasto;
	}

	public Gasto removeGasto(Gasto gasto) {
		getGastos().remove(gasto);
		gasto.setAfectacion(null);

		return gasto;
	}

	public List<Ingreso> getIngresos() {
		return this.ingresos;
	}

	public void setIngresos(List<Ingreso> ingresos) {
		this.ingresos = ingresos;
	}

	public Ingreso addIngreso(Ingreso ingreso) {
		getIngresos().add(ingreso);
		ingreso.setAfectacion(this);

		return ingreso;
	}

	public Ingreso removeIngreso(Ingreso ingreso) {
		getIngresos().remove(ingreso);
		ingreso.setAfectacion(null);

		return ingreso;
	}

	public List<Recaudacion> getRecaudacions() {
		return this.recaudacions;
	}

	public void setRecaudacions(List<Recaudacion> recaudacions) {
		this.recaudacions = recaudacions;
	}

	public Recaudacion addRecaudacion(Recaudacion recaudacion) {
		getRecaudacions().add(recaudacion);
		recaudacion.setAfectacion(this);

		return recaudacion;
	}

	public Recaudacion removeRecaudacion(Recaudacion recaudacion) {
		getRecaudacions().remove(recaudacion);
		recaudacion.setAfectacion(null);

		return recaudacion;
	}

	public List<Reforma> getReformas() {
		return this.reformas;
	}

	public void setReformas(List<Reforma> reformas) {
		this.reformas = reformas;
	}

	public Reforma addReforma(Reforma reforma) {
		getReformas().add(reforma);
		reforma.setAfectacion(this);

		return reforma;
	}

	public Reforma removeReforma(Reforma reforma) {
		getReformas().remove(reforma);
		reforma.setAfectacion(null);

		return reforma;
	}

	/**
	 * @return the idDependencia
	 */
	public Long getIdDependencia() {
		return idDependencia;
	}

	/**
	 * @param idDependencia
	 *            the idDependencia to set
	 */
	public void setIdDependencia(Long idDependencia) {
		this.idDependencia = idDependencia;
	}

	/**
	 * @return the idFacultad
	 */
	public Long getIdFacultad() {
		return idFacultad;
	}

	/**
	 * @param idFacultad
	 *            the idFacultad to set
	 */
	public void setIdFacultad(Long idFacultad) {
		this.idFacultad = idFacultad;
	}

}