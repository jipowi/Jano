/*
 * Copyright 2014 JIPOVI Solutions - ECUADOR 
 * Todos los derechos reservados
 */
package ec.com.uce.jano.dao.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import ec.com.uce.jano.dao.EgresoDao;
import ec.com.uce.jano.entities.Egreso;

/**
 * <b>Permite implementar las operaciones de la interfaz local</b>
 * 
 * @author Paul Jimenez
 * @version 1.0,Dec 10, 2014
 * @since JDK1.6
 */
@Stateless
public class EgresoDaoImpl extends GenericDAOImpl<Egreso, Long> implements EgresoDao {

	Logger log = Logger.getLogger(EgresoDaoImpl.class);

	@PersistenceContext(unitName = "sgs_pu")
	protected EntityManager em;

	

}
