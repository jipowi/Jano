/**
 * 
 */
package ec.com.uce.jano.servicio;

import java.util.List;

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
	public void guardarAfectacion(Afectacion afectacion) throws HiperionException;

	/**
	 * 
	 * <b> Permite listar las facultades ingresadas en la tabla afectaciones. </b>
	 * <p>
	 * [Author: Paul Jimenez, Date: 31/03/2016]
	 * </p>
	 * 
	 * @return
	 * @throws HiperionException
	 */
	public List<Afectacion> obtenerFacultades() throws HiperionException;

	/**
	 * 
	 * <b> Permite listar las dependencias ingresadas con codigo de facultad. </b>
	 * <p>
	 * [Author: Paul Jimenez, Date: 01/04/2016]
	 * </p>
	 * 
	 * @param idFacultad
	 * @return
	 * @throws HiperionException
	 */
	public List<Afectacion> obtenerDependencias(Long idFacultad) throws HiperionException;

	/**
	 * 
	 * <b> Permite listar los departamentos ingresados que dependen de una facultad y dependencia. </b>
	 * <p>
	 * [Author: Paul Jimenez, Date: 02/04/2016]
	 * </p>
	 * 
	 * @param idFacultad
	 * @param idDependencia
	 * @return
	 * @throws HiperionException
	 */
	public List<Afectacion> obtenerDepartamentos(Long idFacultad, Long idDependencia) throws HiperionException;

	/**
	 * 
	 * <b> Permite obtener la afectacion por medio del ID. </b>
	 * <p>
	 * [Author: kruger, Date: Sep 29, 2016]
	 * </p>
	 * 
	 * @param idAfectacion
	 * @return
	 * @throws HiperionException
	 */
	public Afectacion obetenerAfectacionById(Long idAfectacion) throws HiperionException;

}
