package com.tacticlogistics.integrador.db.clientes.tactic.wms.pf;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tacticlogistics.integrador.db.clientes.tactic.wms.pf.productosmedidas.jaxb.CTRLSEG;
import com.tacticlogistics.integrador.db.clientes.tactic.wms.pf.productosmedidas.jaxb.ObjectFactory;
import com.tacticlogistics.integrador.db.clientes.tactic.wms.pf.productosmedidas.jaxb.PARTFOOTDTLSEG;
import com.tacticlogistics.integrador.db.clientes.tactic.wms.pf.productosmedidas.jaxb.PARTFOOTINBIFD;
import com.tacticlogistics.integrador.db.clientes.tactic.wms.pf.productosmedidas.jaxb.PARTFOOTSEG;
import com.tacticlogistics.integrador.db.dto.DbRequestDTO;
import com.tacticlogistics.integrador.db.handlers.DbHandler;
import com.tacticlogistics.integrador.model.etl.archivo.ArchivoRepository;
import com.tacticlogistics.integrador.model.etl.registro.EstadoRegistroType;
import com.tacticlogistics.integrador.model.wms.pf.ProntoFormProductoMedida;
import com.tacticlogistics.integrador.model.wms.pf.ProntoFormProductoMedidaRepository;

import lombok.val;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DbProntoFormProductoMedidaHandler extends DbHandler {
	private static final String FORMATO_DECIMAL = "0.#";

	private static final char SEPARADOR_GRUPO = ',';

	private static final char SEPARADOR_DECIMAL = '.';

	private static final String EXTENSION_TRG = ".TRG";

	private static final String EXTENSION_XML = ".XML";

	private static final String SEARCH_CHARS = "<>:'\"/\\|?* ";

	private static final String REPLACE_CHARS = StringUtils.repeat("_", SEARCH_CHARS.length());

	private static final String XML_ENCODING = "ISO-8859-15";

	private static final String CODIGO_TIPO_ARCHIVO = "WMS_PF_PRODUCTOS_MEDIDAS";

	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HHmmss");

	@Value("${wms.directorios.entradas}")
	private String directorioWms;

	@Autowired
	private ArchivoRepository archivoRepository;

	@Autowired
	private ProntoFormProductoMedidaRepository repository;

	@Override
	protected boolean canHandleRequest(DbRequestDTO request) {
		if (CODIGO_TIPO_ARCHIVO.equals(request.getTipoArchivo().getCodigo())) {
			return true;
		}
		return false;
	}

	@Override
	@Transactional
	protected void handleRequest(DbRequestDTO request) {
		val archivo = archivoRepository.findOne(request.getArchivo().getId());
		val registros = repository.findByIdArchivo(request.getArchivo().getId());
		val directorio = Paths.get(directorioWms);
		String prefijoArchivo = getPrefijoArchivo(request);

		try {
			ObjectFactory factory = new ObjectFactory();
			JAXBContext jaxbContext = JAXBContext.newInstance(PARTFOOTINBIFD.class.getPackage().getName());

			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.setProperty(Marshaller.JAXB_ENCODING, XML_ENCODING);

			procesarRegistro(registros, factory, marshaller, directorio, prefijoArchivo);
			archivo.marcarProcesado(null);
		} catch (JAXBException e) {
			archivo.marcarNoValidoPorExcepcion(e);
		}

		archivoRepository.save(archivo);
	}

	private String getPrefijoArchivo(DbRequestDTO request) {
		String prefijoArchivo;
		prefijoArchivo = FilenameUtils.removeExtension(request.getArchivo().getNombre());
		prefijoArchivo = String.format("%1s-%2s", LocalDateTime.now().format(formatter), prefijoArchivo);
		return prefijoArchivo;
	}

	private boolean procesarRegistro(final List<ProntoFormProductoMedida> registros, final ObjectFactory factory,
			final Marshaller marshaller, final Path directorio, final String prefijoArchivo) {

		boolean result = true;

		for (val registro : registros) {
			String productoCodigo = getProductoCodigo(registro);
			String nombreArchivo = String.format("%1s-%2s", prefijoArchivo, productoCodigo);
			Path nombreArchivoXML = directorio.resolve(nombreArchivo + EXTENSION_XML);
			Path nombreArchivoTRG = directorio.resolve(nombreArchivo + EXTENSION_TRG);

			EstadoRegistroType estadoRegistro;
			try {
				PARTFOOTINBIFD partfootinbifd = buildXMLPartFoot(registro, factory);
				marshaller.marshal(partfootinbifd, nombreArchivoXML.toFile());
				FileUtils.writeStringToFile(nombreArchivoTRG.toFile(), "", Charset.defaultCharset());

				estadoRegistro = EstadoRegistroType.PROCESADO;
			} catch (JAXBException | IOException e) {
				estadoRegistro = EstadoRegistroType.ERROR_FATAL;
				result = false;
				log.error("", e);
			}

			registro.setEstadoRegistro(estadoRegistro);
			registro.setFechaActualizacion(LocalDateTime.now());
			registro.setUsuarioActualizacion(SystemUtils.USER_NAME);
			repository.save(registro);
		}
		return result;
	}

	private String getProductoCodigo(final ProntoFormProductoMedida registro) {
		String productoCodigo;
		productoCodigo = registro.getProductoCodigo();
		if (StringUtils.containsAny(productoCodigo, SEARCH_CHARS)) {
			productoCodigo = StringUtils.replaceChars(productoCodigo, SEARCH_CHARS, REPLACE_CHARS);
		}
		return productoCodigo;
	}

	private PARTFOOTINBIFD buildXMLPartFoot(final ProntoFormProductoMedida registro, ObjectFactory factory) {
		PARTFOOTINBIFD partfootinbifd = factory.createPARTFOOTINBIFD();
		CTRLSEG ctrlseg;
		;
		PARTFOOTSEG partfootseg;
		PARTFOOTDTLSEG partfootdtlseg0;
		PARTFOOTDTLSEG partfootdtlseg1;
		PARTFOOTDTLSEG partfootdtlseg2;

		ctrlseg = buildXMLCtrlSeg(registro, factory);
		partfootseg = buildXMLPartFootSeg(registro, factory);

		partfootdtlseg0 = buildXMLPartFootDtlNivel0(registro, factory);
		partfootdtlseg1 = buildXMLPartFootDtlNivel1(registro, factory);
		partfootdtlseg2 = buildXMLPartFootDtlNivel2(registro, factory);

		if (partfootdtlseg0 != null && partfootdtlseg1 == null) {
			partfootdtlseg0.setCASFLG("1");
			if (partfootdtlseg2 != null) {
				partfootdtlseg2.setUOMLVL("1");
			}
		}

		if (partfootdtlseg0 != null) {
			partfootseg.getPARTFOOTDTLSEG().add(partfootdtlseg0);
		}

		if (partfootdtlseg1 != null) {
			partfootseg.getPARTFOOTDTLSEG().add(partfootdtlseg1);
		}

		if (partfootdtlseg2 != null) {
			partfootseg.getPARTFOOTDTLSEG().add(partfootdtlseg2);
		}

		ctrlseg.setPARTFOOTSEG(partfootseg);
		partfootinbifd.setCTRLSEG(ctrlseg);

		return partfootinbifd;
	}

	private CTRLSEG buildXMLCtrlSeg(final ProntoFormProductoMedida registro, ObjectFactory factory) {
		CTRLSEG ctrlseg;

		ctrlseg = factory.createCTRLSEG();
		ctrlseg.setTRNNAM("PARTFOOT_TRAN");
		ctrlseg.setTRNVER("2015.1");
		ctrlseg.setWHSEID(registro.getBodegaCodigo());

		return ctrlseg;
	}

	private PARTFOOTSEG buildXMLPartFootSeg(final ProntoFormProductoMedida registro, ObjectFactory factory) {
		PARTFOOTSEG partfootseg;

		partfootseg = factory.createPARTFOOTSEG();
		partfootseg.setSEGNAM("PARTFOOT");
		partfootseg.setTRNTYP("A");
		partfootseg.setPRTNUM(registro.getProductoCodigo());
		partfootseg.setFTPCOD(registro.getHuellaCodigo());
		partfootseg.setPRTCLIENTID(registro.getClienteCodigo());
		partfootseg.setLNGDSC(registro.getProductoNombre());
		partfootseg.setLOCALEID("ES-ES");
		partfootseg.setCASLVL(String.valueOf(registro.getCaseLevel()));
		partfootseg.setPALSTCKHGT("0");
		partfootseg.setDEFFTPFLG("1");

		return partfootseg;
	}

	private PARTFOOTDTLSEG buildXMLPartFootDtlNivel0(final ProntoFormProductoMedida registro, ObjectFactory factory) {
		PARTFOOTDTLSEG partfootdtlseg;
		// @formatter:off
		partfootdtlseg = buildXMLPartFootDtl(
				registro.getUnidadCodigo1(), 
				registro.getFactorConversion1(),
				registro.getLargo1(),
				registro.getAncho1(),
				registro.getAlto1(),
				registro.getPeso1(),
				factory);
		// @formatter:on

		fillValoresNivel0(partfootdtlseg);

		return partfootdtlseg;
	}

	private PARTFOOTDTLSEG buildXMLPartFootDtlNivel1(final ProntoFormProductoMedida registro, ObjectFactory factory) {
		PARTFOOTDTLSEG partfootdtlseg;
		// @formatter:off
		partfootdtlseg = buildXMLPartFootDtl(
				registro.getUnidadCodigo2(), 
				registro.getFactorConversion2(),
				registro.getLargo2(),
				registro.getAncho2(),
				registro.getAlto2(),
				registro.getPeso2(),
				factory);
		// @formatter:on

		fillValoresNivel1(partfootdtlseg);

		return partfootdtlseg;
	}

	private PARTFOOTDTLSEG buildXMLPartFootDtlNivel2(final ProntoFormProductoMedida registro, ObjectFactory factory) {
		PARTFOOTDTLSEG partfootdtlseg;
		// @formatter:off
		partfootdtlseg = buildXMLPartFootDtl(
				registro.getUnidadCodigo3(), 
				registro.getFactorConversion3(),
				registro.getLargo3(),
				registro.getAncho3(),
				registro.getAlto3(),
				registro.getPeso3(),
				factory);
		// @formatter:on

		fillValoresNivel2(partfootdtlseg);

		return partfootdtlseg;
	}

	private PARTFOOTDTLSEG buildXMLPartFootDtl(String unidadCodigo, Integer factorConversion, BigDecimal largo,
			BigDecimal ancho, BigDecimal alto, BigDecimal peso, ObjectFactory factory) {
		PARTFOOTDTLSEG partfootdtlseg = null;
		if (StringUtils.isNotEmpty(unidadCodigo)) {
			partfootdtlseg = factory.createPARTFOOTDTLSEG();

			fillValoresRegistro(partfootdtlseg, unidadCodigo, factorConversion, largo, ancho, alto, peso);
			fillValoresConstantes(partfootdtlseg);
		}
		return partfootdtlseg;
	}

	private void fillValoresRegistro(PARTFOOTDTLSEG partfootdtlseg, String unidadCodigo, Integer factorConversion,
			BigDecimal largo, BigDecimal ancho, BigDecimal alto, BigDecimal peso) {

		if (partfootdtlseg != null) {
			String unt = String.valueOf(factorConversion);
			String len = getDecimalFormat().format(largo);
			String wid = getDecimalFormat().format(ancho);
			String hgt = getDecimalFormat().format(alto);
			String wgt = getDecimalFormat().format(peso);

			partfootdtlseg.setUOMCOD(unidadCodigo);
			partfootdtlseg.setUNTQTY(unt);
			partfootdtlseg.setLEN(len);
			partfootdtlseg.setWID(wid);
			partfootdtlseg.setHGT(hgt);
			partfootdtlseg.setGRSWGT(wgt);
			partfootdtlseg.setNETWGT(wgt);
		}

	}

	private void fillValoresConstantes(PARTFOOTDTLSEG partfootdtlseg) {
		if (partfootdtlseg != null) {
			partfootdtlseg.setSEGNAM("PARTFOOT_DTL");
			partfootdtlseg.setTHRESHPCT("100");
			partfootdtlseg.setBULKPCKFLG("0");
		}
	}

	private void fillValoresNivel0(PARTFOOTDTLSEG partfootdtlseg) {
		if (partfootdtlseg != null) {
			partfootdtlseg.setUOMLVL("0");
			partfootdtlseg.setPALFLG("0");
			partfootdtlseg.setCASFLG("0");
			partfootdtlseg.setPAKFLG("0");
			partfootdtlseg.setSTKFLG("1");
			partfootdtlseg.setRCVFLG("1");
		}
	}

	private void fillValoresNivel1(PARTFOOTDTLSEG partfootdtlseg) {
		if (partfootdtlseg != null) {
			partfootdtlseg.setUOMLVL("1");
			partfootdtlseg.setPALFLG("0");
			partfootdtlseg.setCASFLG("1");
			partfootdtlseg.setPAKFLG("0");
			partfootdtlseg.setSTKFLG("0");
			partfootdtlseg.setRCVFLG("0");
		}
	}

	private void fillValoresNivel2(PARTFOOTDTLSEG partfootdtlseg) {
		if (partfootdtlseg != null) {
			partfootdtlseg.setUOMLVL("2");
			partfootdtlseg.setPALFLG("1");
			partfootdtlseg.setCASFLG("0");
			partfootdtlseg.setPAKFLG("0");
			partfootdtlseg.setSTKFLG("0");
			partfootdtlseg.setRCVFLG("0");
		}
	}

	private static DecimalFormat decimalFormat = null;

	public DecimalFormat getDecimalFormat() {
		if (decimalFormat == null) {
			DecimalFormatSymbols symbols = new DecimalFormatSymbols();
			symbols.setDecimalSeparator(SEPARADOR_DECIMAL);
			symbols.setGroupingSeparator(SEPARADOR_GRUPO);

			decimalFormat = new DecimalFormat(FORMATO_DECIMAL, symbols);
			decimalFormat.setParseBigDecimal(true);
		}
		return decimalFormat;
	}
}
