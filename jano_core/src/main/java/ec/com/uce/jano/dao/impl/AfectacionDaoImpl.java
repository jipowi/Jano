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
import ec.com.uce.jano.dao.AfectacionDao;
import ec.com.uce.jano.entities.Afectacion;

/**
 * 
 * <b> Permite implementar las operaciones de la interfaz local. </b>
 * 
 * @author Paul Jimenez
 * @version 1.0,24/02/2016
 * @since JDK1.6
 */
@Stateless
public class AfectacionDaoImpl extends GenericDAOImpl<Afectacion, Long> implements AfectacionDao {

	Logger log = Logger.getLogger(AfectacionDaoImpl.class);

	@PersistenceContext(unitName = "sgs_pu")
	protected EntityManager em;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.com.uce.jano.dao.AfectacionDao#obtenerFacultades()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Afectacion> obtenerFacultades() throws HiperionException {
		try {
			Query query = em.createNamedQuery("Afectacion.findFacultad");

			List<Afectacion> afectaciones = query.getResultList();

			return afectaciones;

		} catch (Exception ex) {
			log.error("Error: No se pudo realizar la Consulta --> Afectacion.findFacultad", ex);
			throw new HiperionException(ex);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.com.uce.jano.dao.AfectacionDao#obtenerDependencias(java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Afectacion> obtenerDependencias(Integer idFacultad) throws HiperionException {
		try {
			Query query = em.createNamedQuery("Afectacion.findDependencia");
			query.setParameter("idFacultad", idFacultad);

			List<Afectacion> afectaciones = query.getResultList();

			return afectaciones;

		} catch (Exception ex) {
			log.error("Error: No se pudo realizar la Consulta --> Afectacion.findDependencia", ex);
			throw new HiperionException(ex);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.com.uce.jano.dao.AfectacionDao#obtenerDepartamentos(java.lang.Integer, java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Afectacion> obtenerDepartamentos(Integer idFacultad, Integer idDependencia) throws HiperionException {
		try {
			Query query = em.createNamedQuery("Afectacion.findDepartamento");
			query.setParameter("idFacultad", idFacultad);
			query.setParameter("idDependencia", idDependencia);

			List<Afectacion> depatamentos = query.getResultList();

			return depatamentos;

		} catch (Exception ex) {
			log.error("Error: No se pudo realizar la Consulta --> Afectacion.findDepartamento", ex);
			throw new HiperionException(ex);
		}
	}

}
