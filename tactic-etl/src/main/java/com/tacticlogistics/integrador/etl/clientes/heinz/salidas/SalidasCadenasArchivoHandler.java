package com.tacticlogistics.integrador.etl.clientes.heinz.salidas;

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
import com.tacticlogistics.integrador.etl.handlers.decorators.LineasSplitterDecorator;
import com.tacticlogistics.integrador.etl.handlers.decorators.MayusculasDecorator;
import com.tacticlogistics.integrador.etl.handlers.decorators.NormalizarSeparadoresDeRegistroDecorator;
import com.tacticlogistics.integrador.etl.handlers.readers.ExcelWorkSheetReaderDelta;
import com.tacticlogistics.integrador.etl.handlers.readers.Reader;
import com.tacticlogistics.integrador.etl.model.oms.Salida;
import com.tacticlogistics.integrador.etl.model.oms.SalidaRepository;

@Component
public class SalidasCadenasArchivoHandler extends ArchivoHandler<Salida,Long> {
	private static final String CODIGO_TIPO_ARCHIVO = "HEINZ_SALIDAS_CADENAS";

	private static final String WORKSHEET_NAME = "CADENAS";

	@Autowired
	private ExcelWorkSheetReaderDelta reader;

	@Autowired
	private SalidaRepository repository;

	// ----------------------------------------------------------------------------------------------------------------
	//
	// ----------------------------------------------------------------------------------------------------------------
	@Override
	protected Reader getReader() {
		if (this.reader.getWorkSheetName() == null) {
			this.reader.setWorkSheetName(WORKSHEET_NAME);
		}
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
		Path result = Paths.get("SALIDAS\\CADENAS");
		return result;
	}

	@Override
	protected Pattern getFileNamePattern() {
		return PATTERN_XLS;
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
