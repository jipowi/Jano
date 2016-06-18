/*
 * Copyright 2016 JIPOVI Solutions - ECUADOR 
 * Todos los derechos reservados
 */
package ec.com.uce.jano.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import ec.com.uce.jano.comun.HiperionException;
import ec.com.uce.jano.dao.GastoDao;
import ec.com.uce.jano.entities.Gasto;

/**
 * 
 * <b> Permite implementar las operaciones de la interfaz local. </b>
 * 
 * @author Paul Jimenez
 * @version 1.0,24/02/2016
 * @since JDK1.6
 */
@Stateless
public class GastoDaoImpl extends GenericDAOImpl<Gasto, Long> implements GastoDao {

	Logger log = Logger.getLogger(GastoDaoImpl.class);

	@PersistenceContext(unitName = "sgs_pu")
	protected EntityManager em;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.com.uce.jano.dao.GastoDao#obtenerGastos(java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Gasto> obtenerGastos(Long idAfectacion) throws HiperionException {
		try {
			Query query = em.createNamedQuery("Gastos.reporte");
			query.setParameter("afectacion", idAfectacion);

			List<Gasto> gastos = query.getResultList();

			return gastos;

		} catch (Exception ex) {
			log.error("Error: No se pudo realizar la Consulta --> Gastos.reporte", ex);
			throw new HiperionException(ex);
		}
	}

}