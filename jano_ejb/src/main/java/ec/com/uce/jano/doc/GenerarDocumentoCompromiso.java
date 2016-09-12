package ec.com.uce.jano.doc;

import javax.ejb.Local;

import ec.com.uce.jano.comun.HiperionException;
import ec.com.uce.jano.dto.CompromisoDTO;

/**
 * 
 * <b> Interface de generación de reporte. </b>
 * 
 * @author kruger
 * @version 1.0,08/09/2016
 * @since JDK1.6
 */
@Local
public interface GenerarDocumentoCompromiso {

	/**
	 * 
	 * <b> Permite generar el XMl del documento compromiso. </b>
	 * <p>
	 * [Author: kruger, Date: 08/09/2016]
	 * </p>
	 * 
	 * @param compromisoDTO
	 * @return
	 * @throws HiperionException
	 */
	String generarXmlCompromiso(CompromisoDTO compromisoDTO) throws HiperionException;

}
