package com.tacticlogistics.integrador.etl.clientes.tactic.tms.rutas.moviles;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.tacticlogistics.ClienteCodigoType;
import com.tacticlogistics.integrador.etl.clientes.tactic.tms.rutas.moviles.model.MovilRutaControl;
import com.tacticlogistics.integrador.etl.clientes.tactic.tms.rutas.moviles.model.MovilRutaControlRepository;
import com.tacticlogistics.integrador.etl.handlers.ArchivoHandler;
import com.tacticlogistics.integrador.etl.handlers.decorators.CamposSplitterDecorator;
import com.tacticlogistics.integrador.etl.handlers.decorators.CheckArchivoVacioDecorator;
import com.tacticlogistics.integrador.etl.handlers.decorators.CheckNumeroDeColumnasDecorator;
import com.tacticlogistics.integrador.etl.handlers.decorators.CheckRegistrosDuplicadosDecorator;
import com.tacticlogistics.integrador.etl.handlers.decorators.CheckRestriccionesDeCamposDecorator;
import com.tacticlogistics.integrador.etl.handlers.decorators.Decorator;
import com.tacticlogistics.integrador.etl.handlers.decorators.IncluirEncabezadoDecorator;
import com.tacticlogistics.integrador.etl.handlers.decorators.LineasSplitterDecorator;
import com.tacticlogistics.integrador.etl.handlers.decorators.MayusculasDecorator;
import com.tacticlogistics.integrador.etl.handlers.readers.ExcelWorkSheetReaderGamma;
import com.tacticlogistics.integrador.etl.handlers.readers.Reader;

@Component
public class MovilesRutaControlArchivoHandler extends ArchivoHandler<MovilRutaControl, Long> {
	private static final String CODIGO_TIPO_ARCHIVO = "RUTACONTROL_MOVILES";

	private static final String WORKSHEET_NAME = "0";

	@Autowired
	private ExcelWorkSheetReaderGamma reader;

	@Autowired
	private MovilRutaControlRepository repository;

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
		Path result = Paths.get(ClienteCodigoType.TACTIC.toString());
		return result;
	}

	@Override
	protected Path getSubDirectorioRelativo() {
		Path result = Paths.get("TMS\\RUTAS\\RUTACONTROL\\MOVILES");
		return result;
	}

	@Override
	protected Pattern getFileNamePattern() {
		return PATTERN_XLS;
	}

	@Override
	protected Decorator<MovilRutaControl> getTransformador() {
		// @formatter:off
		return new MapEntidadMovilRutaControlDecorator(
				new CheckRegistrosDuplicadosDecorator<MovilRutaControl>(
					new CheckRestriccionesDeCamposDecorator<MovilRutaControl>(
						new FiltrarMovilRutaControlDecorator(
							new CamposSplitterDecorator<MovilRutaControl>(
								new CheckNumeroDeColumnasDecorator<MovilRutaControl>(
									new CheckArchivoVacioDecorator<MovilRutaControl>(
										new LineasSplitterDecorator<MovilRutaControl>(
											new IncluirEncabezadoDecorator<MovilRutaControl>(
												new MayusculasDecorator<MovilRutaControl>())))))))));
		// @formatter:on
	}

	@Override
	protected JpaRepository<MovilRutaControl, Long> getRepository() {
		return repository;
	}
}