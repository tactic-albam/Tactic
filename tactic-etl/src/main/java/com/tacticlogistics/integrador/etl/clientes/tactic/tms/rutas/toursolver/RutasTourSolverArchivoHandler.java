package com.tacticlogistics.integrador.etl.clientes.tactic.tms.rutas.toursolver;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.tacticlogistics.ClienteCodigoType;
import com.tacticlogistics.integrador.etl.clientes.tactic.tms.rutas.toursolver.model.RutaTourSolver;
import com.tacticlogistics.integrador.etl.clientes.tactic.tms.rutas.toursolver.model.RutaTourSolverRepository;
import com.tacticlogistics.integrador.etl.handlers.ArchivoHandler;
import com.tacticlogistics.integrador.etl.handlers.decorators.CamposSplitterDecorator;
import com.tacticlogistics.integrador.etl.handlers.decorators.CheckArchivoVacioDecorator;
import com.tacticlogistics.integrador.etl.handlers.decorators.CheckNumeroDeColumnasDecorator;
import com.tacticlogistics.integrador.etl.handlers.decorators.CheckRegistrosDuplicadosDecorator;
import com.tacticlogistics.integrador.etl.handlers.decorators.CheckRestriccionesDeCamposDecorator;
import com.tacticlogistics.integrador.etl.handlers.decorators.Decorator;
import com.tacticlogistics.integrador.etl.handlers.decorators.LineasSplitterDecorator;
import com.tacticlogistics.integrador.etl.handlers.decorators.MayusculasDecorator;
import com.tacticlogistics.integrador.etl.handlers.readers.ExcelWorkSheetReaderBeta;
import com.tacticlogistics.integrador.etl.handlers.readers.Reader;

@Component
public class RutasTourSolverArchivoHandler extends ArchivoHandler<RutaTourSolver,Long> {
	private static final String CODIGO_TIPO_ARCHIVO = "RUTAS_TOURSOLVER";

	private static final String WORKSHEET_NAME = "Informe";

	@Autowired
	private ExcelWorkSheetReaderBeta reader;

	@Autowired
	private RutaTourSolverRepository repository;

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
		Path result = Paths.get("TMS\\RUTAS\\TOURSOLVER");
		return result;
	}

	@Override
	protected Pattern getFileNamePattern() {
		return PATTERN_XLS;
	}
	
	@Override
	protected Decorator<RutaTourSolver> getTransformador() {
		// @formatter:off
		return new MapEntidadRutaTourSolverDecorator(
				new CheckRegistrosDuplicadosDecorator<RutaTourSolver>(
					new CheckRestriccionesDeCamposDecorator<RutaTourSolver>(
						new FiltrarRutasTourSolverDecorator(
							new CamposSplitterDecorator<RutaTourSolver>(
								new CheckNumeroDeColumnasDecorator<RutaTourSolver>(
										new CheckArchivoVacioDecorator<RutaTourSolver>(
											new LineasSplitterDecorator<RutaTourSolver>(
													new MayusculasDecorator<RutaTourSolver>()))))))));
		// @formatter:on
	}

	@Override
	protected JpaRepository<RutaTourSolver, Long> getRepository() {
		return repository;
	}
}
