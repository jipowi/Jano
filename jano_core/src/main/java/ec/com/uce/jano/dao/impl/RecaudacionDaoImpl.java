/*
 * Copyright 2016 JIPOVI Solutions - ECUADOR 
 * Todos los derechos reservados
 */
package ec.com.uce.jano.dao.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import ec.com.uce.jano.dao.RecaudacionDao;
import ec.com.uce.jano.entities.Recaudacion;

/**
 * 
 * <b> Permite implementar las operaciones de la interfaz local. </b>
 * 
 * @author HIPERION
 * @version 1.0,24/02/2016
 * @since JDK1.6
 */
@Stateless
public class RecaudacionDaoImpl extends GenericDAOImpl<Recaudacion, Long> implements RecaudacionDao {

	Logger log = Logger.getLogger(RecaudacionDaoImpl.class);

	@PersistenceContext(unitName = "sgs_pu")
	protected EntityManager em;

}
