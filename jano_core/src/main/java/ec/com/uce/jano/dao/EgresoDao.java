/*
 * Copyright 2014 JIPOVI Solutions - ECUADOR 
 * Todos los derechos reservados
 */
package ec.com.uce.jano.dao;

import javax.ejb.Local;

import ec.com.uce.jano.comun.HiperionException;
import ec.com.uce.jano.entities.Egreso;

/**
 * 
 * <b> Interfaz local de la tabla Egreso para realizar las operaciones necesarias</b>
 * 
 * @author Dario Vinueza
 * @version 1.0,28/11/2013
 * @since JDK1.6
 */
@Local
public interface EgresoDao extends GenericDAO<Egreso, Long> {

	/**
	 * 
	 * <b> Permite listar los egresos que se encuentran en la base bajo los siguientes filtros. </b>
	 * <p>
	 * [Author: Paul Jimenez, Date: 28/03/2016]
	 * </p>
	 * 
	 * @param periodo
	 * @param idAfectacion
	 * @return
	 * @throws HiperionException
	 */
	public Egreso buscarEgresos(String periodo, Long idAfectacion) throws HiperionException;

}
