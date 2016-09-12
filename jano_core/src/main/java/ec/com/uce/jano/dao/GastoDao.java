/*
 * Copyright 2016 JIPOVI Solutions - ECUADOR 
 * Todos los derechos reservados
 */
package ec.com.uce.jano.dao;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import ec.com.uce.jano.comun.HiperionException;
import ec.com.uce.jano.entities.Gasto;

/**
 * 
 * <b> Interfaz local de la tabla Recaudacion para realizar las operaciones necesarias. </b>
 * 
 * @author Paul Jimenez
 * @version 1.0,24/02/2016
 * @since JDK1.6
 */
@Local
public interface GastoDao extends GenericDAO<Gasto, Long> {

	/**
	 * 
	 * <b> Permite obtener los gatos regitradas en la tabla. </b>
	 * <p>
	 * [Author: kruger, Date: 10/06/2016]
	 * </p>
	 * 
	 * @param idAfectacion
	 * @return
	 * @throws HiperionException
	 */
	public List<Gasto> obtenerGastos(Long idAfectacion) throws HiperionException;

	/**
	 * 
	 * <b> Permite buscar los compromisos por los siguientes criterios ingresados. </b>
	 * <p>
	 * [Author: kruger, Date: 07/09/2016]
	 * </p>
	 * 
	 * @param periodo
	 * @param beneficiario
	 * @param fechaInicio
	 * @param fechaFin
	 * @return
	 * @throws HiperionException
	 */
	public List<Gasto> buscarGastos(String periodo, String beneficiario, Date fechaInicio, Date fechaFin) throws HiperionException;

}
