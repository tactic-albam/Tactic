package com.tacticlogistics.integrador.etl.clientes.paneco.salidas;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.tacticlogistics.ClienteCodigoType;
import com.tacticlogistics.integrador.etl.clientes.tactic.oms.MapEntidadSalidaDecorator;
import com.tacticlogistics.integrador.etl.handlers.ArchivoHandler;
import com.tacticlogistics.integrador.etl.handlers.decorators.CamposSplitterDecorator;
import com.tacticlogistics.integrador.etl.handlers.decorators.CheckArchivoVacioDecorator;
import com.tacticlogistics.integrador.etl.handlers.decorators.CheckNumeroDeColumnasDecorator;
import com.tacticlogistics.integrador.etl.handlers.decorators.CheckRegistrosDuplicadosDecorator;
import com.tacticlogistics.integrador.etl.handlers.decorators.CheckRestriccionesDeCamposDecorator;
import com.tacticlogistics.integrador.etl.handlers.decorators.Decorator;
import com.tacticlogistics.integrador.etl.handlers.decorators.IncluirCamposDecorator;
import com.tacticlogistics.integrador.etl.handlers.decorators.IncluirEncabezadoDecorator;
import com.tacticlogistics.integrador.etl.handlers.decorators.LineasSplitterDecorator;
import com.tacticlogistics.integrador.etl.handlers.decorators.MayusculasDecorator;
import com.tacticlogistics.integrador.etl.handlers.decorators.NormalizarSeparadoresDeRegistroDecorator;
import com.tacticlogistics.integrador.etl.handlers.readers.CharsetDetectorFileReaderBeta;
import com.tacticlogistics.integrador.etl.handlers.readers.Reader;
import com.tacticlogistics.integrador.etl.model.oms.Salida;
import com.tacticlogistics.integrador.etl.model.oms.SalidaRepository;

@Component
public class PanecoSalidasArchivoHandler extends ArchivoHandler<Salida,Long> {
	private static final String CODIGO_TIPO_ARCHIVO = "PANECO_SALIDAS";

	@Autowired
	private CharsetDetectorFileReaderBeta reader;

	@Autowired
	private SalidaRepository repository;

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
		Path result = Paths.get(ClienteCodigoType.PANECO.toString());
		return result;
	}

	@Override
	protected Path getSubDirectorioRelativo() {
		Path result = Paths.get("ORDENES\\VENTAS");
		return result;
	}

	@Override
	protected Pattern getFileNamePattern() {
		return PATTERN_TXT;
	}
	
	@Override
	protected Decorator<Salida> getTransformador() {
		// @formatter:off
		return new MapEntidadSalidaDecorator(
				new CheckRegistrosDuplicadosDecorator<Salida>(
					new CheckRestriccionesDeCamposDecorator<Salida>(
						new EnriquecerCamposDecorator(
							new IncluirCamposDecorator<Salida>(
								new CamposSplitterDecorator<Salida>(
									new CheckNumeroDeColumnasDecorator<Salida>(
										new CheckArchivoVacioDecorator<Salida>(
											new LineasSplitterDecorator<Salida>(
												new IncluirEncabezadoDecorator<Salida>(
													new NormalizarSeparadoresDeRegistroDecorator<Salida>(
														new MayusculasDecorator<Salida>(
				))))))))))));
		// @formatter:on
	}

	@Override
	protected JpaRepository<Salida, Long> getRepository() {
		return repository;
	}
}
