/**
 * 
 */
package ec.com.uce.jano.servicio;

import java.util.List;

import javax.ejb.Local;

import ec.com.uce.jano.comun.HiperionException;
import ec.com.uce.jano.entities.Egreso;
import ec.com.uce.jano.entities.Partida;

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
	public List<Partida> obtenerPartidasEgreso(String tipoPartida) throws HiperionException;

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

}
