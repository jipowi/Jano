/*
 * Copyright 2014 JIPOVI Solutions - ECUADOR 
 * Todos los derechos reservados
 */
package ec.com.avila.hiperion.dao;

import javax.ejb.Local;

import ec.com.avila.hiperion.emision.entities.DetalleEgreso;

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

}
