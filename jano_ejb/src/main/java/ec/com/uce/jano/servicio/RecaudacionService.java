/**
 * 
 */
package ec.com.uce.jano.servicio;

import java.util.List;

import javax.ejb.Local;



import ec.com.uce.jano.comun.HiperionException;
import ec.com.uce.jano.entities.Recaudacion;

/**
 * <b> Interfaz que permite realizar las opercaiones necesarias sobre las recaudaciones. </b>
 * 
 * @author Paul Jimenez
 * @version 1.0,31/03/2016
 * @since JDK1.6
 */
@Local
public interface RecaudacionService {

	/**
	 * 
	 * <b> Permite guardar un registro de recaudacion en la base de datos. </b>
	 * <p>
	 * [Author: kruger, Date: 07/06/2016]
	 * </p>
	 * 
	 * @param recaudacion
	 * @throws HiperionException
	 */
	public void guardarRecaudacion(Recaudacion recaudacion) throws HiperionException;
	
	/**
	 * 
	 * <b>
	 * Incluir aqui­ la descripcion del metodo.
	 * </b>
	 * <p>[Author: kruger, Date: 10/06/2016]</p>
	 *
	 * @param idAfectacion
	 * @return
	 * @throws HiperionException
	 */
	public List<Recaudacion> obtenerRecaudaciones(Long idAfectacion)throws HiperionException;
}
