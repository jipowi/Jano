/*
 * Copyright 2014 JIPOVI Solutions - ECUADOR 
 * Todos los derechos reservados
 */
package ec.com.uce.jano.web.backings;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import ec.com.uce.jano.comun.HiperionException;
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
 * @author HIPERION
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

		Partida partida = new Partida();

		try {

			partida.setPartida(partidaBean.getPartida());
			partida.setTipoPartida(partidaBean.getTipoPartida());

			egresoService.guardarPartida(partida);
			MessagesController.addInfo(null, HiperionMensajes.getInstancia().getString("hiperion.mensaje.exito.save"));

		} catch (HiperionException e) {
			MessagesController.addError(null, HiperionMensajes.getInstancia().getString("hiperion.mensaje.error.save"));
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

}
