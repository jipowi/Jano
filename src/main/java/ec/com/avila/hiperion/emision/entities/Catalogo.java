package ec.com.avila.hiperion.emision.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the catalogo database table.
 * 
 */
@Entity
public class Catalogo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_catalogo")
	private Long idCatalogo;

	@Column(name = "descripcion_catalogo")
	private String descripcionCatalogo;

	// bi-directional many-to-one association to DetalleCatalogo
	@OneToMany(mappedBy = "catalogo", fetch = FetchType.EAGER)
	private List<DetalleCatalogo> detalleCatalogos;

	public Catalogo() {
	}

	/**
	 * @return the idCatalogo
	 */
	public Long getIdCatalogo() {
		return idCatalogo;
	}

	/**
	 * @param idCatalogo
	 *            the idCatalogo to set
	 */
	public void setIdCatalogo(Long idCatalogo) {
		this.idCatalogo = idCatalogo;
	}

	public String getDescripcionCatalogo() {
		return this.descripcionCatalogo;
	}

	public void setDescripcionCatalogo(String descripcionCatalogo) {
		this.descripcionCatalogo = descripcionCatalogo;
	}

	public List<DetalleCatalogo> getDetalleCatalogos() {
		return this.detalleCatalogos;
	}

	public void setDetalleCatalogos(List<DetalleCatalogo> detalleCatalogos) {
		this.detalleCatalogos = detalleCatalogos;
	}

	public DetalleCatalogo addDetalleCatalogo(DetalleCatalogo detalleCatalogo) {
		getDetalleCatalogos().add(detalleCatalogo);
		detalleCatalogo.setCatalogo(this);

		return detalleCatalogo;
	}

	public DetalleCatalogo removeDetalleCatalogo(DetalleCatalogo detalleCatalogo) {
		getDetalleCatalogos().remove(detalleCatalogo);
		detalleCatalogo.setCatalogo(null);

		return detalleCatalogo;
	}

}