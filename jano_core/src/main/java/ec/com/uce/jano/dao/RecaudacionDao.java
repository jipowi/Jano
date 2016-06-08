/*
 * Copyright 2016 JIPOVI Solutions - ECUADOR 
 * Todos los derechos reservados
 */
package ec.com.uce.jano.dao;

import javax.ejb.Local;

import ec.com.uce.jano.entities.Recaudacion;

/**
 * 
 * <b> Interfaz local de la tabla Recaudacion para realizar las operaciones necesarias. </b>
 * 
 * @author HIPERION
 * @version 1.0,24/02/2016
 * @since JDK1.6
 */
@Local
public interface RecaudacionDao extends GenericDAO<Recaudacion, Long> {

}
