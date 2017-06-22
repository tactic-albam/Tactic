package com.tacticlogistics.integrador.files.clientes.tactic.tms.rutas.manuales;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.tacticlogistics.ClienteCodigoType;
import com.tacticlogistics.integrador.files.handlers.ArchivoPlanoHandler;
import com.tacticlogistics.integrador.files.handlers.decorators.CamposSplitterDecorator;
import com.tacticlogistics.integrador.files.handlers.decorators.CheckArchivoVacioDecorator;
import com.tacticlogistics.integrador.files.handlers.decorators.CheckNumeroDeColumnasDecorator;
import com.tacticlogistics.integrador.files.handlers.decorators.CheckRegistrosDuplicadosDecorator;
import com.tacticlogistics.integrador.files.handlers.decorators.CheckRestriccionesDeCamposDecorator;
import com.tacticlogistics.integrador.files.handlers.decorators.Decorator;
import com.tacticlogistics.integrador.files.handlers.decorators.IncluirEncabezadoDecorator;
import com.tacticlogistics.integrador.files.handlers.decorators.LineasSplitterDecorator;
import com.tacticlogistics.integrador.files.handlers.decorators.MayusculasDecorator;
import com.tacticlogistics.integrador.files.handlers.decorators.NormalizarSeparadoresDeRegistroDecorator;
import com.tacticlogistics.integrador.model.tms.rutas.manuales.RutaManual;
import com.tacticlogistics.integrador.model.tms.rutas.manuales.RutaManualRepository;

@Component
public class RutasManualesArchivoHandler extends ArchivoPlanoHandler<RutaManual, Long> {
	private static final String CODIGO_TIPO_ARCHIVO = "RUTAS_MANUALES";

	private static final String SUBDIRECTORIO_RELATIVO = "TMS\\RUTAS\\MANUALES";

	@Autowired
	private RutaManualRepository repository;

	// ----------------------------------------------------------------------------------------------------------------
	//
	// ----------------------------------------------------------------------------------------------------------------
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


	// ----------------------------------------------------------------------------------------------------------------
	//
	// ----------------------------------------------------------------------------------------------------------------
	@Override
	protected JpaRepository<RutaManual, Long> getRepository() {
		return repository;
	}

	@Override
	protected Decorator<RutaManual> getTransformador() {
		// @formatter:off
		return new MapEntidadRutaManualDecorator(
				new CheckRegistrosDuplicadosDecorator<RutaManual>(
					new CheckRestriccionesDeCamposDecorator<RutaManual>(
							new CamposSplitterDecorator<RutaManual>(
								new CheckNumeroDeColumnasDecorator<RutaManual>(
									new CheckArchivoVacioDecorator<RutaManual>(
										new LineasSplitterDecorator<RutaManual>(
											new IncluirEncabezadoDecorator<RutaManual>(
												new NormalizarSeparadoresDeRegistroDecorator<RutaManual>(
													new MayusculasDecorator<RutaManual>(
				))))))))));
		// @formatter:on
	}
}