package com.tacticlogistics.integrador.files.clientes.tactic.oms.manufacturas;

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
import com.tacticlogistics.integrador.model.oms.Manufactura;
import com.tacticlogistics.integrador.model.oms.ManufacturaRepository;

public abstract class AbstractPlantillaManufacturaHandler extends ArchivoExcelHandler<Manufactura, Long> {

	static final String WORKSHEET_NAME = "ORDENES";
	
	@Autowired
	private ManufacturaRepository repository;

	public AbstractPlantillaManufacturaHandler() {
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
	protected JpaRepository<Manufactura, Long> getRepository() {
		return repository;
	}

	@Override
	protected Decorator<Manufactura> getTransformador() {
		// @formatter:off
		return new MapEntidadManufacturaDecorator(
				new CheckRegistrosDuplicadosDecorator<Manufactura>(
					new CheckRestriccionesDeCamposDecorator<Manufactura>(						
						new IncluirCamposDecorator<Manufactura>(
							new CamposSplitterDecorator<Manufactura>(
								new CheckNumeroDeColumnasDecorator<Manufactura>(
									new CheckArchivoVacioDecorator<Manufactura>(
										new LineasSplitterDecorator<Manufactura>(
											new MayusculasDecorator<Manufactura>()))))))));
		// @formatter:on
	}

}