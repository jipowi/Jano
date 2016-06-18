/**
 * 
 */
package ec.com.uce.jano.servicio.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ec.com.uce.jano.comun.HiperionException;
import ec.com.uce.jano.dao.AfectacionDao;
import ec.com.uce.jano.entities.Afectacion;
import ec.com.uce.jano.servicio.AfectacionService;

/**
 * <b> Implementacion de la interface local de afectaciones. </b>
 * 
 * @author Paul Jimenez
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.com.uce.jano.servicio.AfectacionService#obtenerFacultades()
	 */
	@Override
	public List<Afectacion> obtenerFacultades() throws HiperionException {
		return afectacionDao.obtenerFacultades();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.com.uce.jano.servicio.AfectacionService#obtenerDependencias(java.lang.Integer)
	 */
	@Override
	public List<Afectacion> obtenerDependencias(Integer idFacultad) throws HiperionException {
		return afectacionDao.obtenerDependencias(idFacultad);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.com.uce.jano.servicio.AfectacionService#obtenerDepartamentos(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<Afectacion> obtenerDepartamentos(Integer idFacultad, Integer idDependencia) throws HiperionException {
		return afectacionDao.obtenerDepartamentos(idFacultad, idDependencia);
	}

}
