/*
 * Copyright 2014 JIPOVI Solutions - ECUADOR 
 * Todos los derechos reservados
 */
package ec.com.uce.jano.dao;

import javax.ejb.Local;

import ec.com.uce.jano.comun.HiperionException;
import ec.com.uce.jano.entities.Ingreso;

/**
 * 
 * <b>
 * Interfaz local de la tabla Ingreso para realizar las operaciones necesarias.
 * </b>
 *  
 * @author Paul Jimenez
 * @version 1.0,26/04/2016
 * @since JDK1.6
 */
@Local
public interface IngresoDao extends GenericDAO<Ingreso, Long> {

	/**
	 * 
	 * <b> Permite listar los ingresos que se encuentran en la base bajo los siguientes filtros. </b>
	 * <p>
	 * [Author: Paul Jimenez, Date: 28/03/2016]
	 * </p>
	 * 
	 * @param periodo
	 * @param idAfectacion
	 * @return
	 * @throws HiperionException
	 */
	public Ingreso buscarIngresos(String periodo, Long idAfectacion) throws HiperionException;

}
