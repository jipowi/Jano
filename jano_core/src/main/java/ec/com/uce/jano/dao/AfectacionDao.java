/*
 * Copyright 2014 JIPOVI Solutions - ECUADOR 
 * Todos los derechos reservados
 */
package ec.com.uce.jano.dao;

import java.util.List;

import javax.ejb.Local;

import ec.com.uce.jano.comun.HiperionException;
import ec.com.uce.jano.entities.Afectacion;

/**
 * 
 * <b> Interfaz local de la tabla Afectacion para realizar las operaciones necesarias. </b>
 * 
 * @author HIPERION
 * @version 1.0,24/02/2016
 * @since JDK1.6
 */
@Local
public interface AfectacionDao extends GenericDAO<Afectacion, Long> {

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
	public List<Afectacion> obtenerDependencias(Integer idFacultad) throws HiperionException;

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
	public List<Afectacion> obtenerDepartamentos(Integer idFacultad, Integer idDependencia) throws HiperionException;

}
