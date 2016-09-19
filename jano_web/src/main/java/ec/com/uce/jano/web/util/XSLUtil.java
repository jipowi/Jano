/**
 * 
 */
package ec.com.uce.jano.web.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.log4j.Logger;
import org.jdom.Document;

import ec.com.kruger.framework.common.util.TransformerUtil;
import ec.com.uce.jano.comun.HiperionException;
import ec.com.uce.jano.doc.GenerarDocumentoCompromiso;
import ec.com.uce.jano.dto.CompromisoDTO;
import ec.com.uce.jano.entities.Gasto;
import ec.com.uce.jano.xsl.XSLHelper;

/**
 * 
 * <b> Clase utilitario para manejar el XSL. </b>
 * 
 * @author kruger
 * @version 1.0,12/09/2016
 * @since JDK1.6
 */

public class XSLUtil {

	private XSLUtil() {
	}

	private static final XSLUtil INSTANCIA = new XSLUtil();

	public static XSLUtil getInstancia() {
		return INSTANCIA;
	}

	private static final String tagInicioDocumento = "<documento>";
	private static final String tagFinDocumento = "</documento>";

	Logger log = Logger.getLogger(XSLUtil.class);

	/**
	 * 
	 * <b> Permite generar un contenido XML para el compromiso. </b>
	 * <p>
	 * [Author: kruger, Date: 12/09/2016]
	 * </p>
	 * 
	 * @param compromisoDTO
	 * @return
	 */
	public String generarXmlCompromiso(CompromisoDTO compromisoDTO, List<Gasto> gastos) {

		StringBuilder xml = new StringBuilder();

		try {
			xml.append(tagInicioDocumento);

			try {
				String nombreClase = "java:app/jano_web/CompromisoImpl";
				GenerarDocumentoCompromiso generarDocumento = (GenerarDocumentoCompromiso) getObjectByJndi(nombreClase);
				xml.append(generarDocumento.generarXmlCompromiso(compromisoDTO, gastos));

			} catch (Exception e) {
				log.error("Error, generacion xml reporte, e{}", e);
			}
			xml.append(tagFinDocumento);
		} catch (Exception e) {
			log.error("Error", e);

		}
		return xml.toString();
	}

	/**
	 * 
	 * <b> Permite generar el HTML del documento. </b>
	 * <p>
	 * [Author: kruger, Date: 12/09/2016]
	 * </p>
	 * 
	 * @param compromisoDTO
	 * @return
	 */
	public String obtenerHtmlCompromiso(CompromisoDTO compromisoDTO, List<Gasto> gastos) {
		String html = null;
		try {
			InputStream in = XSLHelper.class.getResourceAsStream("CompromisoHTML.xsl");
			InputStreamReader is = new InputStreamReader(in);
			StringBuilder sb = new StringBuilder();
			BufferedReader br = new BufferedReader(is);
			String read = br.readLine();

			while (read != null) {
				sb.append(read);
				read = br.readLine();

			}

			String contenidoXSL = sb.toString();

			// Se genera el XML con los datos del correo
			String contenidoXml = generarXmlCompromiso(compromisoDTO, gastos);
			Document docXML = TransformerUtil.stringToXMLDocument(contenidoXml.toString());
			Document docXSL = TransformerUtil.stringToXML(contenidoXSL);
			Document result = TransformerUtil.transformar(docXML, docXSL);
			html = TransformerUtil.xmlToString(result);
			html = html.replaceAll("&lt;", "<").replaceAll("&gt;", ">").replaceAll("&amp;", "&").replace("UTF-8", "ISO-8859-1");

		} catch (Exception e) {
			log.error("Error ", e);

		}

		return html;
	}

	/**
	 * 
	 * <b> Permite obtener un servicio de seguridad. </b>
	 * <p>
	 * [Author: kruger, Date: 12/09/2016]
	 * </p>
	 * 
	 * @param jndiName
	 * @return
	 * @throws HiperionException
	 */
	public Object getObjectByJndi(String jndiName) throws HiperionException {
		try {
			return getContext().lookup(jndiName);
		} catch (Exception e) {
			throw new HiperionException(e);
		}
	}

	/**
	 * 
	 * <b> Permite inicializar el InitialContext. </b>
	 * <p>
	 * [Author: kruger, Date: 12/09/2016]
	 * </p>
	 * 
	 * @return
	 * @throws NamingException
	 */
	public Context getContext() throws NamingException {
		return new InitialContext();
	}

}
