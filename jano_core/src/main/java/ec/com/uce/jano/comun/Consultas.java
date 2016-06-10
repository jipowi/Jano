package ec.com.uce.jano.comun;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
		// CATALOGO
		@NamedQuery(name = "Catalogo.findById", query = "SELECT c FROM Catalogo c WHERE c.idCatalogo =:idCatalogo"),

		// DETALLE_CATALOGO
		@NamedQuery(name = "DetalleCatalogo.findByCodCatalogo", query = "SELECT d FROM DetalleCatalogo d WHERE d.catalogo.idCatalogo =:idCatalogo ORDER BY d.descDetCatalogo"),
		@NamedQuery(name = "DetalleCatalogo.findByCodCatalogoAndCodDetalle", query = "SELECT d FROM DetalleCatalogo d WHERE d.catalogo.idCatalogo =:idCatalogo AND d.codDetalleCatalogo=:codDetalleCatalogo"),
		@NamedQuery(name = "DetalleCatalogo.findByCodDepCatalogo", query = "SELECT d FROM DetalleCatalogo d WHERE d.codDepDetalleCatalogo =:codDepDetalleCatalogo AND d.codDepDetalleCatalogo=:codDepDetCalogo"),
		@NamedQuery(name = "DetalleCatalogo.findByCodigoDetalle", query = "SELECT d FROM DetalleCatalogo d WHERE d.codDepDetalleCatalogo =:codDepDetalleCatalogo AND d.catalogo.idCatalogo=:idCatalogo"),
		// USUARIO
		@NamedQuery(name = "Usuario.loginUser", query = "SELECT u FROM Usuario u WHERE u.usuario =:usuario AND u.clave =:clave"),
		@NamedQuery(name = "Rol.findByUsuario", query = "SELECT r FROM Rol r WHERE r.usuario.idUsuario =:idUsuario"),

		// PARTIDA
		@NamedQuery(name = "Partida.findByTipoPartida", query = "SELECT p FROM Partida p WHERE p.tipoPartida =:tipoPartida"),

		// EGRESOS
		@NamedQuery(name = "Egreso.findEgresos", query = "SELECT e FROM Egreso e WHERE e.periodo =:periodo AND e.afectacion.idAfectacion =:idAfectacion"),

		// INGRESOS
		@NamedQuery(name = "Ingreso.findIngresos", query = "SELECT i FROM Ingreso i WHERE i.periodoIngreso =:periodo AND i.afectacion.idAfectacion =:idAfectacion"),

		// DETALLE EGRESOS
		@NamedQuery(name = "DetalleEgreso.findEgreso", query = "SELECT e FROM DetalleEgreso e WHERE e.egreso.idEgreso =:idEgreso"),

		// DETALLE INGRESOS
		@NamedQuery(name = "DetalleIngreso.findIngreso", query = "SELECT i FROM DetalleIngreso i WHERE i.ingreso.idIngreso =:idIngreso"),

		// AFECTACIONES
		@NamedQuery(name = "Afectacion.findFacultad", query = "SELECT a FROM Afectacion a WHERE a.idFacultad = null AND a.idDependencia = null"),

		// AFECTACIONES - DEPENDENCIA
		@NamedQuery(name = "Afectacion.findDependencia", query = "SELECT a FROM Afectacion a WHERE a.idFacultad =:idFacultad AND a.idDependencia = null"),

		// AFECTACIONES - DEPARTAMENTO
		@NamedQuery(name = "Afectacion.findDepartamento", query = "SELECT a FROM Afectacion a WHERE a.idFacultad =:idFacultad AND a.idDependencia =:idDependencia"),

		// RECAUDACIONES
		@NamedQuery(name = "Recaudacion.reporte", query = "SELECT r FROM Recaudacion r WHERE r.afectacion.idAfectacion =:afectacion"),

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
