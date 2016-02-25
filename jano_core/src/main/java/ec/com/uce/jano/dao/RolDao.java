/*
 * Copyright 2014 JIPOVI Solutions - ECUADOR 
 * Todos los derechos reservados
 */
package ec.com.uce.jano.dao;

import java.util.List;

import javax.ejb.Local;

import ec.com.uce.jano.comun.HiperionException;
import ec.com.uce.jano.entities.Rol;
import ec.com.uce.jano.entities.Usuario;

/**
 * <b> Interfaz local para operaciones sobre la tabla de roles </b>
 * 
 * @author Paul Jimenez
 * @version 1.0,Dec 10, 2014
 * @since JDK1.6
 */
@Local
public interface RolDao extends GenericDAO<Rol, Long> {

	/**
	 * 
	 * <b> Permite consultar los roles que tiene un usuario </b>
	 * <p>
	 * [Author: Paul Jimenez, Date: Dec 10, 2014]
	 * </p>
	 * 
	 * @param usuario
	 * @return
	 * @throws HiperionException
	 */
	public List<Rol> consultarRolByUsuario(Usuario usuario) throws HiperionException;
}
