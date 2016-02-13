package ec.com.avila.hiperion.emision.entities;

import java.io.Serializable;
import javax.persistence.*;

import ec.com.avila.hiperion.enumeration.EstadoEnum;

import java.util.List;


/**
 * The persistent class for the rol database table.
 * 
 */
@Entity
public class Rol implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_rol")
	private Integer idRol;

	@Column(name="descripcion_rol")
	private String descripcionRol;

	@Column(name="estado_rol")
	private EstadoEnum estadoRol;

	@Column(name="nombre_rol")
	private String nombreRol;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="id_usuario")
	private Usuario usuario;

	//bi-directional many-to-one association to RolMenu
	@OneToMany(mappedBy="rol", fetch=FetchType.EAGER)
	private List<RolMenu> rolMenus;

	public Rol() {
	}

	public Integer getIdRol() {
		return this.idRol;
	}

	public void setIdRol(Integer idRol) {
		this.idRol = idRol;
	}

	public String getDescripcionRol() {
		return this.descripcionRol;
	}

	public void setDescripcionRol(String descripcionRol) {
		this.descripcionRol = descripcionRol;
	}

	

	/**
	 * @return the estadoRol
	 */
	public EstadoEnum getEstadoRol() {
		return estadoRol;
	}

	/**
	 * @param estadoRol the estadoRol to set
	 */
	public void setEstadoRol(EstadoEnum estadoRol) {
		this.estadoRol = estadoRol;
	}

	public String getNombreRol() {
		return this.nombreRol;
	}

	public void setNombreRol(String nombreRol) {
		this.nombreRol = nombreRol;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<RolMenu> getRolMenus() {
		return this.rolMenus;
	}

	public void setRolMenus(List<RolMenu> rolMenus) {
		this.rolMenus = rolMenus;
	}

	public RolMenu addRolMenus(RolMenu rolMenus) {
		getRolMenus().add(rolMenus);
		rolMenus.setRol(this);

		return rolMenus;
	}

	public RolMenu removeRolMenus(RolMenu rolMenus) {
		getRolMenus().remove(rolMenus);
		rolMenus.setRol(null);

		return rolMenus;
	}

}