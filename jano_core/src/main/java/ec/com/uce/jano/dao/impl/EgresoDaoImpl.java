/*
 * Copyright 2014 JIPOVI Solutions - ECUADOR 
 * Todos los derechos reservados
 */
package ec.com.uce.jano.dao.impl;

import java.util.ArrayList;
import java.util.List;

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
	@SuppressWarnings("unchecked")
	@Override
	public Egreso buscarEgresos(String periodo, Long idAfectacion) throws HiperionException {
		try {
			List<Egreso> egresos = new ArrayList<>();
			Query query = em.createNamedQuery("Egreso.findEgresos");
			query.setParameter("periodo", periodo);
			query.setParameter("idAfectacion", idAfectacion);
			
			egresos = query.getResultList();
			
			if (egresos.isEmpty()) {
				return null;
			} else {
				Egreso egreso = (Egreso) query.getResultList().get(0);

				return egreso;
			}
			
		} catch (Exception ex) {
			log.error("Error: No se pudo realizar la Consulta --> Egreso.findEgresos", ex);
			throw new HiperionException(ex);

		}
	}

}
