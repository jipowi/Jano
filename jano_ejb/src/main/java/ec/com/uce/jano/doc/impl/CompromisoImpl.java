package ec.com.uce.jano.doc.impl;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;

import org.apache.commons.lang.StringEscapeUtils;

import ec.com.uce.jano.doc.GenerarDocumentoCompromiso;
import ec.com.uce.jano.dto.AfectacionDTO;
import ec.com.uce.jano.dto.CompromisoDTO;
import ec.com.uce.jano.entities.Gasto;

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
	private static String tagInicioCompromisos = "<compromisos>";
	private static String tagInicioCodigo = "<codigo>";
	private static String tagFinCodigo = "</codigo>";
	private static String tagInicioPartida = "<partida>";
	private static String tagFinPartida = "</partida>";
	private static String tagInicioValor = "<valor>";
	private static String tagFinValor = "</valor>";
	private static String tagFinCompromisos = "</compromisos>";
	private static String tagInicioTotal = "<total>";
	private static String tagFinTotal = "</total>";
	private static String tagInicioAfectaciones = "<afectaciones>";
	private static String tagFinAfectaciones = "</afectaciones>";
	private static String tagInicioFacultad = "<facultad>";
	private static String tagFinFacultad = "</facultad>";
	private static String tagInicioDependencia = "<dependencia>";
	private static String tagFinDependencia = "</dependencia>";
	private static String tagInicioAfectacion = "<afectacion>";
	private static String tagFinAfectacion = "</afectacion>";

	@Override
	public String generarXmlCompromiso(CompromisoDTO compromisoDTO, List<Gasto> gastos, List<AfectacionDTO> afectacionDTOs) {

		Date fecDate = new Date();

		StringBuffer buffer = new StringBuffer();
		buffer.append(tagInicioFecha).append(StringEscapeUtils.escapeXml(fecDate.toString())).append(tagFinFecha);
		buffer.append(tagInicioBeneficiario).append(StringEscapeUtils.escapeXml(compromisoDTO.getBeneficiario())).append(tagFinBeneficiario);

		double total = 0.0;
		for (Gasto gasto : gastos) {
			buffer.append(tagInicioCompromisos);
			buffer.append(tagInicioCodigo).append(StringEscapeUtils.escapeXml(gasto.getCur())).append(tagFinCodigo);
			buffer.append(tagInicioPartida).append(StringEscapeUtils.escapeXml(gasto.getPartida().getPartida())).append(tagFinPartida);

			String valor = gasto.getValorGasto() + "";
			total += gasto.getValorGasto();
			buffer.append(tagInicioValor).append(StringEscapeUtils.escapeXml(valor)).append(tagFinValor);
			buffer.append(tagFinCompromisos);
		}
		buffer.append(tagInicioTotal).append(StringEscapeUtils.escapeXml(total + "")).append(tagFinTotal);

		for (AfectacionDTO afectacionDTO : afectacionDTOs) {
			buffer.append(tagInicioAfectaciones);
			buffer.append(tagInicioFacultad).append(StringEscapeUtils.escapeXml(afectacionDTO.getFacultad())).append(tagFinFacultad);
			buffer.append(tagInicioDependencia).append(StringEscapeUtils.escapeXml(afectacionDTO.getDependencia())).append(tagFinDependencia);
			buffer.append(tagInicioAfectacion).append(StringEscapeUtils.escapeXml(afectacionDTO.getAfectacion())).append(tagFinAfectacion);
			buffer.append(tagFinAfectaciones);
		}

		return buffer.toString();
	}

}
