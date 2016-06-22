/**
 * 
 */
package ec.com.uce.jano.servicio.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ec.com.uce.jano.comun.HiperionException;
import ec.com.uce.jano.dao.GastoDao;
import ec.com.uce.jano.dao.RecaudacionDao;
import ec.com.uce.jano.entities.Gasto;
import ec.com.uce.jano.entities.Recaudacion;
import ec.com.uce.jano.servicio.RecaudacionService;

/**
 * <b> Implementacion de la interface local de recuadaciones. </b>
 * 
 * @author Paul Jimenez
 * @version 1.0,23/02/2016
 * @since JDK1.6
 */
@Stateless
public class RecaudacionServiceImpl implements RecaudacionService {

	@EJB
	private RecaudacionDao recaudacionDao;

	@EJB
	private GastoDao gastoDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.com.uce.jano.servicio.RecaudacionService#guardarRecaudacion(ec.com.uce.jano.entities.Recaudacion)
	 */
	@Override
	public void guardarRecaudacion(Recaudacion recaudacion) throws HiperionException {
		recaudacionDao.persist(recaudacion);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.com.uce.jano.servicio.RecaudacionService#obtenerRecaudaciones(java.lang.Long)
	 */
	@Override
	public List<Recaudacion> obtenerRecaudaciones(Long idAfectacion) throws HiperionException {
		return recaudacionDao.obtenerRecaudaciones(idAfectacion);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.com.uce.jano.servicio.RecaudacionService#guardarGastos(ec.com.uce.jano.entities.Gasto)
	 */
	@Override
	public void guardarGastos(Gasto gasto) throws HiperionException {
		gastoDao.persist(gasto);

	}

	/* (non-Javadoc)
	 * @see ec.com.uce.jano.servicio.RecaudacionService#obtenerGastos(java.lang.Long)
	 */
	@Override
	public List<Gasto> obtenerGastos(Long idAfectacion) throws HiperionException {
		return gastoDao.obtenerGastos(idAfectacion);
	}

	/* (non-Javadoc)
	 * @see ec.com.uce.jano.servicio.RecaudacionService#obtenerRecaudaciones()
	 */
	@Override
	public List<Recaudacion> obtenerRecaudaciones() throws HiperionException {
		return recaudacionDao.findAll();
	}

	/* (non-Javadoc)
	 * @see ec.com.uce.jano.servicio.RecaudacionService#obtenerGastos()
	 */
	@Override
	public List<Gasto> obtenerGastos() throws HiperionException {
		return gastoDao.findAll();
	}

}
