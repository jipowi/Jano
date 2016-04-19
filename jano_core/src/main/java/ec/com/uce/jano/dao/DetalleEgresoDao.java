/*
 * Copyright 2014 JIPOVI Solutions - ECUADOR 
 * Todos los derechos reservados
 */
package ec.com.uce.jano.dao;

import java.util.List;

import javax.ejb.Local;

import ec.com.uce.jano.comun.HiperionException;
import ec.com.uce.jano.entities.DetalleEgreso;

/**
 * 
 * <b> Interfaz local de la tabla DetalleEgreso para realizar las operaciones necesarias</b>
 * 
 * @author Dario Vinueza
 * @version 1.0,28/11/2013
 * @since JDK1.6
 */
@Local
public interface DetalleEgresoDao extends GenericDAO<DetalleEgreso, Long> {

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

}
