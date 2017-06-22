package com.tacticlogistics.integrador.files.clientes.tactic.tms.moviles;

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
import com.tacticlogistics.integrador.files.handlers.decorators.IncluirEncabezadoDecorator;
import com.tacticlogistics.integrador.files.handlers.decorators.LineasSplitterDecorator;
import com.tacticlogistics.integrador.files.handlers.decorators.MayusculasDecorator;
import com.tacticlogistics.integrador.model.tms.rutas.moviles.MovilRutaControl;
import com.tacticlogistics.integrador.model.tms.rutas.moviles.MovilRutaControlRepository;

@Component
public class MovilesRutaControlArchivoHandler extends ArchivoExcelHandler<MovilRutaControl, Long> {
	private static final String WORKSHEET_NAME = "0";

	private static final String CODIGO_TIPO_ARCHIVO = "RUTACONTROL_MOVILES";

	private static final String SUBDIRECTORIO_RELATIVO = "TMS\\RUTAS\\RUTACONTROL\\MOVILES";

	@Autowired
	private MovilRutaControlRepository repository;

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
	protected JpaRepository<MovilRutaControl, Long> getRepository() {
		return repository;
	}

	// ----------------------------------------------------------------------------------------------------------------
	//
	// ----------------------------------------------------------------------------------------------------------------
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
}