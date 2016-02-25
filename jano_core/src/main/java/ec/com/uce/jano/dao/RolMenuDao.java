/*
 * Copyright 2014 JIPOVI Solutions - ECUADOR 
 * Todos los derechos reservados
 */
package ec.com.uce.jano.dao;

import javax.ejb.Local;

import ec.com.uce.jano.entities.RolMenu;

/**
 * <b> Interfaz local para operaciones sobre la tabla de rolMenu </b>
 * 
 * @author Paul Jimenez
 * @version 1.0,Dec 10, 2014
 * @since JDK1.6
 */
@Local
public interface RolMenuDao extends GenericDAO<RolMenu, Long> {

}
