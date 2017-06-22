package com.tacticlogistics.integrador.files.clientes.tactic.tms.rutas.toursolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.tacticlogistics.ClienteCodigoType;
import com.tacticlogistics.integrador.files.handlers.ArchivoExcelHandler;
import com.tacticlogistics.integrador.files.handlers.decorators.CamposSplitterDecorator;
import com.tacticlogistics.integrador.files.handlers.decorators.CheckArchivoVacioDecorator;
import com.tacticlogistics.integrador.files.handlers.decorators.CheckNumeroDeColumnasDecorator;
import com.tacticlogistics.integrador.files.handlers.decorators.CheckRegistrosDuplicadosDecorator;
import com.tacticlogistics.integrador.files.handlers.decorators.CheckRestriccionesDeCamposDecorator;
import com.tacticlogistics.integrador.files.handlers.decorators.Decorator;
import com.tacticlogistics.integrador.files.handlers.decorators.LineasSplitterDecorator;
import com.tacticlogistics.integrador.files.handlers.decorators.MayusculasDecorator;
import com.tacticlogistics.integrador.model.tms.rutas.toursolver.RutaTourSolver;
import com.tacticlogistics.integrador.model.tms.rutas.toursolver.RutaTourSolverRepository;

@Component
public class RutasTourSolverArchivoHandler extends ArchivoExcelHandler<RutaTourSolver,Long> {
	private static final String WORKSHEET_NAME = "Informe";

	private static final String CODIGO_TIPO_ARCHIVO = "RUTAS_TOURSOLVER";

	private static final String SUBDIRECTORIO_RELATIVO = "TMS\\RUTAS\\TOURSOLVER";

	@Autowired
	private RutaTourSolverRepository repository;

	// ----------------------------------------------------------------------------------------------------------------
	//
	// ----------------------------------------------------------------------------------------------------------------
	@Override
	protected String getWorkSheetName() {
		return WORKSHEET_NAME;
	}

	@Override
	protected String getClienteCodigo() {
		return ClienteCodigoType.TACTIC.toString();
	}
	
	@Override
	protected String getCodigoTipoArchivo() {
		return CODIGO_TIPO_ARCHIVO;
	}

	@Override
	protected String getDirectorioRelativo() {
		return SUBDIRECTORIO_RELATIVO;
	}

	@Override
	protected JpaRepository<RutaTourSolver, Long> getRepository() {
		return repository;
	}

	// ----------------------------------------------------------------------------------------------------------------
	//
	// ----------------------------------------------------------------------------------------------------------------
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
}
