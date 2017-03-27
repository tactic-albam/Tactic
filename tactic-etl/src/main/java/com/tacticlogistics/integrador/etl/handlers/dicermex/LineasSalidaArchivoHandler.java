package com.tacticlogistics.integrador.etl.handlers.dicermex;

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
import com.tacticlogistics.integrador.etl.handlers.dicermex.model.LineaSalida;
import com.tacticlogistics.integrador.etl.handlers.dicermex.model.LineaSalidaRepository;
import com.tacticlogistics.integrador.etl.readers.CharsetDetectorFileReaderBeta;
import com.tacticlogistics.integrador.etl.readers.Reader;

@Component
public class LineasSalidaArchivoHandler extends ArchivoHandler<LineaSalida,Long> {
	private static final Pattern PATTERN = Pattern.compile("(?i:(.*)ESBOrdenesDeDespacho(Parcial)?_(\\d+)_DETALLE\\.txt)");
	
	private static final String CODIGO_TIPO_ARCHIVO = "DICERMEX_LINEAS_SALIDA";

	@Autowired
	private CharsetDetectorFileReaderBeta reader;

	@Autowired
	private LineaSalidaRepository repository;

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
		Path result = Paths.get(ClienteCodigoType.DICERMEX.toString());
		return result;
	}

	@Override
	protected Path getSubDirectorioRelativo() {
		Path result = Paths.get("SALIDAS");
		return result;
	}

	@Override
	protected Pattern getFileNamePattern() {
		return PATTERN;
	}
	
	@Override
	protected Decorator<LineaSalida> getTransformador() {
		// @formatter:off
		return new MapEntidadLineaSalidaDecorator(
				new CheckRegistrosDuplicadosDecorator<LineaSalida>(
					new CheckRestriccionesDeCamposDecorator<LineaSalida>(
						new CamposSplitterDecorator<LineaSalida>(
							new CheckNumeroDeColumnasDecorator<LineaSalida>(
								new CheckArchivoVacioDecorator<LineaSalida>(
									new LineasSplitterDecorator<LineaSalida>(
										new IncluirEncabezadoDecorator<LineaSalida>(
											new NormalizarSeparadoresDeRegistroDecorator<LineaSalida>(
												new MayusculasDecorator<LineaSalida>(
		))))))))));
		// @formatter:on
	}

	@Override
	protected JpaRepository<LineaSalida, Long> getRepository() {
		return repository;
	}
}
