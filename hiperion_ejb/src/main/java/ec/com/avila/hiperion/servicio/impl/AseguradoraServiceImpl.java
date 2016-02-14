/*
 * Copyright 2014 JIPOVI Solutions - ECUADOR 
 * Todos los derechos reservados
 */
package ec.com.avila.hiperion.servicio.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ec.com.avila.hiperion.comun.HiperionException;
import ec.com.avila.hiperion.dao.AseguradoraDao;
import ec.com.avila.hiperion.dao.ClienteDao;
import ec.com.avila.hiperion.emision.entities.Aseguradora;
import ec.com.avila.hiperion.emision.entities.Cliente;
import ec.com.avila.hiperion.servicio.AseguradoraService;

/**
 * <b>Implementacion del servicio de aseguradora</b>
 * 
 * @author kruger
 * @version 1.0,Dec 21, 2013
 * @since JDK1.6
 */
@Stateless
public class AseguradoraServiceImpl implements AseguradoraService {

	@EJB
	private AseguradoraDao aseguradoraDao;

	@EJB
	private ClienteDao clienteDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.com.avila.hiperion.servicio.PolizaService#guardarAseguradora(ec.com.avila.hiperion.emision.entities.Aseguradora, java.util.List)
	 */
	@Override
	public void guardarAseguradora(Aseguradora aseguradora, List<Cliente> contactos) throws HiperionException {

		aseguradoraDao.persist(aseguradora);

		for (Cliente cliente : contactos) {
			cliente.setAseguradora(aseguradora);
			clienteDao.persist(cliente);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.com.avila.hiperion.servicio.AseguradoraService#consultarAseguradoraByRucAseg(java.lang.String, java.lang.Integer)
	 */
	@Override
	public List<Aseguradora> consultarAseguradoraByRucAseg(String ruc, Integer aseguradora) throws HiperionException {
		return aseguradoraDao.consultarAseguradora(ruc, aseguradora);
	}

	/* (non-Javadoc)
	 * @see ec.com.avila.hiperion.servicio.AseguradoraService#consultarClienteByAseguradora(java.lang.String)
	 */
	@Override
	public List<Cliente> consultarClienteByAseguradora(String aseguradora) throws HiperionException {
		return clienteDao.consultarClienteByAseguradora(aseguradora);
	}

}