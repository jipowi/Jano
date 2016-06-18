/*
 * Copyright 2014 JIPOVI Solutions - ECUADOR 
 * Todos los derechos reservados
 */
package ec.com.uce.jano.web.backings;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.primefaces.event.RowEditEvent;

import ec.com.uce.jano.comun.HiperionException;
import ec.com.uce.jano.dto.PartidaDTO;
import ec.com.uce.jano.entities.Partida;
import ec.com.uce.jano.servicio.CatalogoService;
import ec.com.uce.jano.servicio.DetalleCatalogoService;
import ec.com.uce.jano.servicio.EgresoService;
import ec.com.uce.jano.web.beans.PartidaBean;
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
public class PartidaBacking implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{partidaBean}")
	private PartidaBean partidaBean;

	@EJB
	private CatalogoService catalogoService;
	@EJB
	private EgresoService egresoService;
	@EJB
	private DetalleCatalogoService detalleCatalogoService;

	private List<PartidaDTO> partidas = new ArrayList<>();
	private List<PartidaDTO> partidasDB;
	private String tipoPartida;
	private List<SelectItem> partidaItems;
	private boolean activarTabla;

	/**
	 * 
	 * <b> Permite guardar las partidas. </b>
	 * <p>
	 * [Author: HIPERION, Date: 24/02/2016]
	 * </p>
	 * 
	 * @throws HiperionException
	 */
	public void guardarPartida() throws HiperionException {

		try {
			
			for (PartidaDTO partidaDTO : partidas) {
				Partida partida = new Partida();
				partida.setPartida(partidaDTO.getPartida());
				partida.setTipoPartida(partidaDTO.getTipoPartida());

				egresoService.guardarPartida(partida);
			}

			MessagesController.addInfo(null, HiperionMensajes.getInstancia().getString("hiperion.mensaje.exito.save"));
			partidas.clear();
			

		} catch (HiperionException e) {
			MessagesController.addError(null, HiperionMensajes.getInstancia().getString("hiperion.mensaje.error.save"));
			throw new HiperionException(e);
		}
	}

	/**
	 * 
	 * <b> Permite listar las partidas ingresar por tipo de partida </b>
	 * <p>
	 * [Author: Paul Jimenez, Date: 24/03/2016]
	 * </p>
	 * 
	 * @throws HiperionException
	 */
	public void buscarPartidas() throws HiperionException {
		try {

			partidasDB = new ArrayList<>();

			List<Partida> partidasTemp = egresoService.obtenerPartidas(tipoPartida);

			if (partidasTemp.isEmpty()) {
				MessagesController.addWarn(null, HiperionMensajes.getInstancia().getString("hiperion.mensaje.warn.buscar"));
				activarTabla = false;
			} else {
				for (Partida partida : partidasTemp) {
					PartidaDTO partidaDTO = new PartidaDTO();

					partidaDTO.setPartida(partida.getPartida());
					partidaDTO.setTipoPartida(partida.getTipoPartida());

					partidasDB.add(partidaDTO);
				}
				activarTabla = true;
			}
		} catch (HiperionException e) {
			throw new HiperionException(e);
		}
	}

	/**
	 * @return the partidaBean
	 */
	public PartidaBean getPartidaBean() {
		return partidaBean;
	}

	/**
	 * @param partidaBean
	 *            the partidaBean to set
	 */
	public void setPartidaBean(PartidaBean partidaBean) {
		this.partidaBean = partidaBean;
	}

	/**
	 * @return the partidas
	 */
	public List<PartidaDTO> getPartidas() {
		return partidas;
	}

	/**
	 * @param partidas
	 *            the partidas to set
	 */
	public void setPartidas(List<PartidaDTO> partidas) {
		this.partidas = partidas;
	}

	/**
	 * @return the tipoPartida
	 */
	public String getTipoPartida() {
		return tipoPartida;
	}

	/**
	 * @param tipoPartida
	 *            the tipoPartida to set
	 */
	public void setTipoPartida(String tipoPartida) {
		this.tipoPartida = tipoPartida;
	}

	/**
	 * @return the periodoItems
	 */
	public List<SelectItem> getPartidaItems() {
		try {
			this.partidaItems = new ArrayList<SelectItem>();

			List<Partida> partidas = egresoService.obtenerPartidas("Egreso");

			for (Partida partida : partidas) {
				SelectItem selectItem = new SelectItem(partida.getIdPartida(), partida.getPartida());
				partidaItems.add(selectItem);
			}

		} catch (HiperionException e) {
			e.printStackTrace();
		}
		return partidaItems;

	}

	/**
	 * 
	 * <b> Permite agregar una partida . </b>
	 * <p>
	 * [Author: Paul Jimenez, Date: 24/03/2016]
	 * </p>
	 * 
	 */
	public void addPartida() {
		PartidaDTO item = new PartidaDTO(partidaBean.getTipoPartida(), partidaBean.getPartida());

		partidas.add(item);

		partidaBean.setTipoPartida(null);
		partidaBean.setPartida(null);
		
		activarTabla = true;

	}

	/**
	 * 
	 * <b> Permite editar una partida. </b>
	 * <p>
	 * [Author: Paul Jimenez, Date: 24/03/2016]
	 * </p>
	 * 
	 * @param event
	 */
	public void editarPartida(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Item Edited", ((PartidaDTO) event.getObject()).getPartida());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	/**
	 * 
	 * <b> Permite eliminar una partida. </b>
	 * <p>
	 * [Author: Paul Jimenez, Date: 24/03/2016]
	 * </p>
	 * 
	 * @param event
	 */
	public void eliminarPartida(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Item Cancelled");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		partidas.remove((PartidaDTO) event.getObject());
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
	 * @return the partidasDB
	 */
	public List<PartidaDTO> getPartidasDB() {
		return partidasDB;
	}

	/**
	 * @param partidasDB
	 *            the partidasDB to set
	 */
	public void setPartidasDB(List<PartidaDTO> partidasDB) {
		this.partidasDB = partidasDB;
	}

}
