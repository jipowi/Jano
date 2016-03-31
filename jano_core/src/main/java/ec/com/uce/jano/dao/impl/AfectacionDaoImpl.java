/*
 * Copyright 2014 JIPOVI Solutions - ECUADOR 
 * Todos los derechos reservados
 */
package ec.com.uce.jano.dao.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import ec.com.uce.jano.dao.AfectacionDao;
import ec.com.uce.jano.entities.Afectacion;

/**
 * 
 * <b> Permite implementar las operaciones de la interfaz local. </b>
 * 
 * @author HIPERION
 * @version 1.0,24/02/2016
 * @since JDK1.6
 */
@Stateless
public class AfectacionDaoImpl extends GenericDAOImpl<Afectacion, Long> implements AfectacionDao {

	Logger log = Logger.getLogger(AfectacionDaoImpl.class);

	@PersistenceContext(unitName = "sgs_pu")
	protected EntityManager em;

}
