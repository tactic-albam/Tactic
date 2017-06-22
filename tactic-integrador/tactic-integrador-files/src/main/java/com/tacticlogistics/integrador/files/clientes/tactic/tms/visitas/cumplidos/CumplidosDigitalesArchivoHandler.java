package com.tacticlogistics.integrador.files.clientes.tactic.tms.visitas.cumplidos;

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
import com.tacticlogistics.integrador.model.tms.cumplidos.CumplidoDigital;
import com.tacticlogistics.integrador.model.tms.cumplidos.CumplidoDigitalRepository;

@Component
public class CumplidosDigitalesArchivoHandler extends ArchivoPlanoHandler<CumplidoDigital, Long> {
	private static final String CODIGO_TIPO_ARCHIVO = "CUMPLIDOS_DIGITALES";

	private static final String SUBDIRECTORIO_RELATIVO = "TMS\\CUMPLIDOS\\DIGITALES";

	@Autowired
	private CumplidoDigitalRepository repository;

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
	protected JpaRepository<CumplidoDigital, Long> getRepository() {
		return repository;
	}

	@Override
	protected Decorator<CumplidoDigital> getTransformador() {
		// @formatter:off
		return new MapEntidadCumplidoDigitalDecorator(
				new CheckRegistrosDuplicadosDecorator<CumplidoDigital>(
					new CheckRestriccionesDeCamposDecorator<CumplidoDigital>(
						new ExtraerCamposCumplidoDigitalDecorator(
							new FiltrarCumplidoDigitalDecorator(
								new CamposSplitterDecorator<CumplidoDigital>(
									new CheckNumeroDeColumnasDecorator<CumplidoDigital>(
										new CheckArchivoVacioDecorator<CumplidoDigital>(
											new LineasSplitterDecorator<CumplidoDigital>(
												new IncluirEncabezadoDecorator<CumplidoDigital>(
													new NormalizarSeparadoresDeRegistroDecorator<CumplidoDigital>(
														new MayusculasDecorator<CumplidoDigital>())))))))))));
		// @formatter:on
	}
}