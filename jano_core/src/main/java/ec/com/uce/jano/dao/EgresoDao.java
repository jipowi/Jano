/*
 * Copyright 2014 JIPOVI Solutions - ECUADOR 
 * Todos los derechos reservados
 */
package ec.com.uce.jano.dao;

import javax.ejb.Local;

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

}
