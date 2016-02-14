package ec.com.avila.hiperion.comun;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
		// CATALOGO
		@NamedQuery(name = "Catalogo.findById", query = "SELECT c FROM Catalogo c WHERE c.idCatalogo =:idCatalogo"),

		// TIPO_DIRECCION
		// DETALLE_CATALOGO
		@NamedQuery(name = "DetalleCatalogo.findByCodCatalogo", query = "SELECT d FROM DetalleCatalogo d WHERE d.catalogo.idCatalogo =:idCatalogo ORDER BY d.descDetCatalogo"),
		@NamedQuery(name = "DetalleCatalogo.findByCodCatalogoAndCodDetalle", query = "SELECT d FROM DetalleCatalogo d WHERE d.catalogo.idCatalogo =:idCatalogo AND d.codDetalleCatalogo=:codDetalleCatalogo"),
		@NamedQuery(name = "DetalleCatalogo.findByCodDepCatalogo", query = "SELECT d FROM DetalleCatalogo d WHERE d.codDepDetalleCatalogo =:codDepDetalleCatalogo AND d.codDepDetalleCatalogo=:codDepDetCalogo"),
		@NamedQuery(name = "DetalleCatalogo.findByCodigoDetalle", query = "SELECT d FROM DetalleCatalogo d WHERE d.codDepDetalleCatalogo =:codDepDetalleCatalogo AND d.catalogo.idCatalogo=:idCatalogo"),
		// USUARIO
		@NamedQuery(name = "Usuario.loginUser", query = "SELECT u FROM Usuario u WHERE u.usuario =:usuario AND u.clave =:clave"),
		// ROL
		@NamedQuery(name = "Rol.findByUsuario", query = "SELECT r FROM Rol r WHERE r.usuario.idUsuario =:idUsuario"),
		// MENU
		@NamedQuery(name = "Menu.findByRol", query = "SELECT m FROM RolMenu m WHERE m.rol.idRol =:idRol") })
public class Consultas implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Integer comunId;

	/**
	 * 
	 * @return
	 */
	public Integer getComunId() {
		return comunId;
	}

	/**
	 * 
	 * @param comunId
	 */
	public void setComunId(Integer comunId) {
		this.comunId = comunId;
	}
}
