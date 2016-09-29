/**
 * 
 */
package ec.com.uce.jano.dto;


/**
 * <b> DTO para almacenar la informacion de cada una de las recaudaciones obtenidas de la BD. </b>
 * 
 * @author Paul Jimenez
 * @version 1.0,09/06/2016
 * @since JDK1.6
 */
public class AfectacionDTO {

	private Long idAfectacion;
	private String facultad;
	private String dependencia;
	private String afectacion;

	/**
	 * 
	 */
	public AfectacionDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the idAfectacion
	 */
	public Long getIdAfectacion() {
		return idAfectacion;
	}

	/**
	 * @param idAfectacion
	 *            the idAfectacion to set
	 */
	public void setIdAfectacion(Long idAfectacion) {
		this.idAfectacion = idAfectacion;
	}

	/**
	 * @return the facultad
	 */
	public String getFacultad() {
		return facultad;
	}

	/**
	 * @param facultad
	 *            the facultad to set
	 */
	public void setFacultad(String facultad) {
		this.facultad = facultad;
	}

	/**
	 * @return the dependencia
	 */
	public String getDependencia() {
		return dependencia;
	}

	/**
	 * @param dependencia
	 *            the dependencia to set
	 */
	public void setDependencia(String dependencia) {
		this.dependencia = dependencia;
	}

	/**
	 * @return the afectacion
	 */
	public String getAfectacion() {
		return afectacion;
	}

	/**
	 * @param afectacion
	 *            the afectacion to set
	 */
	public void setAfectacion(String afectacion) {
		this.afectacion = afectacion;
	}

}
