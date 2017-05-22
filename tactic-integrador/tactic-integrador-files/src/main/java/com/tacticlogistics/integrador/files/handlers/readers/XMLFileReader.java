package com.tacticlogistics.integrador.files.handlers.readers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import lombok.val;

@Component
public class XMLFileReader implements Reader {

	private static final String PATH_NUMERO_ORDEN = "//warehouseShippingOrderIdentification/uniqueCreatorIdentification/text()";
	private static final String PATH_BODEGA = "//purchaseOrderIdentification/contentOwner/additionalPartyIdentification[additionalPartyIdentificationType='BUYER_ASSIGNED_IDENTIFIER_FOR_A_PARTY']/additionalPartyIdentificationValue/text()";
	private static final String PATH_PUNTO_ENTREGA = "//extension/shipToNameAndAddress";
	private static final String PATH_TERCERO_IDENTIFICACION = "EntityInfo/EntityTaxId/text()";
	private static final String PATH_TERCERO_NOMBRE = "name/text()";
	private static final String PATH_CIUDAD = "city/text()";
	private static final String PATH_DIRECCION = "streetAddressOne/text()";
	private static final String PATH_LINEAS = "//warehouseTradeItemDescription";
	private static final String PATH_LINEA_SKU_CODIGO = "tradeItemIdentification/additionalTradeItemIdentification[additionalTradeItemIdentificationType='SUPPLIER_ASSIGNED']/additionalTradeItemIdentificationValue/text()";
	private static final String PATH_LINEA_SKU_NOMBRE = "tradeItemIdentification/additionalTradeItemIdentification[additionalTradeItemIdentificationType='FOR_INTERNAL_USE_1']/additionalTradeItemIdentificationValue/text()";
	private static final String PATH_LINEA_CANTIDAD = "quantityRequested/value/text()";
	private static final String PATH_DOCUMENTO_CLIENTE = "//purchaseOrderIdentification/uniqueCreatorIdentification/text()";
	
	@Override
	public String read(Path input) throws IOException {
		String result = "";
		try {
			File file = input.toFile();
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setNamespaceAware(true);

			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(file);
			document.getDocumentElement().normalize();

			XPathFactory xpathFactory = XPathFactory.newInstance();
			XPath xpath = xpathFactory.newXPath();

			String numeroOrden = getNumeroOrden(document, xpath);
			String documentoCliente = getDocumentoCliente(document, xpath);
			String bodega = getBodega(document, xpath);
			val puntoEntrega = getPuntoEntrega(document, xpath);
			val lineas = getLineas(document, xpath);

			StringBuilder sb = new StringBuilder();

			sb.append("NUMERO ORDEN").append("\t");
			sb.append("SKU").append("\t");
			sb.append("DESCRIPCION").append("\t");
			sb.append("UNIDADES").append("\t");
			sb.append("BODEGA").append("\t");
			sb.append("VALOR DECLARADO x UNIDAD").append("\t");
			sb.append("CROSSDOCK").append("\t");
			sb.append("EVENTO").append("\t");
			sb.append("FEMI").append("\t");
			sb.append("FEMA").append("\t");
			sb.append("CANAL").append("\t");
			sb.append("NIT CLIENTE FINAL").append("\t");
			sb.append("NOMBRE CLIENTE FINAL").append("\t");
			sb.append("CIUDAD").append("\t");
			sb.append("DIRECCION").append("\t");
			sb.append("CLIENTE RETIRA?").append("\t");
			sb.append("DOC REF CLIENTE").append("\n");

			for (Map<String, String> map : lineas) {
				sb.append(numeroOrden).append("\t");
				sb.append(map.get("sku_codigo")).append("\t");
				sb.append(map.get("sku_nombre")).append("\t");
				sb.append(map.get("cantidad")).append("\t");
				sb.append(bodega).append("\t");
				sb.append("???").append("\t");
				sb.append("???").append("\t");
				sb.append("???").append("\t");
				sb.append("???").append("\t");
				sb.append("???").append("\t");
				sb.append("NO APLICA").append("\t");
				sb.append(puntoEntrega.get("tercero_identificacion")).append("\t");
				sb.append(puntoEntrega.get("tercero_nombre")).append("\t");
				sb.append(puntoEntrega.get("ciudad_codigo")).append("\t");
				sb.append(puntoEntrega.get("direccion")).append("\t");
				sb.append("NO APLICA").append("\t");
				sb.append(documentoCliente).append("\n");
			}
			
			result = sb.toString();
		} catch (ParserConfigurationException | XPathExpressionException | SAXException e) {
			e.printStackTrace();
			throw new RuntimeException(
					"No fue posible interpretar el contenido del archivo " + input.getFileName().toString());
		}
		System.out.println("------------------------------------------------------------------------------------");
		System.out.println(input.getFileName().toString());
		System.out.println(result);
		System.out.println("------------------------------------------------------------------------------------");
		return result;
	}

