/**
 * 
 */
package ec.com.uce.jano.servicio;

import java.util.List;

import javax.ejb.Local;

import ec.com.uce.jano.comun.HiperionException;
import ec.com.uce.jano.entities.DetalleEgreso;
import ec.com.uce.jano.entities.DetalleIngreso;
import ec.com.uce.jano.entities.Egreso;
import ec.com.uce.jano.entities.Ingreso;
import ec.com.uce.jano.entities.Partida;

/**
 * <b> Interface local para realizar las operaciones necesarias de egresos. </b>
 * 
 * @author Paul Jimenez
 * @version 1.0,23/02/2016
 * @since JDK1.6
 */
@Local
public interface EgresoService {

	/**
	 * 
	 * <b> Permite guardar un regitro de egreso en la base de datos. </b>
	 * <p>
	 * [Author: Paul Jimenez, Date: 12/04/2016]
	 * </p>
	 * 
	 * @param egreso
	 * @param detalles
	 * @throws HiperionException
	 */
	public void guardarEgreso(Egreso egreso, List<DetalleEgreso> detalles, boolean save) throws HiperionException;

	/**
	 * 
	 * <b> Permite guardar un registro de ingreso en la base de datos. </b>
	 * <p>
	 * [Author: Paul Jimenez, Date: 26/04/2016]
	 * </p>
	 * 
	 * @param ingreso
	 * @param detalles
	 * @param save
	 * @throws HiperionException
	 */
	public void guardarIngreso(Ingreso ingreso, List<DetalleIngreso> detalles, boolean save) throws HiperionException;

	/**
	 * 
	 * <b> Permite guardar un registro en la tabla Partida. </b>
	 * <p>
	 * [Author: HIPERION, Date: 24/02/2016]
	 * </p>
	 * 
	 * @param partida
	 * @throws HiperionException
	 */
	public void guardarPartida(Partida partida) throws HiperionException;

	/**
	 * 
	 * <b> Permite obtener todas las partidas para egresos. </b>
	 * <p>
	 * [Author: HIPERION, Date: 25/02/2016]
	 * </p>
	 * 
	 * @param tipoPartida
	 * @return
	 * @throws HiperionException
	 */
	public List<Partida> obtenerPartidas(String tipoPartida) throws HiperionException;

	/**
	 * 
	 * <b> Permite obtener la partida por medio del id. </b>
	 * <p>
	 * [Author: HIPERION, Date: 25/02/2016]
	 * </p>
	 * 
	 * @param idPartida
	 * @return
	 * @throws HiperionException
	 */
	public Partida obtenerPartidaById(Long idPartida) throws HiperionException;

	/**
	 * 
	 * <b> Permite listar los egresos que se encuentran en la base bajo los siguientes filtros. </b>
	 * <p>
	 * [Author: Paul Jimenez, Date: 28/03/2016]
	 * </p>
	 * 
	 * @param idAfectacion
	 * @return
	 * @throws HiperionException
	 */
	public Egreso buscarEgresos(String periodo, Long idAfectacion) throws HiperionException;

	/**
	 * 
	 * <b> Permite listar los ingresos que se encuentran en la base bajo los siguientes filtros. </b>
	 * <p>
	 * [Author: Paul Jimenez, Date: 26/04/2016]
	 * </p>
	 * 
	 * @param periodo
	 * @param idAfectacion
	 * @return
	 * @throws HiperionException
	 */
	public Ingreso buscarIngresos(String periodo, Long idAfectacion) throws HiperionException;

	/**
	 * 
	 * <b> Permite buscar los detalles de un egreso. </b>
	 * <p>
	 * [Author: Paul Jimenez, Date: 13/04/2016]
	 * </p>
	 * 
	 * @param idEgreso
	 * @return
	 * @throws HiperionException
	 */
	public List<DetalleEgreso> buscarEgresos(Long idEgreso) throws HiperionException;

	/**
	 * 
	 * <b> Permite buscar los detalles de un ingreso. </b>
	 * <p>
	 * [Author: Paul Jimenez, Date: 26/04/2016]
	 * </p>
	 * 
	 * @param idIngreso
	 * @return
	 * @throws HiperionException
	 */
	public List<DetalleIngreso> buscarIngresos(Long idIngreso) throws HiperionException;

	/**
	 * 
	 * <b> Permite actualizar el detalle de egresos. </b>
	 * <p>
	 * [Author: kruger, Date: Oct 3, 2016]
	 * </p>
	 * 
	 * @param detalleEgreso
	 * @throws HiperionException
	 */
	public void editarDetalleEgreso(DetalleEgreso detalleEgreso) throws HiperionException;

	/**
	 * 
	 * <b> Permite editar un egreso en la based de datos. </b>
	 * <p>
	 * [Author: kruger, Date: Oct 7, 2016]
	 * </p>
	 * 
	 * @param egreso
	 * @throws HiperionException
	 */
	public void editarEgreso(Egreso egreso) throws HiperionException;

	/**
	 * 
	 * <b> Permite editar un ingreso en la base de datos. </b>
	 * <p>
	 * [Author: kruger, Date: Oct 7, 2016]
	 * </p>
	 * 
	 * @param ingreso
	 * @throws HiperionException
	 */
	public void editarIngreso(Ingreso ingreso) throws HiperionException;

	/**
	 * 
	 * <b> Permite eliminar un egreso de la base de datos. </b>
	 * <p>
	 * [Author: kruger, Date: Oct 7, 2016]
	 * </p>
	 * 
	 * @param egreso
	 * @throws HiperionException
	 */
	public void eliminarEgreso(Egreso egreso) throws HiperionException;

	/**
	 * 
	 * <b> Permite eliminar un registro de ingreso de la base de datos. </b>
	 * <p>
	 * [Author: kruger, Date: Oct 7, 2016]
	 * </p>
	 * 
	 * @param ingreso
	 * @throws HiperionException
	 */
	public void eliminarIngreso(Ingreso ingreso) throws HiperionException;

	/**
	 * 
	 * <b> permite editar un registro de la base de datos. </b>
	 * <p>
	 * [Author: kruger, Date: Oct 7, 2016]
	 * </p>
	 * 
	 * @param partida
	 * @throws HiperionException
	 */
	public void editarPartida(Partida partida) throws HiperionException;

	/**
	 * 
	 * <b> permite eliminar un registro de la base de datos. </b>
	 * <p>
	 * [Author: kruger, Date: Oct 7, 2016]
	 * </p>
	 * 
	 * @param partida
	 * @throws HiperionException
	 */
	public void eliminarPartida(Partida partida) throws HiperionException;

}
