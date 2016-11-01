/**
 * 
 */
package ec.com.uce.jano.servicio.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ec.com.uce.jano.comun.HiperionException;
import ec.com.uce.jano.dao.GastoDao;
import ec.com.uce.jano.dao.RecaudacionDao;
import ec.com.uce.jano.dto.CompromisoDTO;
import ec.com.uce.jano.dto.RecaudacionDTO;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.com.uce.jano.servicio.RecaudacionService#obtenerGastos(java.lang.Long)
	 */
	@Override
	public List<Gasto> obtenerGastos(Long idAfectacion) throws HiperionException {
		return gastoDao.obtenerGastos(idAfectacion);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.com.uce.jano.servicio.RecaudacionService#obtenerRecaudaciones()
	 */
	@Override
	public List<Recaudacion> obtenerRecaudaciones() throws HiperionException {
		return recaudacionDao.findAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.com.uce.jano.servicio.RecaudacionService#obtenerGastos()
	 */
	@Override
	public List<Gasto> obtenerGastos() throws HiperionException {
		return gastoDao.findAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.com.uce.jano.servicio.RecaudacionService#buscarGastos(java.lang.Long)
	 */
	@Override
	public List<CompromisoDTO> buscarGastosAll(String periodo, String beneficiario, Date fechaInicio, Date fechaFin) throws HiperionException {

		List<Gasto> gastos = gastoDao.buscarGastosAll(periodo, beneficiario, fechaInicio, fechaFin);
		List<CompromisoDTO> compromisoDTOs = new ArrayList<>();

		for (Gasto gasto : gastos) {
			CompromisoDTO compromisoDTO = new CompromisoDTO();

			compromisoDTO.setIdGasto(gasto.getIdGastos());
			compromisoDTO.setBeneficiario(gasto.getBeneficiarioGasto());
			compromisoDTO.setEstado(gasto.getEstadoGasto());
			compromisoDTO.setPeriodo(gasto.getPeriodoGasto());
			compromisoDTO.setValor(gasto.getValorGasto());
			compromisoDTO.setFecha(gasto.getFechaGasto());
			compromisoDTO.setComprobante(gasto.getComprobanteGasto());
			compromisoDTO.setCur(gasto.getCur());

			compromisoDTOs.add(compromisoDTO);
		}

		return compromisoDTOs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.com.uce.jano.servicio.RecaudacionService#editarCompromiso(ec.com.uce.jano.dto.CompromisoDTO)
	 */
	@Override
	public void editarCompromiso(CompromisoDTO compromiso) throws HiperionException {

		Gasto gasto = new Gasto();

		gasto.setIdGastos(compromiso.getIdGasto());
		gasto.setBeneficiarioGasto(compromiso.getBeneficiario());
		gasto.setComprobanteGasto(compromiso.getComprobante());
		gasto.setCur(compromiso.getCur());
		gasto.setEstadoGasto(compromiso.getEstado());
		gasto.setFechaGasto(compromiso.getFecha());
		gasto.setValorGasto(compromiso.getValor());
		gasto.setPeriodoGasto(compromiso.getPeriodo());

		gastoDao.update(gasto);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.com.uce.jano.servicio.RecaudacionService#eliminarCompromiso(ec.com.uce.jano.dto.CompromisoDTO)
	 */
	@Override
	public void eliminarCompromiso(CompromisoDTO compromiso) throws HiperionException {

		Gasto gasto = new Gasto();

		gasto.setIdGastos(compromiso.getIdGasto());
		gastoDao.delete(gasto);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.com.uce.jano.servicio.RecaudacionService#buscarRecaudaciones(java.lang.String, java.util.Date, java.util.Date)
	 */
	@Override
	public List<RecaudacionDTO> buscarRecaudaciones(String periodo, Date fechaInicio, Date fechaFin) throws HiperionException {

		List<Recaudacion> recaudaciones = recaudacionDao.buscarRecaudaciones(periodo, fechaInicio, fechaFin);
		List<RecaudacionDTO> recaudacionDTOs = new ArrayList<>();

		for (Recaudacion recaudacion : recaudaciones) {
			RecaudacionDTO recaudacionDTO = new RecaudacionDTO();

			recaudacionDTO.setIdRecaudacion(recaudacion.getIdRecaudacion());
			recaudacionDTO.setComprobante(recaudacion.getComprobante());
			recaudacionDTO.setCur(recaudacion.getCur());
			recaudacionDTO.setBeneficiario(recaudacion.getBeneficiario());
			recaudacionDTO.setFechaRecaudacion(recaudacion.getFechaRecaudacion());
			recaudacionDTO.setValorRecaudacion(recaudacion.getValorRecaudacion());
			recaudacionDTO.setPeriodo(recaudacion.getPeridoRecaudacion());
			recaudacionDTO.setAfectacion(recaudacion.getAfectacion());
			recaudacionDTO.setPartida(recaudacion.getPartida());
			recaudacionDTO.setObservacion(recaudacion.getObservacion());
			recaudacionDTO.setCodigoIngreso(recaudacion.getCodigoIngreso());

			recaudacionDTOs.add(recaudacionDTO);
		}

		return recaudacionDTOs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.com.uce.jano.servicio.RecaudacionService#editarRecaudacion(ec.com.uce.jano.dto.RecaudacionDTO)
	 */
	@Override
	public void editarRecaudacion(RecaudacionDTO recaudacionDTO) throws HiperionException {
		Recaudacion recaudacion = new Recaudacion();

		recaudacion.setIdRecaudacion(recaudacionDTO.getIdRecaudacion());
		recaudacion.setComprobante(recaudacionDTO.getComprobante());
		recaudacion.setCur(recaudacionDTO.getCur());
		recaudacion.setBeneficiario(recaudacionDTO.getBeneficiario());
		recaudacion.setEstadoRecaudacion(recaudacionDTO.getEstado());
		recaudacion.setFechaRecaudacion(recaudacionDTO.getFechaRecaudacion());
		recaudacion.setValorRecaudacion(recaudacionDTO.getValorRecaudacion());
		recaudacion.setPeridoRecaudacion(recaudacionDTO.getPeriodo());
		recaudacion.setAfectacion(recaudacionDTO.getAfectacion());
		recaudacion.setPartida(recaudacionDTO.getPartida());
		recaudacion.setObservacion(recaudacionDTO.getObservacion());
		recaudacion.setCodigoIngreso(recaudacionDTO.getCodigoIngreso());

		recaudacionDao.update(recaudacion);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.com.uce.jano.servicio.RecaudacionService#eliminarRecaudacion(ec.com.uce.jano.dto.RecaudacionDTO)
	 */
	@Override
	public void eliminarRecaudacion(RecaudacionDTO recaudacionDTO) throws HiperionException {
		Recaudacion recaudacion = new Recaudacion();

		recaudacion.setIdRecaudacion(recaudacionDTO.getIdRecaudacion());
		recaudacionDao.delete(recaudacion);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.com.uce.jano.servicio.RecaudacionService#buscarGastoById(java.lang.Integer)
	 */
	@Override
	public Gasto buscarGastoById(Long idGasto) throws HiperionException {

		return gastoDao.findById(idGasto);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.com.uce.jano.servicio.RecaudacionService#buscaGastosByComprobante(java.lang.String)
	 */
	@Override
	public List<Gasto> buscaGastosByComprobante(String comprobante) throws HiperionException {
		return gastoDao.buscaGastosByComprobante(comprobante);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.com.uce.jano.servicio.RecaudacionService#buscarGastosByEstado(java.lang.String)
	 */
	@Override
	public List<CompromisoDTO> buscarGastosByEstado(String estado) throws HiperionException {

		List<Gasto> gastos = gastoDao.buscarGastosByEstado(estado);
		List<CompromisoDTO> compromisoDTOs = new ArrayList<>();

		for (Gasto gasto : gastos) {
			CompromisoDTO compromisoDTO = new CompromisoDTO();

			compromisoDTO.setIdGasto(gasto.getIdGastos());
			compromisoDTO.setBeneficiario(gasto.getBeneficiarioGasto());
			compromisoDTO.setEstado(gasto.getEstadoGasto());
			compromisoDTO.setPeriodo(gasto.getPeriodoGasto());
			compromisoDTO.setValor(gasto.getValorGasto());
			compromisoDTO.setFecha(gasto.getFechaGasto());
			compromisoDTO.setComprobante(gasto.getComprobanteGasto());
			compromisoDTO.setCur(gasto.getCur());

			compromisoDTOs.add(compromisoDTO);
		}

		return compromisoDTOs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.com.uce.jano.servicio.RecaudacionService#actualizarGasto(ec.com.uce.jano.entities.Gasto)
	 */
	@Override
	public void actualizarGasto(Gasto gasto) throws HiperionException {
		gastoDao.update(gasto);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.com.uce.jano.servicio.RecaudacionService#buscarGastosByPeriodo(java.lang.String)
	 */
	@Override
	public List<CompromisoDTO> buscarGastosByPeriodo(String periodo) throws HiperionException {
		List<Gasto> gastos = gastoDao.buscarGastosByPeriodo(periodo);
		List<CompromisoDTO> compromisoDTOs = new ArrayList<>();

		for (Gasto gasto : gastos) {
			CompromisoDTO compromisoDTO = new CompromisoDTO();

			compromisoDTO.setIdGasto(gasto.getIdGastos());
			compromisoDTO.setBeneficiario(gasto.getBeneficiarioGasto());
			compromisoDTO.setEstado(gasto.getEstadoGasto());
			compromisoDTO.setPeriodo(gasto.getPeriodoGasto());
			compromisoDTO.setValor(gasto.getValorGasto());
			compromisoDTO.setFecha(gasto.getFechaGasto());
			compromisoDTO.setComprobante(gasto.getComprobanteGasto());
			compromisoDTO.setCur(gasto.getCur());

			compromisoDTOs.add(compromisoDTO);
		}

		return compromisoDTOs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.com.uce.jano.servicio.RecaudacionService#buscaGastosByBeneficiario(java.lang.String)
	 */
	@Override
	public List<CompromisoDTO> buscaGastosByBeneficiario(String beneficiario) throws HiperionException {
		List<Gasto> gastos = gastoDao.buscaGastosByBeneficiario(beneficiario);
		List<CompromisoDTO> compromisoDTOs = new ArrayList<>();

		for (Gasto gasto : gastos) {
			CompromisoDTO compromisoDTO = new CompromisoDTO();

			compromisoDTO.setIdGasto(gasto.getIdGastos());
			compromisoDTO.setBeneficiario(gasto.getBeneficiarioGasto());
			compromisoDTO.setEstado(gasto.getEstadoGasto());
			compromisoDTO.setPeriodo(gasto.getPeriodoGasto());
			compromisoDTO.setValor(gasto.getValorGasto());
			compromisoDTO.setFecha(gasto.getFechaGasto());
			compromisoDTO.setComprobante(gasto.getComprobanteGasto());
			compromisoDTO.setCur(gasto.getCur());

			compromisoDTOs.add(compromisoDTO);
		}

		return compromisoDTOs;
	}

}
