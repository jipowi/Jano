/**
 * 
 */
package ec.com.uce.jano.dto;

/**
 * <b> Permite manejar la informacion de las partidas. </b>
 * 
 * @author Paul Jimenez
 * @version 1.0,24/03/2016
 * @since JDK1.6
 */
public class PartidaDTO {

	private String tipoPartida;
	private String partida;

	/**
	 * 
	 */
	public PartidaDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param tipoPartida
	 * @param partida
	 */
	public PartidaDTO(String tipoPartida, String partida) {
		super();
		this.tipoPartida = tipoPartida;
		this.partida = partida;
	}

	/**
	 * @return the tipoPartida
	 */
	public String getTipoPartida() {
		return tipoPartida;
	}

	/**
	 * @param tipoPartida
	 *            the tipoPartida to set
	 */
	public void setTipoPartida(String tipoPartida) {
		this.tipoPartida = tipoPartida;
	}

	/**
	 * @return the partida
	 */
	public String getPartida() {
		return partida;
	}

	/**
	 * @param partida
	 *            the partida to set
	 */
	public void setPartida(String partida) {
		this.partida = partida;
	}

}
