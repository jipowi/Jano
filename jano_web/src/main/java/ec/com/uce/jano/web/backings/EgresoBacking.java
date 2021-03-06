/*
 * Copyright 2014 JIPOVI Solutions - ECUADOR 
 * Todos los derechos reservados
 */
package ec.com.uce.jano.web.backings;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.primefaces.event.RowEditEvent;

import ec.com.uce.jano.comun.HiperionException;
import ec.com.uce.jano.dto.EgresoDTO;
import ec.com.uce.jano.entities.Afectacion;
import ec.com.uce.jano.entities.Catalogo;
import ec.com.uce.jano.entities.DetalleCatalogo;
import ec.com.uce.jano.entities.DetalleEgreso;
import ec.com.uce.jano.entities.Egreso;
import ec.com.uce.jano.entities.Partida;
import ec.com.uce.jano.servicio.AfectacionService;
import ec.com.uce.jano.servicio.CatalogoService;
import ec.com.uce.jano.servicio.DetalleCatalogoService;
import ec.com.uce.jano.servicio.EgresoService;
import ec.com.uce.jano.web.beans.EgresoBean;
import ec.com.uce.jano.web.util.HiperionMensajes;
import ec.com.uce.jano.web.util.MessagesController;

/**
 * <b> Incluir aqui la descripcion de la clase. </b>
 * 
 * @author Paul Jimenez
 * @version 1.0,23/02/2016
 * @since JDK1.6
 */
