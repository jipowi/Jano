/**
 * 
 */
package ec.com.uce.jano.servicio.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ec.com.uce.jano.comun.HiperionException;
import ec.com.uce.jano.dao.RecaudacionDao;
import ec.com.uce.jano.entities.Recaudacion;
import ec.com.uce.jano.servicio.RecaudacionService;

/**
 * <b> Implementacion de la interface local de recuadaciones. </b>
 * 
 * @author HIPERION
 * @version 1.0,23/02/2016
 * @since JDK1.6
 */
@Stateless
public class RecaudacionServiceImpl implements RecaudacionService {

	@EJB
	private RecaudacionDao recaudacionDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.com.uce.jano.servicio.RecaudacionService#guardarRecaudacion(ec.com.uce.jano.entities.Recaudacion)
	 */
	@Override
	public void guardarRecaudacion(Recaudacion recaudacion) throws HiperionException {
		recaudacionDao.persist(recaudacion);
	}

}
