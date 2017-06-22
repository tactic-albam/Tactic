package com.tacticlogistics.integrador.files.clientes.heinz.cadenas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.tacticlogistics.ClienteCodigoType;
import com.tacticlogistics.integrador.files.handlers.ArchivoExcelHandler;
import com.tacticlogistics.integrador.files.handlers.decorators.CamposSplitterDecorator;
import com.tacticlogistics.integrador.files.handlers.decorators.CheckArchivoVacioDecorator;
import com.tacticlogistics.integrador.files.handlers.decorators.CheckNumeroDeColumnasDecorator;
import com.tacticlogistics.integrador.files.handlers.decorators.Decorator;
import com.tacticlogistics.integrador.files.handlers.decorators.IncluirCamposDecorator;
import com.tacticlogistics.integrador.files.handlers.decorators.LineasSplitterDecorator;
import com.tacticlogistics.integrador.files.handlers.decorators.MayusculasDecorator;
import com.tacticlogistics.integrador.files.handlers.decorators.NormalizarSeparadoresDeRegistroDecorator;
import com.tacticlogistics.integrador.model.oms.Salida;
import com.tacticlogistics.integrador.model.oms.SalidaRepository;

@Component
public abstract class SalidasCadenasArchivoHandler extends ArchivoExcelHandler<Salida,Long> {
	
	private static final String WORKSHEET_NAME = "0";
	
	private static final String SUBDIRECTORIO_RELATIVO = "CADENAS";

	@Autowired
	private SalidaRepository repository;

	// ----------------------------------------------------------------------------------------------------------------
	//
	// ----------------------------------------------------------------------------------------------------------------
	@Override
	protected String getWorkSheetName() {
		return WORKSHEET_NAME;
	}

	@Override
	protected String getClienteCodigo() {
		return ClienteCodigoType.HEINZ.toString();
	}

	@Override
	protected String getDirectorioRelativo() {
		return SUBDIRECTORIO_RELATIVO;
	}

	// ----------------------------------------------------------------------------------------------------------------
	//
	// ----------------------------------------------------------------------------------------------------------------
	@Override
	protected JpaRepository<Salida, Long> getRepository() {
		return repository;
	}

	@Override
	protected Decorator<Salida> getTransformador() {
		// @formatter:off
		return new IncluirCamposDecorator<Salida>(
				new CamposSplitterDecorator<Salida>(
					new CheckNumeroDeColumnasDecorator<Salida>(
						new CheckArchivoVacioDecorator<Salida>(
							new LineasSplitterDecorator<Salida>(
								new NormalizarSeparadoresDeRegistroDecorator<Salida>(
									new MayusculasDecorator<Salida>(
		)))))));
		// @formatter:on
	}
}