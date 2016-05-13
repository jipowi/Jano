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
import ec.com.uce.jano.dao.IngresoDao;
import ec.com.uce.jano.entities.Ingreso;

/**
 * <b>Permite implementar las operaciones de la interfaz local</b>
 * 
 * @author Paul Jimenez
 * @version 1.0,Dec 10, 2014
 * @since JDK1.6
 */
@Stateless
public class IngresoDaoImpl extends GenericDAOImpl<Ingreso, Long> implements IngresoDao {

	Logger log = Logger.getLogger(IngresoDaoImpl.class);

	@PersistenceContext(unitName = "sgs_pu")
	protected EntityManager em;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.com.uce.jano.dao.IngresoDao#buscarIngresos(java.lang.String, java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Ingreso buscarIngresos(String periodo, Long idAfectacion) throws HiperionException {
		try {

			List<Ingreso> ingresos = new ArrayList<>();
			Query query = em.createNamedQuery("Ingreso.findIngresos");
			query.setParameter("periodo", periodo);
			query.setParameter("idAfectacion", idAfectacion);

			ingresos = query.getResultList();

			if (ingresos.isEmpty()) {
				return null;
			} else {
				Ingreso ingreso = (Ingreso) query.getResultList().get(0);

				return ingreso;
			}

		} catch (Exception ex) {
			log.error("Error: No se pudo realizar la Consulta --> Ingreso.findIngresos", ex);
			throw new HiperionException(ex);

		}
	}

}
