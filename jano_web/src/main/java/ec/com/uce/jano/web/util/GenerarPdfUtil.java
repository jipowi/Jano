package ec.com.uce.jano.web.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.xml.transform.TransformerException;

import org.jdom.JDOMException;

import com.lowagie.text.DocumentException;

import ec.com.kruger.framework.common.util.pdf.HtmltoPDF;
import ec.com.uce.jano.dto.CompromisoDTO;
import ec.com.uce.jano.entities.Gasto;

public class GenerarPdfUtil implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * <b> Permite generar un archivo PDF. </b>
	 * <p>
	 * [Author: kruger, Date: 12/09/2016]
	 * </p>
	 * 
	 * @param compromisoDTO
	 * @return
	 * @throws Exception
	 */
	public static byte[] generarAchivoPDFCompromiso(CompromisoDTO compromisoDTO, List<Gasto> gastos) throws Exception {
		String pHtml = XSLUtil.getInstancia().obtenerHtmlCompromiso(compromisoDTO, gastos);

		byte[] contenido = obtenerCadenaBytes(pHtml);

		// Agregar marca de agua y piede de pagina al Pdf
		return ConcatenadorPdf.numerarMarcar(contenido, Boolean.TRUE, Boolean.TRUE, ConstantesUtil.PATH_MARCA_AGUA_PDF);
	}

	/**
	 * 
	 * <b> Permite obtener un cadena de bytes para el documento. </b>
	 * <p>
	 * [Author: kruger, Date: 12/09/2016]
	 * </p>
	 * 
	 * @param contenido
	 * @return
	 * @throws IOException
	 * @throws DocumentException
	 */
	public static byte[] generarPDFs(byte[] contenido) throws IOException, DocumentException {
		return ConcatenadorPdf.numerarMarcar(contenido, Boolean.TRUE, Boolean.TRUE, ConstantesUtil.PATH_MARCA_AGUA_PDF);
	}

	/**
	 * 
	 * <b> Permite obtener la cadena de bytes del archivo HTML. </b>
	 * <p>
	 * [Author: kruger, Date: 12/09/2016]
	 * </p>
	 * 
	 * @param pHtml
	 * @return
	 * @throws FileNotFoundException
	 * @throws JDOMException
	 * @throws IOException
	 * @throws TransformerException
	 */
	private static byte[] obtenerCadenaBytes(String pHtml) throws FileNotFoundException, JDOMException, IOException, TransformerException {
		pHtml = pHtml.replace(ConstantesUtil.ENCABEZADO_ISO_XML, "");
		pHtml = pHtml.replace(ConstantesUtil.ENCABEZADO_XML, "");
		pHtml = pHtml.replace(ConstantesUtil.ENCABEZADO_XML_STANDALONE, "");
		pHtml = pHtml.replace(ConstantesUtil.ENCABEZADO_XML, "");

		HtmltoPDF htmltoPDF = new HtmltoPDF(FacesContext.getCurrentInstance().getExternalContext().getRealPath(ConstantesUtil.DIRECTORIO_PLANTILLA_XHTML2FO));
		HashMap<String, String> parametros = new HashMap<String, String>();
		byte contenido[] = htmltoPDF.convertir(pHtml, "", "", parametros, null);
		return contenido;
	}
}
