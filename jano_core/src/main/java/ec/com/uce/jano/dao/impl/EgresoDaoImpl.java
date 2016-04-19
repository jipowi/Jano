/*
 * Copyright 2014 JIPOVI Solutions - ECUADOR 
 * Todos los derechos reservados
 */
package ec.com.uce.jano.dao.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import ec.com.uce.jano.comun.HiperionException;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.com.uce.jano.dao.EgresoDao#buscarEgresos(java.lang.String, java.lang.Integer)
	 */	
	@Override
	public Egreso buscarEgresos(String periodo, Long idAfectacion) throws HiperionException {
		try {
			Query query = em.createNamedQuery("Egreso.findEgresos");
			query.setParameter("periodo", periodo);
			query.setParameter("idAfectacion", idAfectacion);
			Egreso egreso = (Egreso) query.getSingleResult();

			return egreso;

		} catch (Exception ex) {
			log.error("Error: No se pudo realizar la Consulta --> Egreso.findEgresos", ex);
			throw new HiperionException(ex);
			
		}
	}

}
