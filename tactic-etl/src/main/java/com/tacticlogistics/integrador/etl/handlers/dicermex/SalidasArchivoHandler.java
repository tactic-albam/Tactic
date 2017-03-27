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
import com.tacticlogistics.integrador.etl.handlers.dicermex.model.Salida;
import com.tacticlogistics.integrador.etl.handlers.dicermex.model.SalidaRepository;
import com.tacticlogistics.integrador.etl.readers.CharsetDetectorFileReaderBeta;
import com.tacticlogistics.integrador.etl.readers.Reader;

@Component
public class SalidasArchivoHandler extends ArchivoHandler<Salida,Long> {
	private static final Pattern PATTERN = Pattern.compile("(?i:(.*)ESBOrdenesDeDespacho(Parcial)?_(\\d+)\\.txt)");
	
	private static final String CODIGO_TIPO_ARCHIVO = "DICERMEX_SALIDAS";

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
	protected Decorator<Salida> getTransformador() {
		// @formatter:off
		return new MapEntidadSalidaDecorator(
				new CheckRegistrosDuplicadosDecorator<Salida>(
					new CheckRestriccionesDeCamposDecorator<Salida>(
						new ExtraerCamposSalidaDecorator(
							new CamposSplitterDecorator<Salida>(
								new CheckNumeroDeColumnasDecorator<Salida>(
									new CheckArchivoVacioDecorator<Salida>(
										new LineasSplitterDecorator<Salida>(
											new IncluirEncabezadoDecorator<Salida>(
												new NormalizarSeparadoresDeRegistroDecorator<Salida>(
													new MayusculasDecorator<Salida>(
		)))))))))));
		// @formatter:on
	}

	@Override
	protected JpaRepository<Salida, Long> getRepository() {
		return repository;
	}
}
