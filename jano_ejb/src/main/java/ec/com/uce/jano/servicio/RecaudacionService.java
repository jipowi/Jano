/**
 * 
 */
package ec.com.uce.jano.servicio;

import java.util.List;

import javax.ejb.Local;

import ec.com.uce.jano.comun.HiperionException;
import ec.com.uce.jano.entities.Gasto;
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
	 * [Author: Paul Jimenez, Date: 07/06/2016]
	 * </p>
	 * 
	 * @param recaudacion
	 * @throws HiperionException
	 */
	public void guardarRecaudacion(Recaudacion recaudacion) throws HiperionException;

	/**
	 * 
	 * <b> Permite guardar un registro de comprobantes de gastos en la tabla Gasto. </b>
	 * <p>
	 * [Author: Paul Jimenez, Date: 18/06/2016]
	 * </p>
	 * 
	 * @param gasto
	 * @throws HiperionException
	 */
	public void guardarGastos(Gasto gasto) throws HiperionException;

	/**
	 * 
	 * <b> Permite obtener las recaudaciones registradas. </b>
	 * <p>
	 * [Author: Paul Jimenez, Date: 10/06/2016]
	 * </p>
	 * 
	 * @param idAfectacion
	 * @return
	 * @throws HiperionException
	 */
	public List<Recaudacion> obtenerRecaudaciones(Long idAfectacion) throws HiperionException;

	/**
	 * 
	 * <b> Permite obtener todas las recaudaciones ingresadas. </b>
	 * <p>
	 * [Author: kruger, Date: 22/06/2016]
	 * </p>
	 * 
	 * @return
	 * @throws HiperionException
	 */
	public List<Recaudacion> obtenerRecaudaciones() throws HiperionException;

	/**
	 * 
	 * <b> Permite obtener las gastos registrados. </b>
	 * <p>
	 * [Author: kruger, Date: 18/06/2016]
	 * </p>
	 * 
	 * @param idAfectacion
	 * @return
	 * @throws HiperionException
	 */
	public List<Gasto> obtenerGastos(Long idAfectacion) throws HiperionException;

	/**
	 * 
	 * <b> Permite obtener todos los registros de gastos. </b>
	 * <p>
	 * [Author: kruger, Date: 22/06/2016]
	 * </p>
	 * 
	 * @return
	 * @throws HiperionException
	 */
	public List<Gasto> obtenerGastos() throws HiperionException;
}
