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
import ec.com.uce.jano.dto.IngresoDTO;
import ec.com.uce.jano.entities.Afectacion;
import ec.com.uce.jano.entities.Catalogo;
import ec.com.uce.jano.entities.DetalleCatalogo;
import ec.com.uce.jano.entities.DetalleIngreso;
import ec.com.uce.jano.entities.Ingreso;
import ec.com.uce.jano.entities.Partida;
import ec.com.uce.jano.servicio.AfectacionService;
import ec.com.uce.jano.servicio.CatalogoService;
import ec.com.uce.jano.servicio.DetalleCatalogoService;
import ec.com.uce.jano.servicio.EgresoService;
import ec.com.uce.jano.web.beans.IngresoBean;
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
public class IngresoBacking implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{ingresoBean}")
	private IngresoBean ingresoBean;

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
	private List<IngresoDTO> ingresosDTO = new ArrayList<>();
	private List<DetalleIngreso> detIngresos = new ArrayList<>();
	private Ingreso ingresoDB;
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

			List<Partida> partidas = egresoService.obtenerPartidas("Ingreso");

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

			List<Afectacion> dependencias = afectacionService.obtenerDependencias(ingresoBean.getFacultad());

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

			List<Afectacion> departamentos = afectacionService.obtenerDepartamentos(ingresoBean.getFacultad(), ingresoBean.getDependencia());

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
	 * <b> Permite listar los ingresos registrados en la base de datos. </b>
	 * <p>
	 * [Author: Paul Jimenez, Date: 29/03/2016]
	 * </p>
	 * 
	 * @throws HiperionException
	 */
	public void buscarIngresos() throws HiperionException {

		totalPresupuesto = 0.0;

		try {
			ingresoDB = egresoService.buscarIngresos(ingresoBean.getPeriodo(), ingresoBean.getAfectacion());
			if (ingresoDB == null) {
				MessagesController.addWarn(null, HiperionMensajes.getInstancia().getString("hiperion.mensaje.warn.buscar"));
				activarTabla = false;
			} else {
				activarTabla = true;
				detIngresos = egresoService.buscarIngresos(ingresoDB.getIdIngreso());

				for (DetalleIngreso ingreso : detIngresos) {
					totalPresupuesto += ingreso.getPresupuestoIngreso();
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
	 * <b> Permite registrar un ingreso. </b>
	 * <p>
	 * [Author: HIPERION, Date: 23/02/2016]
	 * </p>
	 * 
	 * @throws HiperionException
	 * 
	 */
	public void guardarIngreso() throws HiperionException {

		boolean save = true;
		Ingreso ingreso = new Ingreso();
		try {

			ingreso = egresoService.buscarIngresos(ingresoBean.getPeriodo(), ingresoBean.getAfectacion());

			if (ingreso == null) {

				ingreso = new Ingreso();
				ingreso.setPeriodoIngreso(ingresoBean.getPeriodo());

				Afectacion afectacion = new Afectacion();
				afectacion.setIdFacultad(ingresoBean.getFacultad());
				afectacion.setIdDependencia(ingresoBean.getDependencia());
				afectacion.setIdAfectacion(ingresoBean.getAfectacion());

				ingreso.setAfectacion(afectacion);
			} else {
				save = false;
			}
			List<DetalleIngreso> detalles = new ArrayList<>();
			if (ingresosDTO.isEmpty()) {
				MessagesController.addWarn(null, HiperionMensajes.getInstancia().getString("hiperion.mensaje.war.detIngreso"));
			} else {

				for (IngresoDTO detalleIngreso : ingresosDTO) {
					DetalleIngreso detalle = new DetalleIngreso();
					

					detalle.setPartida(detalleIngreso.getPartida());
					
					detalleIngreso.getPartida().setPresupuestado("A");
					egresoService.editarPartida(detalleIngreso.getPartida());

					detalle.setPresupuestoIngreso(detalleIngreso.getPresupuesto());

					detalles.add(detalle);
				}
				
				egresoService.guardarIngreso(ingreso, detalles, save);
				
				MessagesController.addInfo(null, HiperionMensajes.getInstancia().getString("hiperion.mensaje.exito.save"));
				
				ingresosDTO = new ArrayList<>();
			}

		} catch (HiperionException e) {
			MessagesController.addError(null, HiperionMensajes.getInstancia().getString("hiperion.mensaje.error.save"));
			throw new HiperionException(e);
		}
	}

	/**
	 * @return the ingresoBean
	 */
	public IngresoBean getIngresoBean() {
		return ingresoBean;
	}

	/**
	 * @param ingresoBean
	 *            the ingresoBean to set
	 */
	public void setIngresoBean(IngresoBean ingresoBean) {
		this.ingresoBean = ingresoBean;
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
	public void addIngreso() throws HiperionException {

		try {
			this.partida = egresoService.obtenerPartidaById(idPartida);

			IngresoDTO ingreso = new IngresoDTO(this.partida, this.presupuesto);

			Ingreso ingresoDB = egresoService.buscarIngresos(ingresoBean.getPeriodo(), ingresoBean.getAfectacion());

			int cont = 0;
			if (ingresoDB != null) {

				List<DetalleIngreso> detIngresos = egresoService.buscarIngresos(ingresoDB.getIdIngreso());

				for (DetalleIngreso detIngreso : detIngresos) {
					if (detIngreso.getPartida().getPartida().equals(this.partida.getPartida())) {
						cont++;
					}
				}

			}

			if (cont > 0) {
				MessagesController.addWarn(null, "Ya existe ingresada una partida similar ");
			} else {
				ingresosDTO.add(ingreso);
				this.partida = null;
				this.presupuesto = null;
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
		FacesMessage msg = new FacesMessage("Item Edited", ((IngresoDTO) event.getObject()).getPartida().toString());
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
		ingresosDTO.remove((IngresoDTO) event.getObject());
	}

	/**
	 * @return the ingresosDTO
	 */
	public List<IngresoDTO> getIngresosDTO() {
		return ingresosDTO;
	}

	/**
	 * @param ingresosDTO
	 *            the ingresosDTO to set
	 */
	public void setIngresosDTO(List<IngresoDTO> ingresosDTO) {
		this.ingresosDTO = ingresosDTO;
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
	 * @return the detIngresos
	 */
	public List<DetalleIngreso> getDetIngresos() {
		return detIngresos;
	}

	/**
	 * @param detIngresos
	 *            the detIngresos to set
	 */
	public void setDetIngresos(List<DetalleIngreso> detIngresos) {
		this.detIngresos = detIngresos;
	}

	/**
	 * @return the ingresoDB
	 */
	public Ingreso getIngresoDB() {
		return ingresoDB;
	}

	/**
	 * @param ingresoDB
	 *            the ingresoDB to set
	 */
	public void setIngresoDB(Ingreso ingresoDB) {
		this.ingresoDB = ingresoDB;
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
