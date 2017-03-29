package com.tacticlogistics.integrador.etl.clientes.gws.salidas;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.tacticlogistics.ClienteCodigoType;
import com.tacticlogistics.integrador.etl.clientes.heinz.salidas.model.SalidaCadena;
import com.tacticlogistics.integrador.etl.clientes.heinz.salidas.model.SalidaCadenaRepository;
import com.tacticlogistics.integrador.etl.handlers.ArchivoHandler;
import com.tacticlogistics.integrador.etl.handlers.decorators.CamposSplitterDecorator;
import com.tacticlogistics.integrador.etl.handlers.decorators.CheckArchivoVacioDecorator;
import com.tacticlogistics.integrador.etl.handlers.decorators.CheckNumeroDeColumnasDecorator;
import com.tacticlogistics.integrador.etl.handlers.decorators.CheckRegistrosDuplicadosDecorator;
import com.tacticlogistics.integrador.etl.handlers.decorators.CheckRestriccionesDeCamposDecorator;
import com.tacticlogistics.integrador.etl.handlers.decorators.Decorator;
import com.tacticlogistics.integrador.etl.handlers.decorators.LineasSplitterDecorator;
import com.tacticlogistics.integrador.etl.handlers.decorators.MayusculasDecorator;
import com.tacticlogistics.integrador.etl.handlers.readers.CharsetDetectorFileReaderBeta;
import com.tacticlogistics.integrador.etl.handlers.readers.ExcelWorkSheetReaderDelta;
import com.tacticlogistics.integrador.etl.handlers.readers.ExcelWorkSheetReaderGamma;
import com.tacticlogistics.integrador.etl.handlers.readers.Reader;

@Component
public class GwsSalidasArchivoHandler extends ArchivoHandler<SalidaCadena,Long> {
	private static final String CODIGO_TIPO_ARCHIVO = "GWS_SALIDAS";

	@Autowired
	private CharsetDetectorFileReaderBeta reader;

	@Autowired
	private SalidaCadenaRepository repository;

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
		Path result = Paths.get(ClienteCodigoType.HEINZ.toString());
		return result;
	}

	@Override
	protected Path getSubDirectorioRelativo() {
		Path result = Paths.get(".");
		return result;
	}

	@Override
	protected Pattern getFileNamePattern() {
		return PATTERN_XLS;
	}
	
	@Override
	protected Decorator<SalidaCadena> getTransformador() {
		// @formatter:off
		return new MapEntidadSalidaDecorator(
				new CheckRegistrosDuplicadosDecorator<SalidaCadena>(
					new CheckRestriccionesDeCamposDecorator<SalidaCadena>(
						new CamposSplitterDecorator<SalidaCadena>(
							new CheckNumeroDeColumnasDecorator<SalidaCadena>(
								new CheckArchivoVacioDecorator<SalidaCadena>(
									new LineasSplitterDecorator<SalidaCadena>(
										new MayusculasDecorator<SalidaCadena>(
		))))))));
		// @formatter:on
	}

	@Override
	protected JpaRepository<SalidaCadena, Long> getRepository() {
		return repository;
	}
}
