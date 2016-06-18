/**
 * 
 */
package ec.com.uce.jano.dto;

import java.io.Serializable;

import ec.com.uce.jano.entities.Partida;

/**
 * <b> Permite almacenar la informacion de un egreso y el detalle. </b>
 * 
 * @author Paul Jimenez
 * @version 1.0,24/02/2016
 * @since JDK1.6
 */
public class EgresoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Partida partida;
	private Double presupuesto;

	/**
	 * @param partida
	 * @param presupuesto
	 */
	public EgresoDTO(Partida partida, Double presupuesto) {
		super();
		this.partida = partida;
		this.presupuesto = presupuesto;
	}

	/**
	 * @return the partida
	 */
	public Partida getPartida() {
		return partida;
	}

	/**
	 * @param partida the partida to set
	 */
	public void setPartida(Partida partida) {
		this.partida = partida;
	}

	/**
	 * @return the presupuesto
	 */
	public Double getPresupuesto() {
		return presupuesto;
	}

	/**
	 * @param presupuesto the presupuesto to set
	 */
	public void setPresupuesto(Double presupuesto) {
		this.presupuesto = presupuesto;
	}

	
}
