/*
 * Copyright 2014 JIPOVI Solutions - ECUADOR 
 * Todos los derechos reservados
 */
package ec.com.uce.jano.dao;

import javax.ejb.Local;

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

}
