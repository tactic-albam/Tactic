package com.tacticlogistics.integrador.files.clientes.paneco.salidas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.tacticlogistics.ClienteCodigoType;
import com.tacticlogistics.integrador.files.clientes.tactic.oms.salidas.MapEntidadSalidaDecorator;
import com.tacticlogistics.integrador.files.handlers.ArchivoPlanoHandler;
import com.tacticlogistics.integrador.files.handlers.decorators.CamposSplitterDecorator;
import com.tacticlogistics.integrador.files.handlers.decorators.CheckArchivoVacioDecorator;
import com.tacticlogistics.integrador.files.handlers.decorators.CheckNumeroDeColumnasDecorator;
import com.tacticlogistics.integrador.files.handlers.decorators.CheckRegistrosDuplicadosDecorator;
import com.tacticlogistics.integrador.files.handlers.decorators.CheckRestriccionesDeCamposDecorator;
import com.tacticlogistics.integrador.files.handlers.decorators.Decorator;
import com.tacticlogistics.integrador.files.handlers.decorators.IncluirCamposDecorator;
import com.tacticlogistics.integrador.files.handlers.decorators.IncluirEncabezadoDecorator;
import com.tacticlogistics.integrador.files.handlers.decorators.LineasSplitterDecorator;
import com.tacticlogistics.integrador.files.handlers.decorators.MayusculasDecorator;
import com.tacticlogistics.integrador.files.handlers.decorators.NormalizarSeparadoresDeRegistroDecorator;
import com.tacticlogistics.integrador.model.oms.Salida;
import com.tacticlogistics.integrador.model.oms.SalidaRepository;

@Component
public class PanecoSalidasArchivoHandler extends ArchivoPlanoHandler<Salida,Long> {
	private static final String CODIGO_TIPO_ARCHIVO = "PANECO_SALIDAS";

	private static final String SUBDIRECTORIO_RELATIVO = "ORDENES\\VENTAS";

	@Autowired
	private SalidaRepository repository;

	// ----------------------------------------------------------------------------------------------------------------
	//
	// ----------------------------------------------------------------------------------------------------------------
	@Override
	protected String getClienteCodigo() {
		return ClienteCodigoType.PANECO.toString();
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
	protected JpaRepository<Salida, Long> getRepository() {
		return repository;
	}

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
												new IncluirEncabezadoDecorator<Salida>(
													new NormalizarSeparadoresDeRegistroDecorator<Salida>(
														new MayusculasDecorator<Salida>(
				))))))))))));
		// @formatter:on
	}
}