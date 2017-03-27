package com.tacticlogistics.integrador.etl.handlers.tactic.tms.cumplidos.digitales;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.tacticlogistics.ClienteCodigoType;
import com.tacticlogistics.integrador.etl.decorators.CamposSplitterDecorator;
import com.tacticlogistics.integrador.etl.decorators.CheckArchivoVacioDecorator;
import com.tacticlogistics.integrador.etl.decorators.CheckNumeroDeColumnasDecorator;
import com.tacticlogistics.integrador.etl.decorators.CheckRegistrosDuplicadosDecorator;
import com.tacticlogistics.integrador.etl.decorators.CheckRestriccionesDeCamposDecorator;
import com.tacticlogistics.integrador.etl.decorators.Decorator;
import com.tacticlogistics.integrador.etl.decorators.IncluirEncabezadoDecorator;
import com.tacticlogistics.integrador.etl.decorators.LineasSplitterDecorator;
import com.tacticlogistics.integrador.etl.decorators.MayusculasDecorator;
import com.tacticlogistics.integrador.etl.decorators.NormalizarSeparadoresDeRegistroDecorator;
import com.tacticlogistics.integrador.etl.handlers.ArchivoHandler;
import com.tacticlogistics.integrador.etl.handlers.tactic.tms.cumplidos.digitales.model.CumplidoDigital;
import com.tacticlogistics.integrador.etl.handlers.tactic.tms.cumplidos.digitales.model.CumplidoDigitalRepository;
import com.tacticlogistics.integrador.etl.readers.CharsetDetectorFileReaderBeta;
import com.tacticlogistics.integrador.etl.readers.Reader;

@Component
public class CumplidosDigitalesArchivoHandler extends ArchivoHandler<CumplidoDigital, Long> {
	private static final String CODIGO_TIPO_ARCHIVO = "CUMPLIDOS_DIGITALES";

	@Autowired
	private CharsetDetectorFileReaderBeta reader;

	@Autowired
	private CumplidoDigitalRepository repository;

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
		Path result = Paths.get("TMS\\CUMPLIDOS\\DIGITALES");
		return result;
	}

	@Override
	protected Pattern getFileNamePattern() {
		return PATTERN_TXT;
	}

	@Override
	protected Decorator<CumplidoDigital> getTransformador() {
		// @formatter:off
		return new MapEntidadCumplidoDigitalDecorator(
				new CheckRegistrosDuplicadosDecorator<CumplidoDigital>(
					new CheckRestriccionesDeCamposDecorator<CumplidoDigital>(
						new ExtraerCamposCumplidoDigitalDecorator(
							new FiltrarCumplidoDigitalDecorator(
								new CamposSplitterDecorator<CumplidoDigital>(
									new CheckNumeroDeColumnasDecorator<CumplidoDigital>(
										new CheckArchivoVacioDecorator<CumplidoDigital>(
											new LineasSplitterDecorator<CumplidoDigital>(
												new IncluirEncabezadoDecorator<CumplidoDigital>(
													new NormalizarSeparadoresDeRegistroDecorator<CumplidoDigital>(
														new MayusculasDecorator<CumplidoDigital>())))))))))));
		// @formatter:on
	}

	@Override
	protected JpaRepository<CumplidoDigital, Long> getRepository() {
		return repository;
	}
}