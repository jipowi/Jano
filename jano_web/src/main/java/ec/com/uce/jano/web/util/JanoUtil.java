/*
 * Copyright 2014 JIPOVI Solutions - ECUADOR 
 * Todos los derechos reservados
 */
package ec.com.uce.jano.web.util;

import org.apache.log4j.Logger;

/**
 * <b> Incluir aqui la descripcion de la clase. </b>
 * 
 * @author kruger
 * @version 1.0,18/06/2016
 * @since JDK1.6
 */
public class JanoUtil {

	private static Integer longitudClientes = 3;

	// Unica variable de la clase
	private static JanoUtil INSTANCIA = new JanoUtil();

	// Constructor Privado Singleton

	Logger log = Logger.getLogger(JanoUtil.class);

	// Devuelve la unica instancia de la clase
	public static JanoUtil getInstancia() {
		return INSTANCIA;
	}

	/**
	 * 
	 */
	public JanoUtil() {
		super();
	}

	/**
	 * 
	 * <b> Permite obtener un codigo secuencial. </b>
	 * <p>
	 * [Author: Paul Jimenez, Date: 18/06/2016]
	 * </p>
	 * 
	 * @param codigo
	 * @return
	 */
	public static String obtenerCodigoSecuencial(Integer codigo) {
		int codigoLength = String.valueOf(codigo).length();
		int cantCeros = longitudClientes - codigoLength;
		StringBuffer sb = new StringBuffer(cantCeros + 1);
		sb.append("UCE");
		for (int i = 0; i <= cantCeros; i++) {
			sb.append("0");
		}

		return sb.toString() + codigo;
	}
}
