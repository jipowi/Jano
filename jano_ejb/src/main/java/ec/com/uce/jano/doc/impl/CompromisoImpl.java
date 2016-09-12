package ec.com.uce.jano.doc.impl;

import java.util.Date;

import javax.ejb.Stateless;

import org.apache.commons.lang.StringEscapeUtils;

import ec.com.uce.jano.doc.GenerarDocumentoCompromiso;
import ec.com.uce.jano.dto.CompromisoDTO;

/**
 * 
 * <b> Clase que se encarga de generar el documento PDF.. </b>
 * 
 * @author kruger
 * @version 1.0,12/09/2016
 * @since JDK1.6
 */
@Stateless
public class CompromisoImpl implements GenerarDocumentoCompromiso {

	private static String tagInicioFecha = "<fechaActual>";
	private static String tagFinFecha = "</fechaActual>";
	private static String tagInicioBeneficiario = "<beneficiario>";
	private static String tagFinBeneficiario = "</beneficiario>";

	@Override
	public String generarXmlCompromiso(CompromisoDTO compromisoDTO) {

		Date fecDate = new Date();

		StringBuffer buffer = new StringBuffer();
		buffer.append(tagInicioFecha).append(StringEscapeUtils.escapeXml(fecDate.toString())).append(tagFinFecha);
		buffer.append(tagInicioBeneficiario).append(StringEscapeUtils.escapeXml(compromisoDTO.getBeneficiario())).append(tagFinBeneficiario);

		return buffer.toString();
	}

}
