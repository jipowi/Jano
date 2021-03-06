/*
 * Copyright 2014 JIPOVI Solutions - ECUADOR 
 * Todos los derechos reservados
 */
package ec.com.uce.jano.dao.impl;

import javax.ejb.Stateless;

import ec.com.uce.jano.dao.CatalogoDao;
import ec.com.uce.jano.entities.Catalogo;

/**
 * 
 * <b> Permite implementar las operaciones de la tabla Catalogo </b>
 * 
 * @author Franklin
 * @version 1.0,21/12/2013
 * @since JDK1.6
 */
@Stateless
public class CatalogoDaoImpl extends GenericDAOImpl<Catalogo, Long> implements CatalogoDao {

}
