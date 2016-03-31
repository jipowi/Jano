/**
 * 
 */
package ec.com.uce.jano.servicio.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ec.com.uce.jano.comun.HiperionException;
import ec.com.uce.jano.dao.AfectacionDao;
import ec.com.uce.jano.entities.Afectacion;
import ec.com.uce.jano.servicio.AfectacionService;

/**
 * <b> Implementacion de la interface local de afectaciones. </b>
 * 
 * @author HIPERION
 * @version 1.0,23/02/2016
 * @since JDK1.6
 */
@Stateless
public class AfectacionServiceImpl implements AfectacionService {

	@EJB
	private AfectacionDao afectacionDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.com.uce.jano.servicio.AfectacionService#guardarAfectacion(ec.com.uce.jano.entities.Afectacion)
	 */
	@Override
	public void guardarAfectacion(Afectacion afectacion) throws HiperionException {
		afectacionDao.persist(afectacion);
	}

}
