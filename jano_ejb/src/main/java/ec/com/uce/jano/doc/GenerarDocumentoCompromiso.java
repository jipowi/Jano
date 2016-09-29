package ec.com.uce.jano.doc;

import java.util.List;

import javax.ejb.Local;

import ec.com.uce.jano.comun.HiperionException;
import ec.com.uce.jano.dto.AfectacionDTO;
import ec.com.uce.jano.dto.CompromisoDTO;
import ec.com.uce.jano.entities.Gasto;

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
	String generarXmlCompromiso(CompromisoDTO compromisoDTO, List<Gasto> gastos, List<AfectacionDTO> afectacionDTOs) throws HiperionException;

}
