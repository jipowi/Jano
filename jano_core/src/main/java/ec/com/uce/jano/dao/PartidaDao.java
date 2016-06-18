/*
 * Copyright 2014 JIPOVI Solutions - ECUADOR 
 * Todos los derechos reservados
 */
package ec.com.uce.jano.dao;

import java.util.List;

import javax.ejb.Local;

import ec.com.uce.jano.comun.HiperionException;
import ec.com.uce.jano.entities.Partida;

/**
 * 
 * <b> Interfaz local de la tabla Partida para realizar las operaciones necesarias. </b>
 * 
 * @author Paul Jimenez
 * @version 1.0,24/02/2016
 * @since JDK1.6
 */
@Local
public interface PartidaDao extends GenericDAO<Partida, Long> {

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
	

}
