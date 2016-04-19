/**
 * 
 */
package ec.com.uce.jano.servicio.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ec.com.uce.jano.comun.HiperionException;
import ec.com.uce.jano.dao.DetalleEgresoDao;
import ec.com.uce.jano.dao.EgresoDao;
import ec.com.uce.jano.dao.PartidaDao;
import ec.com.uce.jano.entities.DetalleEgreso;
import ec.com.uce.jano.entities.Egreso;
import ec.com.uce.jano.entities.Partida;
import ec.com.uce.jano.servicio.EgresoService;

/**
 * <b> Implementacion de la interface local de egresos. </b>
 * 
 * @author HIPERION
 * @version 1.0,23/02/2016
 * @since JDK1.6
 */
@Stateless
public class EgresoServiceImpl implements EgresoService {

	@EJB
	private EgresoDao egresoDao;
	@EJB
	private DetalleEgresoDao detalleEgresoDao;
	@EJB
	private PartidaDao partidaDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.com.avila.hiperion.servicio.EgresoService#guardarEgreso(ec.com.avila.hiperion.emision.entities.Egreso)
	 */
	@Override
	public void guardarEgreso(Egreso egreso, List<DetalleEgreso> detalles, boolean save) throws HiperionException {

		if (save) {
			egresoDao.persist(egreso);
		} else {
			egresoDao.update(egreso);
		}

		for (DetalleEgreso detalleEgreso : detalles) {
			detalleEgreso.setEgreso(egreso);
			detalleEgresoDao.persist(detalleEgreso);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.com.uce.jano.servicio.EgresoService#guardarPartida(ec.com.uce.jano.entities.Partida)
	 */
	@Override
	public void guardarPartida(Partida partida) throws HiperionException {
		partidaDao.persist(partida);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.com.uce.jano.servicio.EgresoService#obtenerPartidasEgreso(java.lang.String)
	 */
	@Override
	public List<Partida> obtenerPartidas(String tipoPartida) throws HiperionException {
		return partidaDao.obtenerPartidasEgreso(tipoPartida);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.com.uce.jano.servicio.EgresoService#obtenerPartidaById(java.lang.Long)
	 */
	@Override
	public Partida obtenerPartidaById(Long idPartida) throws HiperionException {
		return partidaDao.findById(idPartida);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.com.uce.jano.servicio.EgresoService#buscarEgresos(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Egreso buscarEgresos(String periodo, Long idAfectacion) throws HiperionException {
		return egresoDao.buscarEgresos(periodo, idAfectacion);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.com.uce.jano.servicio.EgresoService#buscarEgresos(java.lang.Long)
	 */
	@Override
	public List<DetalleEgreso> buscarEgresos(Long idEgreso) throws HiperionException {
		return detalleEgresoDao.buscarEgresos(idEgreso);
	}

}
