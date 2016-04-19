/*
 * Copyright 2014 JIPOVI Solutions - ECUADOR 
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
import ec.com.uce.jano.dao.DetalleEgresoDao;
import ec.com.uce.jano.entities.DetalleEgreso;

/**
 * <b>Permite implementar las operaciones de la interfaz local</b>
 * 
 * @author Paul Jimenez
 * @version 1.0,Dec 10, 2014
 * @since JDK1.6
 */
@Stateless
public class DetalleEgresoDaoImpl extends GenericDAOImpl<DetalleEgreso, Long> implements DetalleEgresoDao {

	Logger log = Logger.getLogger(DetalleEgresoDaoImpl.class);

	@PersistenceContext(unitName = "sgs_pu")
	protected EntityManager em;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.com.uce.jano.dao.DetalleEgresoDao#buscarEgresos(java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DetalleEgreso> buscarEgresos(Long idEgreso) throws HiperionException {
		try {
			Query query = em.createNamedQuery("DetalleEgreso.findEgreso");
			query.setParameter("idEgreso", idEgreso);
			List<DetalleEgreso> egresos = query.getResultList();

			return egresos;

		} catch (Exception ex) {
			log.error("Error: No se pudo realizar la Consulta --> Egreso.findEgresos", ex);
			throw new HiperionException(ex);
		}
	}

}
