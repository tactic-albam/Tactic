package com.tacticlogistics.integrador.files.clientes.tactic.oms.salidas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import com.tacticlogistics.ClienteCodigoType;
import com.tacticlogistics.integrador.files.handlers.ArchivoExcelHandler;
import com.tacticlogistics.integrador.files.handlers.decorators.CamposSplitterDecorator;
import com.tacticlogistics.integrador.files.handlers.decorators.CheckArchivoVacioDecorator;
import com.tacticlogistics.integrador.files.handlers.decorators.CheckNumeroDeColumnasDecorator;
import com.tacticlogistics.integrador.files.handlers.decorators.CheckRegistrosDuplicadosDecorator;
import com.tacticlogistics.integrador.files.handlers.decorators.CheckRestriccionesDeCamposDecorator;
import com.tacticlogistics.integrador.files.handlers.decorators.Decorator;
import com.tacticlogistics.integrador.files.handlers.decorators.IncluirCamposDecorator;
import com.tacticlogistics.integrador.files.handlers.decorators.LineasSplitterDecorator;
import com.tacticlogistics.integrador.files.handlers.decorators.MayusculasDecorator;
import com.tacticlogistics.integrador.model.oms.Salida;
import com.tacticlogistics.integrador.model.oms.SalidaRepository;

public abstract class AbstractPlantillaSalidasHandler extends ArchivoExcelHandler<Salida, Long> {

	static final String WORKSHEET_NAME = "ORDENES";

	@Autowired
	private SalidaRepository repository;

	public AbstractPlantillaSalidasHandler() {
		super();
	}

	@Override
	protected String getClienteCodigo() {
		return ClienteCodigoType.TACTIC.toString();
	}

	@Override
	protected String getWorkSheetName() {
		return WORKSHEET_NAME;
	}

	@Override
	protected JpaRepository<Salida, Long> getRepository() {
		return repository;
	}
	
	// ----------------------------------------------------------------------------------------------------------------
	//
	// ----------------------------------------------------------------------------------------------------------------
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
													new MayusculasDecorator<Salida>(
			))))))))));
		// @formatter:on
	}
}