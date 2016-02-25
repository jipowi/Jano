/**
 * 
 */
package ec.com.uce.jano.servicio.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ec.com.uce.jano.comun.HiperionException;
import ec.com.uce.jano.dao.EgresoDao;
import ec.com.uce.jano.entities.Egreso;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.com.avila.hiperion.servicio.EgresoService#guardarEgreso(ec.com.avila.hiperion.emision.entities.Egreso)
	 */
	@Override
	public void guardarEgreso(Egreso egreso) throws HiperionException {
		egresoDao.persist(egreso);

	}

}
