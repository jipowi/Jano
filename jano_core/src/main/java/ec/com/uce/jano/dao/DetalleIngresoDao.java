/*
 * Copyright 2014 JIPOVI Solutions - ECUADOR 
 * Todos los derechos reservados
 */
package ec.com.uce.jano.dao;

import java.util.List;

import javax.ejb.Local;

import ec.com.uce.jano.comun.HiperionException;
import ec.com.uce.jano.entities.DetalleIngreso;

/**
 * 
 * <b> Interfaz local de la tabla DetalleIngreso para realizar las operaciones necesarias. </b>
 * 
 * @author U
 * @version 1.0,26/04/2016
 * @since JDK1.6
 */
@Local
public interface DetalleIngresoDao extends GenericDAO<DetalleIngreso, Long> {

	/**
	 * 
	 * <b> Permite buscar los detalles de un ingreso. </b>
	 * <p>
	 * [Author: Paul Jimenez, Date: 13/04/2016]
	 * </p>
	 * 
	 * @param idIngreso
	 * @return
	 * @throws HiperionException
	 */
	public List<DetalleIngreso> buscarIngreso(Long idIngreso) throws HiperionException;

}
