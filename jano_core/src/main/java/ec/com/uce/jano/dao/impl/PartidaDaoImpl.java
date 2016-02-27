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
import ec.com.uce.jano.dao.PartidaDao;
import ec.com.uce.jano.entities.Partida;

/**
 * 
 * <b> Permite implementar las operaciones de la interfaz local. </b>
 * 
 * @author HIPERION
 * @version 1.0,24/02/2016
 * @since JDK1.6
 */
@Stateless
public class PartidaDaoImpl extends GenericDAOImpl<Partida, Long> implements PartidaDao {

	Logger log = Logger.getLogger(PartidaDaoImpl.class);

	@PersistenceContext(unitName = "sgs_pu")
	protected EntityManager em;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.com.uce.jano.dao.PartidaDao#obtenerPartidasEgreso()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Partida> obtenerPartidasEgreso(String tipoPartida) throws HiperionException {

		try {
			Query query = em.createNamedQuery("Partida.findByTipoPartida");
			query.setParameter("tipoPartida", tipoPartida);
			List<Partida> partidas = query.getResultList();

			return partidas;

		} catch (Exception ex) {
			log.error("Error: No se pudo realizar la Consulta --> findByTipoPartida", ex);
			throw new HiperionException(ex);
		}
	}

}