@ManagedBean
@ViewScoped
public class EgresoBacking implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{egresoBean}")
	private EgresoBean egresoBean;

	@EJB
	private CatalogoService catalogoService;
	@EJB
	private EgresoService egresoService;
	@EJB
	private DetalleCatalogoService detalleCatalogoService;
	@EJB
	private AfectacionService afectacionService;

	private List<SelectItem> periodoItems;
	private List<SelectItem> facultadItems;
	private List<SelectItem> dependenciaItems;
	private List<SelectItem> departamentoItems;
	private List<SelectItem> partidaItems;
	private List<EgresoDTO> egresosDTO = new ArrayList<>();
	private List<DetalleEgreso> detEgresos = new ArrayList<>();
	private Egreso egresoDB;
	private Partida partida;
	private Double presupuesto;
	private Long idPartida;
	private boolean activarTabla = false;
	private double totalPresupuesto;

	@PostConstruct
	public void inicializar() throws HiperionException {
		try {

			obtenerFacultades();

		} catch (HiperionException e) {
			throw new HiperionException(e);
		}
	}

	/**
	 * @return the periodoItems
	 * @throws HiperionException
	 */
	public List<SelectItem> getPartidaItems() throws HiperionException {
		try {
			this.partidaItems = new ArrayList<SelectItem>();

			List<Partida> partidas = egresoService.obtenerPartidas("Egreso");

			for (Partida partida : partidas) {
				SelectItem selectItem = new SelectItem(partida.getIdPartida(), partida.getPartida());
				partidaItems.add(selectItem);
			}

		} catch (HiperionException e) {
			throw new HiperionException(e);
		}
		return partidaItems;
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

			List<Afectacion> dependencias = afectacionService.obtenerDependencias(egresoBean.getFacultad());

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

			List<Afectacion> departamentos = afectacionService.obtenerDepartamentos(egresoBean.getFacultad(), egresoBean.getDependencia());

			for (Afectacion departamento : departamentos) {
				SelectItem selectItem = new SelectItem(departamento.getIdAfectacion(), departamento.getDescAfectacion());
				departamentoItems.add(selectItem);
			}

		} catch (HiperionException e) {
			throw new HiperionException(e);
		}
	}

	/**
	 * @return the periodoItems
	 * @throws HiperionException
	 */
	public List<SelectItem> getPeriodoItems() throws HiperionException {
		try {
			this.periodoItems = new ArrayList<SelectItem>();
			Catalogo catalogo = catalogoService.consultarCatalogoById(HiperionMensajes.getInstancia().getLong("ec.gob.jano.catalogo.periodo"));

			List<DetalleCatalogo> periodos = catalogo.getDetalleCatalogos();

			for (DetalleCatalogo detalle : periodos) {
				SelectItem selectItem = new SelectItem(detalle.getCodDetalleCatalogo(), detalle.getDescDetCatalogo());
				periodoItems.add(selectItem);
			}

		} catch (HiperionException e) {
			throw new HiperionException(e);
		}
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
	 * @param facultadItems
	 *            the facultadItems to set
	 */
	public void setFacultadItems(List<SelectItem> facultadItems) {
		this.facultadItems = facultadItems;
	}

	/**
	 * 
	 * <b> Permite listar los egresos registrados en la base de datos. </b>
	 * <p>
	 * [Author: Paul Jimenez, Date: 29/03/2016]
	 * </p>
	 * 
	 * @throws HiperionException
	 */
	public void buscarEgresos() throws HiperionException {

		totalPresupuesto = 0.0;

		try {

			egresoDB = egresoService.buscarEgresos(egresoBean.getPeriodo(), egresoBean.getAfectacion());
			if (egresoDB == null) {
				MessagesController.addWarn(null, HiperionMensajes.getInstancia().getString("hiperion.mensaje.warn.buscar"));
				activarTabla = false;
			} else {
				activarTabla = true;
				detEgresos = egresoService.buscarEgresos(egresoDB.getIdEgreso());

				for (DetalleEgreso egreso : detEgresos) {
					totalPresupuesto += egreso.getPresupuesto();
				}
			}

		} catch (HiperionException e) {
			throw new HiperionException(e);
		}
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
	 * @return the facultadItems
	 */
	public List<SelectItem> getFacultadItems() {
		return facultadItems;
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
	 * 
	 * <b> Permite registrar un egreso. </b>
	 * <p>
	 * [Author: HIPERION, Date: 23/02/2016]
	 * </p>
	 * 
	 * @throws HiperionException
	 * 
	 */
	public void guardarEgreso() throws HiperionException {

		boolean save = true;
		Egreso egreso = new Egreso();
		try {

			egreso = egresoService.buscarEgresos(egresoBean.getPeriodo(), egresoBean.getAfectacion());

			if (egreso == null) {

				egreso = new Egreso();
				egreso.setPeriodo(egresoBean.getPeriodo());

				Afectacion afectacion = new Afectacion();
				afectacion.setIdFacultad(egresoBean.getFacultad());
				afectacion.setIdDependencia(egresoBean.getDependencia());
				afectacion.setIdAfectacion(egresoBean.getAfectacion());

				egreso.setAfectacion(afectacion);

			} else {
				save = false;
			}
			List<DetalleEgreso> detalles = new ArrayList<>();
			if (egresosDTO.isEmpty()) {
				MessagesController.addWarn(null, HiperionMensajes.getInstancia().getString("hiperion.mensaje.war.detEgresos"));
			} else {

				for (EgresoDTO detalleEgreso : egresosDTO) {
					DetalleEgreso detalle = new DetalleEgreso();

					detalle.setPartida(detalleEgreso.getPartida());

					detalleEgreso.getPartida().setPresupuestado("A");
					egresoService.editarPartida(detalleEgreso.getPartida());

					detalle.setPresupuesto(detalleEgreso.getPresupuesto());

					detalles.add(detalle);
				}
				egresoService.guardarEgreso(egreso, detalles, save);
				MessagesController.addInfo(null, HiperionMensajes.getInstancia().getString("hiperion.mensaje.exito.save"));
				egresosDTO = new ArrayList<>();
			}

		} catch (HiperionException e) {
			MessagesController.addError(null, HiperionMensajes.getInstancia().getString("hiperion.mensaje.error.save"));
			throw new HiperionException(e);
		}
	}

	/**
	 * @return the egresoBean
	 */
	public EgresoBean getEgresoBean() {
		return egresoBean;
	}

	/**
	 * @param egresoBean
	 *            the egresoBean to set
	 */
	public void setEgresoBean(EgresoBean egresoBean) {
		this.egresoBean = egresoBean;
	}

	/**
	 * 
	 * <b> Permite agregar un registro a la tabla. </b>
	 * <p>
	 * [Author: HIPERION, Date: 25/02/2016]
	 * </p>
	 * 
	 * @return
	 * @throws HiperionException
	 */
	public void addEgreso() throws HiperionException {

		try {
			this.partida = egresoService.obtenerPartidaById(idPartida);

			if (this.presupuesto > 0) {

				double presupuesto = this.presupuesto.doubleValue();

				EgresoDTO egreso = new EgresoDTO(this.partida, presupuesto);

				Egreso egresoDB = egresoService.buscarEgresos(egresoBean.getPeriodo(), egresoBean.getAfectacion());

				int cont = 0;
				if (egresoDB != null) {

					List<DetalleEgreso> detEgresos = egresoService.buscarEgresos(egresoDB.getIdEgreso());

					for (DetalleEgreso detEgreso : detEgresos) {
						if (detEgreso.getPartida().getPartida().equals(this.partida.getPartida())) {
							cont++;
						}
					}

				}

				if (cont > 0) {
					MessagesController.addWarn(null, "Ya existe ingresada una partida similar ");
				} else {
					egresosDTO.add(egreso);

					this.partida = null;
					this.presupuesto = null;
				}

			} else {
				MessagesController.addError(null, "El presupuesto debe ser mayor de cero, valor invalido.");
			}

		} catch (HiperionException e) {
			MessagesController.addError(null, HiperionMensajes.getInstancia().getString("hiperion.mensaje.error.save"));
			throw new HiperionException(e);
		}
	}

	/**
	 * 
	 * <b> Permite editar un registro. </b>
	 * <p>
	 * [Author: HIPERION, Date: 25/02/2016]
	 * </p>
	 * 
	 * @param event
	 */
	public void onEdit(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Item Edited", ((EgresoDTO) event.getObject()).getPartida().toString());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	/**
	 * 
	 * <b> permite eliminar un registro de la tabla</b>
	 * <p>
	 * [Author: Paul Jimenez, Date: Mar 3, 2014]
	 * </p>
	 * 
	 * @param event
	 */
	public void onCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Item Cancelled");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		egresosDTO.remove((EgresoDTO) event.getObject());
	}

	/**
	 * @return the egresosDTO
	 */
	public List<EgresoDTO> getEgresosDTO() {
		return egresosDTO;
	}

	/**
	 * @param egresosDTO
	 *            the egresosDTO to set
	 */
	public void setEgresosDTO(List<EgresoDTO> egresosDTO) {
		this.egresosDTO = egresosDTO;
	}

	/**
	 * @return the partida
	 */
	public Partida getPartida() {
		return partida;
	}

	/**
	 * @param partida
	 *            the partida to set
	 */
	public void setPartida(Partida partida) {
		this.partida = partida;
	}

	/**
	 * @return the presupuesto
	 */
	public Double getPresupuesto() {
		return presupuesto;
	}

	/**
	 * @param presupuesto
	 *            the presupuesto to set
	 */
	public void setPresupuesto(Double presupuesto) {
		this.presupuesto = presupuesto;
	}

	/**
	 * @return the idPartida
	 */
	public Long getIdPartida() {
		return idPartida;
	}

	/**
	 * @param idPartida
	 *            the idPartida to set
	 */
	public void setIdPartida(Long idPartida) {
		this.idPartida = idPartida;
	}

	/**
	 * @param partidaItems
	 *            the partidaItems to set
	 */
	public void setPartidaItems(List<SelectItem> partidaItems) {
		this.partidaItems = partidaItems;
	}

	/**
	 * @return the egresoDB
	 */
	public Egreso getEgresoDB() {
		return egresoDB;
	}

	/**
	 * @param egresoDB
	 *            the egresoDB to set
	 */
	public void setEgresoDB(Egreso egresoDB) {
		this.egresoDB = egresoDB;
	}

	/**
	 * @return the activarTabla
	 */
	public boolean isActivarTabla() {
		return activarTabla;
	}

	/**
	 * @param activarTabla
	 *            the activarTabla to set
	 */
	public void setActivarTabla(boolean activarTabla) {
		this.activarTabla = activarTabla;
	}

	/**
	 * @return the detEgresos
	 */
	public List<DetalleEgreso> getDetEgresos() {
		return detEgresos;
	}

	/**
	 * @param detEgresos
	 *            the detEgresos to set
	 */
	public void setDetEgresos(List<DetalleEgreso> detEgresos) {
		this.detEgresos = detEgresos;
	}

	/**
	 * @return the totalPresupuesto
	 */
	public double getTotalPresupuesto() {
		return totalPresupuesto;
	}

	/**
	 * @param totalPresupuesto
	 *            the totalPresupuesto to set
	 */
	public void setTotalPresupuesto(double totalPresupuesto) {
		this.totalPresupuesto = totalPresupuesto;
	}

}
