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
			query.setParameter("beneficiario", idAfectacion);

			List<Gasto> gastos = query.getResultList();

			return gastos;

		} catch (Exception ex) {
			log.error("Error: No se pudo realizar la Consulta --> Gastos.reporte", ex);
			throw new HiperionException(ex);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.com.uce.jano.dao.GastoDao#buscarGastos(java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Gasto> buscarGastosAll(String periodo, String beneficiario, Date fechaInicio, Date fechaFin) throws HiperionException {
		try {
			Query query = em.createNamedQuery("Gastos.findByAll");
			query.setParameter("periodo", periodo);
			query.setParameter("beneficiario", "%" + beneficiario + "%");
			query.setParameter("fechaInicio", fechaInicio);
			query.setParameter("fechaFin", fechaFin);

			List<Gasto> gastos = query.getResultList();

			return gastos;

		} catch (Exception ex) {
			log.error("Error: No se pudo realizar la Consulta --> Gastos.findByAll", ex);
			throw new HiperionException(ex);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.com.uce.jano.dao.GastoDao#buscaGastosByComprobante(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Gasto> buscaGastosByComprobante(String comprobante) throws HiperionException {
		try {
			Query query = em.createNamedQuery("Gastos.findByComprobante");
			query.setParameter("comprobante", comprobante);

			List<Gasto> gastos = query.getResultList();

			return gastos;

		} catch (Exception ex) {
			log.error("Error: No se pudo realizar la Consulta --> Gastos.findByComprobante", ex);
			throw new HiperionException(ex);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.com.uce.jano.dao.GastoDao#buscarGastosByEstado(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Gasto> buscarGastosByEstado(String estado) throws HiperionException {
		try {
			Query query = em.createNamedQuery("Gastos.findByEstado");
			query.setParameter("estado", estado);

			List<Gasto> gastos = query.getResultList();

			return gastos;

		} catch (Exception ex) {
			log.error("Error: No se pudo realizar la Consulta --> Gastos.findByEstado", ex);
			throw new HiperionException(ex);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.com.uce.jano.dao.GastoDao#buscarGastosByPeriodo(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Gasto> buscarGastosByPeriodo(String periodo) throws HiperionException {
		try {
			Query query = em.createNamedQuery("Gastos.findByPeriodo");
			query.setParameter("periodo", periodo);

			List<Gasto> gastos = query.getResultList();

			return gastos;

		} catch (Exception ex) {
			log.error("Error: No se pudo realizar la Consulta --> Gastos.findByPeriodo", ex);
			throw new HiperionException(ex);
		}
	}

	/* (non-Javadoc)
	 * @see ec.com.uce.jano.dao.GastoDao#buscaGastosByBeneficiario(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Gasto> buscaGastosByBeneficiario(String beneficiario) throws HiperionException {
		try {
			Query query = em.createNamedQuery("Gastos.findByBeneficiario");
			query.setParameter("beneficiario", "%" + beneficiario + "%");

			List<Gasto> gastos = query.getResultList();

			return gastos;

		} catch (Exception ex) {
			log.error("Error: No se pudo realizar la Consulta --> Gastos.findByBeneficiario", ex);
			throw new HiperionException(ex);
		}
	}

}