	private static String getNumeroOrden(Document document, XPath xpath) throws XPathExpressionException {
		String result;

		XPathExpression expr = xpath.compile(PATH_NUMERO_ORDEN);
		result = (String) expr.evaluate(document, XPathConstants.STRING);

		return result;
	}
	
	private static String getDocumentoCliente(Document document, XPath xpath) throws XPathExpressionException {
		String result;

		XPathExpression expr = xpath.compile(PATH_DOCUMENTO_CLIENTE);
		result = (String) expr.evaluate(document, XPathConstants.STRING);

		return result;
	}


	private static String getBodega(Document document, XPath xpath) throws XPathExpressionException {
		String result;

		XPathExpression expr = xpath.compile(PATH_BODEGA);
		result = (String) expr.evaluate(document, XPathConstants.STRING);

		return result;
	}

	private static Map<String, String> getPuntoEntrega(Document document, XPath xpath) throws XPathExpressionException {
		Map<String, String> result = new HashMap<>();

		XPathExpression expr = xpath.compile(PATH_PUNTO_ENTREGA);
		Node n = (Node) expr.evaluate(document, XPathConstants.NODE);

		String terceroIdentificacion = getTerceroIdentificacion(n, xpath);
		String terceroNombre = getTerceroNombre(n, xpath);
		String ciudadCodigo = getCiudadCodigo(n, xpath);
		String direccion = getDireccion(n, xpath);

		result.put("tercero_identificacion", terceroIdentificacion);
		result.put("tercero_nombre", terceroNombre);
		result.put("ciudad_codigo", ciudadCodigo);
		result.put("direccion", direccion);

		return result;
	}

	private static String getTerceroIdentificacion(Node node, XPath xpath) throws XPathExpressionException {
		String result;

		XPathExpression expr = xpath.compile(PATH_TERCERO_IDENTIFICACION);
		result = (String) expr.evaluate(node, XPathConstants.STRING);

		return result;
	}

	private static String getTerceroNombre(Node node, XPath xpath) throws XPathExpressionException {
		String result;

		XPathExpression expr = xpath.compile(PATH_TERCERO_NOMBRE);
		result = (String) expr.evaluate(node, XPathConstants.STRING);

		return result;
	}

	private static String getCiudadCodigo(Node node, XPath xpath) throws XPathExpressionException {
		String result;

		XPathExpression expr = xpath.compile(PATH_CIUDAD);
		result = (String) expr.evaluate(node, XPathConstants.STRING);

		return result;
	}

	private static String getDireccion(Node node, XPath xpath) throws XPathExpressionException {
		String result;

		XPathExpression expr = xpath.compile(PATH_DIRECCION);
		result = (String) expr.evaluate(node, XPathConstants.STRING);

		return result;
	}

	private static List<Map<String, String>> getLineas(Document document, XPath xpath) throws XPathExpressionException {
		List<Map<String, String>> result = new ArrayList<>();
		XPathExpression expr = xpath.compile(PATH_LINEAS);
		NodeList nodes = (NodeList) expr.evaluate(document, XPathConstants.NODESET);

		for (int i = 0; i < nodes.getLength(); i++) {
			val n = nodes.item(i);
			result.add(getLinea(n, xpath));
		}

		return result;
	}

	private static Map<String, String> getLinea(Node n, XPath xpath) throws XPathExpressionException {
		Map<String, String> result = new HashMap<>();

		String sku = getSkuCodigo(n, xpath);
		String nombre = getSkuNombre(n, xpath);
		String cantidad = getCantidad(n, xpath);

		result.put("sku_codigo", sku);
		result.put("sku_nombre", nombre);
		result.put("cantidad", cantidad);

		return result;
	}

	private static String getSkuCodigo(Node node, XPath xpath) throws XPathExpressionException {
		String result;

		XPathExpression expr = xpath.compile(PATH_LINEA_SKU_CODIGO);
		result = (String) expr.evaluate(node, XPathConstants.STRING);

		return result;
	}

	private static String getSkuNombre(Node node, XPath xpath) throws XPathExpressionException {
		String result;

		XPathExpression expr = xpath.compile(PATH_LINEA_SKU_NOMBRE);
		result = (String) expr.evaluate(node, XPathConstants.STRING);

		return result;
	}

	private static String getCantidad(Node node, XPath xpath) throws XPathExpressionException {
		String result;

		XPathExpression expr = xpath.compile(PATH_LINEA_CANTIDAD);
		result = (String) expr.evaluate(node, XPathConstants.STRING);

		return result;
	}
}