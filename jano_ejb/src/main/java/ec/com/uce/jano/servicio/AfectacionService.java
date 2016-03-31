/**
 * 
 */
package ec.com.uce.jano.servicio;

import javax.ejb.Local;

import ec.com.uce.jano.comun.HiperionException;
import ec.com.uce.jano.entities.Afectacion;

/**
 * <b> Interfaz que permite realizar las opercaiones necesarias sobre las afectaciones. </b>
 * 
 * @author Paul Jimenez
 * @version 1.0,31/03/2016
 * @since JDK1.6
 */
@Local
public interface AfectacionService {

	/**
	 * 
	 * <b> Permite registrar una afectacion en la base de datos. </b>
	 * <p>
	 * [Author: Paul Jimenez, Date: 31/03/2016]
	 * </p>
	 * 
	 * @param afectacion
	 * @throws HiperionException
	 */
	public void guardarAfectacion(Afectacion afectacion) throws HiperionException;;
}
