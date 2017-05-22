package com.tacticlogistics.integrador.files.clientes.tactic.tms.rutas.manuales;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.tacticlogistics.ClienteCodigoType;
import com.tacticlogistics.integrador.files.handlers.ArchivoHandler;
import com.tacticlogistics.integrador.files.handlers.decorators.CamposSplitterDecorator;
import com.tacticlogistics.integrador.files.handlers.decorators.CheckArchivoVacioDecorator;
import com.tacticlogistics.integrador.files.handlers.decorators.CheckNumeroDeColumnasDecorator;
import com.tacticlogistics.integrador.files.handlers.decorators.CheckRegistrosDuplicadosDecorator;
import com.tacticlogistics.integrador.files.handlers.decorators.CheckRestriccionesDeCamposDecorator;
import com.tacticlogistics.integrador.files.handlers.decorators.Decorator;
import com.tacticlogistics.integrador.files.handlers.decorators.IncluirEncabezadoDecorator;
import com.tacticlogistics.integrador.files.handlers.decorators.LineasSplitterDecorator;
import com.tacticlogistics.integrador.files.handlers.decorators.MayusculasDecorator;
import com.tacticlogistics.integrador.files.handlers.decorators.NormalizarSeparadoresDeRegistroDecorator;
import com.tacticlogistics.integrador.files.handlers.readers.CharsetDetectorFileReaderBeta;
import com.tacticlogistics.integrador.files.handlers.readers.Reader;
import com.tacticlogistics.integrador.model.tms.rutas.manuales.RutaManual;
import com.tacticlogistics.integrador.model.tms.rutas.manuales.RutaManualRepository;

@Component
public class RutasManualesArchivoHandler extends ArchivoHandler<RutaManual, Long> {
	private static final String CODIGO_TIPO_ARCHIVO = "RUTAS_MANUALES";

	@Autowired
	private CharsetDetectorFileReaderBeta reader;

	@Autowired
	private RutaManualRepository repository;

	// ----------------------------------------------------------------------------------------------------------------
	//
	// ----------------------------------------------------------------------------------------------------------------
	@Override
	protected Reader getReader() {
		return reader;
	}

	@Override
	protected String getCodigoTipoArchivo() {
		return CODIGO_TIPO_ARCHIVO;
	}

	@Override
	protected Path getCliente() {
		Path result = Paths.get(ClienteCodigoType.TACTIC.toString());
		return result;
	}

	@Override
	protected Path getSubDirectorioRelativo() {
		Path result = Paths.get("TMS\\RUTAS\\MANUALES");
		return result;
	}

	@Override
	protected Pattern getFileNamePattern() {
		return PATTERN_TXT;
	}

	@Override
	protected Decorator<RutaManual> getTransformador() {
		// @formatter:off
		return new MapEntidadRutaManualDecorator(
				new CheckRegistrosDuplicadosDecorator<RutaManual>(
					new CheckRestriccionesDeCamposDecorator<RutaManual>(
							new CamposSplitterDecorator<RutaManual>(
								new CheckNumeroDeColumnasDecorator<RutaManual>(
									new CheckArchivoVacioDecorator<RutaManual>(
										new LineasSplitterDecorator<RutaManual>(
											new IncluirEncabezadoDecorator<RutaManual>(
												new NormalizarSeparadoresDeRegistroDecorator<RutaManual>(
													new MayusculasDecorator<RutaManual>(
				))))))))));
		// @formatter:on
	}

	@Override
	protected JpaRepository<RutaManual, Long> getRepository() {
		return repository;
	}
}
