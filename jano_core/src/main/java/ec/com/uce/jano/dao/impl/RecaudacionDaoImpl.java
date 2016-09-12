/*
 * Copyright 2016 JIPOVI Solutions - ECUADOR 
 * Todos los derechos reservados
 */
package ec.com.uce.jano.dao.impl;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import ec.com.uce.jano.comun.HiperionException;
import ec.com.uce.jano.dao.RecaudacionDao;
import ec.com.uce.jano.entities.Recaudacion;

/**
 * 
 * <b> Permite implementar las operaciones de la interfaz local. </b>
 * 
 * @author Paul Jimenez
 * @version 1.0,24/02/2016
 * @since JDK1.6
 */
@Stateless
public class RecaudacionDaoImpl extends GenericDAOImpl<Recaudacion, Long> implements RecaudacionDao {

	Logger log = Logger.getLogger(RecaudacionDaoImpl.class);

	@PersistenceContext(unitName = "sgs_pu")
	protected EntityManager em;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.com.uce.jano.dao.RecaudacionDao#obtenerRecaudaciones(java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Recaudacion> obtenerRecaudaciones(Long idAfectacion) throws HiperionException {

		try {
			Query query = em.createNamedQuery("Recaudacion.reporte");
			query.setParameter("afectacion", idAfectacion);

			List<Recaudacion> recaudaciones = query.getResultList();

			return recaudaciones;

		} catch (Exception ex) {
			log.error("Error: No se pudo realizar la Consulta --> Recaudacion.reporte", ex);
			throw new HiperionException(ex);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.com.uce.jano.dao.RecaudacionDao#buscarRecaudaciones(java.lang.String, java.util.Date, java.util.Date)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Recaudacion> buscarRecaudaciones(String periodo, Date fechaInicio, Date fechaFin) throws HiperionException {
		try {

			Query query = em.createNamedQuery("Recaudacion.buscar");
			query.setParameter("periodo", periodo);
			query.setParameter("fechaInicio", fechaInicio);
			query.setParameter("fechaFin", fechaFin);

			List<Recaudacion> recaudaciones = query.getResultList();

			return recaudaciones;

		} catch (Exception ex) {
			log.error("Error: No se pudo realizar la Consulta --> Recaudacion.buscar", ex);
			throw new HiperionException(ex);
		}
	}

}
