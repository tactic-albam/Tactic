package com.tacticlogistics.integrador.files.clientes.tactic.oms.recibos;

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
import com.tacticlogistics.integrador.model.oms.Recibo;
import com.tacticlogistics.integrador.model.oms.ReciboRepository;

public abstract class AbstractPlantillaRecibosHandler extends ArchivoExcelHandler<Recibo, Long> {

	static final String WORKSHEET_NAME = "ORDENES";

	@Autowired
	private ReciboRepository repository;

	public AbstractPlantillaRecibosHandler() {
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
	protected JpaRepository<Recibo, Long> getRepository() {
		return repository;
	}
	
	// ----------------------------------------------------------------------------------------------------------------
	//
	// ----------------------------------------------------------------------------------------------------------------
	@Override
	protected Decorator<Recibo> getTransformador() {
		// @formatter:off
		return new MapEntidadReciboDecorator(
				new CheckRegistrosDuplicadosDecorator<Recibo>(
					new CheckRestriccionesDeCamposDecorator<Recibo>(
						new EnriquecerCamposDecorator(
							new IncluirCamposDecorator<Recibo>(
								new CamposSplitterDecorator<Recibo>(
									new CheckNumeroDeColumnasDecorator<Recibo>(
										new CheckArchivoVacioDecorator<Recibo>(
											new LineasSplitterDecorator<Recibo>(
												new MayusculasDecorator<Recibo>(
			))))))))));
		// @formatter:on
	}
}