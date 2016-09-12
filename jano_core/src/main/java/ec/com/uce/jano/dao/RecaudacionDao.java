/*
 * Copyright 2016 JIPOVI Solutions - ECUADOR 
 * Todos los derechos reservados
 */
package ec.com.uce.jano.dao;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import ec.com.uce.jano.comun.HiperionException;
import ec.com.uce.jano.entities.Recaudacion;

/**
 * 
 * <b> Interfaz local de la tabla Recaudacion para realizar las operaciones necesarias. </b>
 * 
 * @author Paul Jimenez
 * @version 1.0,24/02/2016
 * @since JDK1.6
 */
@Local
public interface RecaudacionDao extends GenericDAO<Recaudacion, Long> {

	/**
	 * 
	 * <b> Incluir aqui­ la descripcion del metodo. </b>
	 * <p>
	 * [Author: kruger, Date: 10/06/2016]
	 * </p>
	 * 
	 * @param idAfectacion
	 * @return
	 * @throws HiperionException
	 */
	public List<Recaudacion> obtenerRecaudaciones(Long idAfectacion) throws HiperionException;

	public List<Recaudacion> buscarRecaudaciones(String periodo, Date fechaInicio, Date fechaFin) throws HiperionException;
}
