/**
 * 
 */
package ec.com.uce.jano.servicio;

import javax.ejb.Local;

import ec.com.uce.jano.comun.HiperionException;
import ec.com.uce.jano.entities.Egreso;

/**
 * <b> Interface local para realizar las operaciones necesarias de egresos. </b>
 * 
 * @author HIPERION
 * @version 1.0,23/02/2016
 * @since JDK1.6
 */
@Local
public interface EgresoService {

	/**
	 * 
	 * <b> Permite guardar un regitro de egreso en la base de datos. </b>
	 * <p>
	 * [Author: HIPERION, Date: 23/02/2016]
	 * </p>
	 * 
	 * @param egreso
	 * @throws HiperionException
	 */
	public void guardarEgreso(Egreso egreso) throws HiperionException;
}
