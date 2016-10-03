/*
 * Copyright 2014 JIPOVI Solutions - ECUADOR 
 * Todos los derechos reservados
 */
package ec.com.uce.jano.web.backings;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import ec.com.uce.jano.comun.HiperionException;
import ec.com.uce.jano.dto.AfectacionDTO;
import ec.com.uce.jano.dto.CompromisoDTO;
import ec.com.uce.jano.dto.RecaudacionDTO;
import ec.com.uce.jano.entities.Afectacion;
import ec.com.uce.jano.entities.DetalleEgreso;
import ec.com.uce.jano.entities.Egreso;
import ec.com.uce.jano.entities.Gasto;
import ec.com.uce.jano.servicio.AfectacionService;
import ec.com.uce.jano.servicio.EgresoService;
import ec.com.uce.jano.servicio.RecaudacionService;
import ec.com.uce.jano.web.beans.ReformaBean;
import ec.com.uce.jano.web.util.MessagesController;

/**
 * <b> Incluir aqui la descripcion de la clase. </b>
 * 
 * @author kruger
 * @version 1.0,01/08/2016
 * @since JDK1.6
 */
@ManagedBean
@ViewScoped
public class ReformaBacking implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{reformaBean}")
	private ReformaBean reformaBean;

	@EJB
	private AfectacionService afectacionService;

	@EJB
	private RecaudacionService recaudacionService;

	@EJB
	private EgresoService egresoService;

	private List<SelectItem> periodoItems;
	private List<SelectItem> facultadItems;
	private List<SelectItem> dependenciaItems;
	private List<SelectItem> departamentoItems;
	private List<SelectItem> partidasItems;
	private List<RecaudacionDTO> recaudacionDTOs = new ArrayList<>();
	private List<CompromisoDTO> compromisosDTO;
	private Long idGastoOriginal;
	private Long idGastoAfectado;
	private Gasto gastoAfectado;
	private Gasto gastoOriginal;
	private double presupuestoOriginal;
	private double presupuestoAfectado;
	private double tempOriginal;
	private double tempAfectado;

	AfectacionDTO afectacionDTO = new AfectacionDTO();

	/**
	 * 
	 * <b> Permite buscar los compromisos por los filtros ingresados. </b>
	 * <p>
	 * [Author: kruger, Date: 05/09/2016]
	 * </p>
	 * 
	 * @throws HiperionException
	 */
	public void buscarCompromisos() throws HiperionException {

		try {

			if (!reformaBean.getEstado().equals("")) {
				compromisosDTO = recaudacionService.buscarGastosByEstado(reformaBean.getEstado());
				if (reformaBean.getEstado().equals("T")) {
					List<Gasto> gastos = recaudacionService.obtenerGastos();

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
						compromisoDTO.setPartida(gasto.getPartida());

						Egreso egresoDB = egresoService.buscarEgresos(gasto.getPeriodoGasto(), gasto.getAfectacion().getIdAfectacion());

						if (egresoDB != null) {
							List<DetalleEgreso> detEgresos = egresoService.buscarEgresos(egresoDB.getIdEgreso());

							for (DetalleEgreso detEgreso : detEgresos) {
								if (detEgreso.getPartida().getPartida().equals(gasto.getPartida().getPartida())) {
									compromisoDTO.setPresupuesto(detEgreso.getPresupuesto());
								}
							}
						}

						compromisosDTO.add(compromisoDTO);
					}

				}

			} else {
				MessagesController.addWarn(null, "Se deben ingresar todos los parametros de busqueda.");
			}

		} catch (HiperionException e) {
			throw new HiperionException(e);
		}
	}

	/**
	 * 
	 * <b> Permite realizar la tranferencia entre cuantas. </b>
	 * <p>
	 * [Author: kruger, Date: Oct 3, 2016]
	 * </p>
	 * 
	 */
	public void transferir() {
		double valorDisponibe = presupuestoOriginal - gastoOriginal.getValorGasto();
		if (this.tempOriginal <= valorDisponibe) {
			this.tempAfectado = this.tempOriginal;
		} else {
			MessagesController.addWarn(null, "El valor dispobible del gasto con CUR " + gastoOriginal.getCur() + " es de " + valorDisponibe);
		}
	}

	/**
	 * 
	 * <b> Permite actualizar un gasto en la base de datos. </b>
	 * <p>
	 * [Author: kruger, Date: Oct 3, 2016]
	 * </p>
	 * 
	 * @throws HiperionException
	 */
	public void actualizarTransferencias() throws HiperionException {
		try {

			if (gastoOriginal == null) {
				MessagesController.addWarn(null, "Usted debe ingresar el codigo de la cuenta que desea afectar y luego un valor.");
			} else {

				// Actualizar Original
				DetalleEgreso detEgresoOriginal = new DetalleEgreso();
				gastoOriginal.setValorGasto(gastoOriginal.getValorGasto() - this.tempOriginal);

				Egreso egresoDBOriginal = egresoService.buscarEgresos(gastoOriginal.getPeriodoGasto(), gastoOriginal.getAfectacion()
						.getIdAfectacion());

				if (egresoDBOriginal != null) {
					List<DetalleEgreso> detEgresos = egresoService.buscarEgresos(egresoDBOriginal.getIdEgreso());

					for (DetalleEgreso detEgreso : detEgresos) {
						if (detEgreso.getPartida().getPartida().equals(gastoOriginal.getPartida().getPartida())) {
							detEgresoOriginal = detEgreso;
							detEgresoOriginal.setPresupuesto(detEgreso.getPresupuesto() - this.tempOriginal);
						}
					}
				}
				egresoService.editarDetalleEgreso(detEgresoOriginal);

				DetalleEgreso detEgresoAfectado = new DetalleEgreso();
				gastoAfectado.setValorGasto(gastoAfectado.getValorGasto() - this.tempAfectado);

				Egreso egresoDBAfectado = egresoService.buscarEgresos(gastoAfectado.getPeriodoGasto(), gastoAfectado.getAfectacion()
						.getIdAfectacion());

				if (egresoDBAfectado != null) {
					List<DetalleEgreso> detEgresos = egresoService.buscarEgresos(egresoDBAfectado.getIdEgreso());

					for (DetalleEgreso detEgreso : detEgresos) {
						if (detEgreso.getPartida().getPartida().equals(gastoAfectado.getPartida().getPartida())) {
							detEgresoAfectado = detEgreso;
							detEgresoAfectado.setPresupuesto(detEgreso.getPresupuesto() - this.tempAfectado);
						}
					}
				}
				egresoService.editarDetalleEgreso(detEgresoAfectado);

				MessagesController.addInfo(null, "El valor " + this.tempOriginal + " fue transferido exitosamente a la partida "
						+ gastoAfectado.getPartida().getPartida());
			}

		} catch (HiperionException e) {
			throw new HiperionException(e);
		}
	}

	/**
	 * 
	 * <b> Permite obtener las facultades registradas y transformar a una lista de seleccion. </b>
	 * <p>
	 * [Author: Paul Jimenez, Date: 31/03/2016]
	 * </p>
	 * 
	 * @throws HiperionException
	 */
	public void obtenerFacultades() throws HiperionException {

		try {
			this.facultadItems = new ArrayList<SelectItem>();

			List<Afectacion> facultades = afectacionService.obtenerFacultades();

			for (Afectacion facultad : facultades) {
				SelectItem selectItem = new SelectItem(facultad.getIdAfectacion(), facultad.getDescAfectacion());
				facultadItems.add(selectItem);
			}

		} catch (HiperionException e) {
			throw new HiperionException(e);
		}
	}

	/**
	 * 
	 * <b> Permite obtener las dependencias registradas y transformar a una lista de seleccion. </b>
	 * <p>
	 * [Author: Paul Jimenez, Date: 31/03/2016]
	 * </p>
	 * 
	 * @throws HiperionException
	 */
	public void obtenerDependencias() throws HiperionException {

		try {
			this.dependenciaItems = new ArrayList<SelectItem>();

			List<Afectacion> dependencias = afectacionService.obtenerDependencias(reformaBean.getFacultad());

			for (Afectacion dependencia : dependencias) {
				SelectItem selectItem = new SelectItem(dependencia.getIdAfectacion(), dependencia.getDescAfectacion());
				dependenciaItems.add(selectItem);
			}

		} catch (HiperionException e) {
			throw new HiperionException(e);
		}
	}

	/**
	 * 
	 * <b> Permite obtener los departamentos registradas y transformar a una lista de seleccion. </b>
	 * <p>
	 * [Author: Paul Jimenez, Date: 31/03/2016]
	 * </p>
	 * 
	 * @throws HiperionException
	 */
	public void obtenerDepartamentos() throws HiperionException {

		try {
			this.departamentoItems = new ArrayList<SelectItem>();

			List<Afectacion> departamentos = afectacionService.obtenerDepartamentos(reformaBean.getFacultad(), reformaBean.getDependencia());

			for (Afectacion departamento : departamentos) {
				SelectItem selectItem = new SelectItem(departamento.getIdAfectacion(), departamento.getDescAfectacion());
				departamentoItems.add(selectItem);
			}

		} catch (HiperionException e) {
			throw new HiperionException(e);
		}
	}

	/**
	 * 
	 * <b> Permite obtener los datos del gasto original </b>
	 * <p>
	 * [Author: kruger, Date: 16/09/2016]
	 * </p>
	 * 
	 * @throws HiperionException
	 */
	public void reformarOriginal() throws HiperionException {

		gastoOriginal = new Gasto();
		gastoOriginal = recaudacionService.buscarGastoById(idGastoOriginal);
		Long idAfectacion = gastoOriginal.getAfectacion().getIdAfectacion();

		afectacionDTO.setIdAfectacion(idAfectacion);
		Afectacion afectacion = afectacionService.obetenerAfectacionById(idAfectacion);
		afectacionDTO.setAfectacion(afectacion.getDescAfectacion());

		Afectacion dependencia = afectacionService.obetenerAfectacionById(afectacion.getIdDependencia());
		afectacionDTO.setDependencia(dependencia.getDescAfectacion());

		Afectacion facultad = afectacionService.obetenerAfectacionById(afectacion.getIdFacultad());
		afectacionDTO.setFacultad(facultad.getDescAfectacion());

		Egreso egresoDB = egresoService.buscarEgresos(gastoOriginal.getPeriodoGasto(), idAfectacion);

		if (egresoDB != null) {
			List<DetalleEgreso> detEgresos = egresoService.buscarEgresos(egresoDB.getIdEgreso());

			for (DetalleEgreso detEgreso : detEgresos) {
				if (detEgreso.getPartida().getPartida().equals(gastoOriginal.getPartida().getPartida())) {
					presupuestoOriginal = detEgreso.getPresupuesto();
				}
			}
		}

	}

	/**
	 * 
	 * <b> Permite obtener los datos del gasto afectado</b>
	 * <p>
	 * [Author: kruger, Date: 16/09/2016]
	 * </p>
	 * 
	 * @throws HiperionException
	 */
	public void reformarAfectado() throws HiperionException {

		gastoAfectado = new Gasto();
		gastoAfectado = recaudacionService.buscarGastoById(idGastoAfectado);
		Long idAfectacion = gastoAfectado.getAfectacion().getIdAfectacion();

		afectacionDTO.setIdAfectacion(idAfectacion);
		Afectacion afectacion = afectacionService.obetenerAfectacionById(idAfectacion);
		afectacionDTO.setAfectacion(afectacion.getDescAfectacion());

		Afectacion dependencia = afectacionService.obetenerAfectacionById(afectacion.getIdDependencia());
		afectacionDTO.setDependencia(dependencia.getDescAfectacion());

		Afectacion facultad = afectacionService.obetenerAfectacionById(afectacion.getIdFacultad());
		afectacionDTO.setFacultad(facultad.getDescAfectacion());

		Egreso egresoDB = egresoService.buscarEgresos(gastoAfectado.getPeriodoGasto(), idAfectacion);

		if (egresoDB != null) {
			List<DetalleEgreso> detEgresos = egresoService.buscarEgresos(egresoDB.getIdEgreso());

			for (DetalleEgreso detEgreso : detEgresos) {
				if (detEgreso.getPartida().getPartida().equals(gastoAfectado.getPartida().getPartida())) {
					presupuestoAfectado = detEgreso.getPresupuesto();
				}
			}
		}

	}

	/**
	 * @return the reformaBean
	 */
	public ReformaBean getReformaBean() {
		return reformaBean;
	}

	/**
	 * @param reformaBean
	 *            the reformaBean to set
	 */
	public void setReformaBean(ReformaBean reformaBean) {
		this.reformaBean = reformaBean;
	}

	/**
	 * @return the periodoItems
	 */
	public List<SelectItem> getPeriodoItems() {
		return periodoItems;
	}

	/**
	 * @param periodoItems
	 *            the periodoItems to set
	 */
	public void setPeriodoItems(List<SelectItem> periodoItems) {
		this.periodoItems = periodoItems;
	}

	/**
	 * @return the facultadItems
	 */
	public List<SelectItem> getFacultadItems() {
		return facultadItems;
	}

	/**
	 * @param facultadItems
	 *            the facultadItems to set
	 */
	public void setFacultadItems(List<SelectItem> facultadItems) {
		this.facultadItems = facultadItems;
	}

	/**
	 * @return the dependenciaItems
	 */
	public List<SelectItem> getDependenciaItems() {
		return dependenciaItems;
	}

	/**
	 * @param dependenciaItems
	 *            the dependenciaItems to set
	 */
	public void setDependenciaItems(List<SelectItem> dependenciaItems) {
		this.dependenciaItems = dependenciaItems;
	}

	/**
	 * @return the departamentoItems
	 */
	public List<SelectItem> getDepartamentoItems() {
		return departamentoItems;
	}

	/**
	 * @param departamentoItems
	 *            the departamentoItems to set
	 */
	public void setDepartamentoItems(List<SelectItem> departamentoItems) {
		this.departamentoItems = departamentoItems;
	}

	/**
	 * @return the partidasItems
	 */
	public List<SelectItem> getPartidasItems() {
		return partidasItems;
	}

	/**
	 * @param partidasItems
	 *            the partidasItems to set
	 */
	public void setPartidasItems(List<SelectItem> partidasItems) {
		this.partidasItems = partidasItems;
	}

	/**
	 * @return the recaudacionDTOs
	 */
	public List<RecaudacionDTO> getRecaudacionDTOs() {
		return recaudacionDTOs;
	}

	/**
	 * @param recaudacionDTOs
	 *            the recaudacionDTOs to set
	 */
	public void setRecaudacionDTOs(List<RecaudacionDTO> recaudacionDTOs) {
		this.recaudacionDTOs = recaudacionDTOs;
	}

	/**
	 * @return the compromisosDTO
	 */
	public List<CompromisoDTO> getCompromisosDTO() {
		return compromisosDTO;
	}

	/**
	 * @param compromisosDTO
	 *            the compromisosDTO to set
	 */
	public void setCompromisosDTO(List<CompromisoDTO> compromisosDTO) {
		this.compromisosDTO = compromisosDTO;
	}

	/**
	 * @return the afectacionDTO
	 */
	public AfectacionDTO getAfectacionDTO() {
		return afectacionDTO;
	}

	/**
	 * @param afectacionDTO
	 *            the afectacionDTO to set
	 */
	public void setAfectacionDTO(AfectacionDTO afectacionDTO) {
		this.afectacionDTO = afectacionDTO;
	}

	/**
	 * @return the idGastoOriginal
	 */
	public Long getIdGastoOriginal() {
		return idGastoOriginal;
	}

	/**
	 * @param idGastoOriginal
	 *            the idGastoOriginal to set
	 */
	public void setIdGastoOriginal(Long idGastoOriginal) {
		this.idGastoOriginal = idGastoOriginal;
	}

	/**
	 * @return the idGastoAfectado
	 */
	public Long getIdGastoAfectado() {
		return idGastoAfectado;
	}

	/**
	 * @param idGastoAfectado
	 *            the idGastoAfectado to set
	 */
	public void setIdGastoAfectado(Long idGastoAfectado) {
		this.idGastoAfectado = idGastoAfectado;
	}

	/**
	 * @return the presupuestoOriginal
	 */
	public double getPresupuestoOriginal() {
		return presupuestoOriginal;
	}

	/**
	 * @param presupuestoOriginal
	 *            the presupuestoOriginal to set
	 */
	public void setPresupuestoOriginal(double presupuestoOriginal) {
		this.presupuestoOriginal = presupuestoOriginal;
	}

	/**
	 * @return the presupuestoAfectado
	 */
	public double getPresupuestoAfectado() {
		return presupuestoAfectado;
	}

	/**
	 * @param presupuestoAfectado
	 *            the presupuestoAfectado to set
	 */
	public void setPresupuestoAfectado(double presupuestoAfectado) {
		this.presupuestoAfectado = presupuestoAfectado;
	}

	/**
	 * @return the gastoAfectado
	 */
	public Gasto getGastoAfectado() {
		return gastoAfectado;
	}

	/**
	 * @param gastoAfectado
	 *            the gastoAfectado to set
	 */
	public void setGastoAfectado(Gasto gastoAfectado) {
		this.gastoAfectado = gastoAfectado;
	}

	/**
	 * @return the gastoOriginal
	 */
	public Gasto getGastoOriginal() {
		return gastoOriginal;
	}

	/**
	 * @param gastoOriginal
	 *            the gastoOriginal to set
	 */
	public void setGastoOriginal(Gasto gastoOriginal) {
		this.gastoOriginal = gastoOriginal;
	}

	/**
	 * @return the tempOriginal
	 */
	public double getTempOriginal() {
		return tempOriginal;
	}

	/**
	 * @param tempOriginal
	 *            the tempOriginal to set
	 */
	public void setTempOriginal(double tempOriginal) {
		this.tempOriginal = tempOriginal;
	}

	/**
	 * @return the tempAfectado
	 */
	public double getTempAfectado() {
		return tempAfectado;
	}

	/**
	 * @param tempAfectado
	 *            the tempAfectado to set
	 */
	public void setTempAfectado(double tempAfectado) {
		this.tempAfectado = tempAfectado;
	}

}
