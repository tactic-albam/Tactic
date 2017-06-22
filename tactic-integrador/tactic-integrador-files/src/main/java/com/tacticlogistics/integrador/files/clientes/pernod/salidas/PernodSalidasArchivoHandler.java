package com.tacticlogistics.integrador.files.clientes.pernod.salidas;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.tacticlogistics.ClienteCodigoType;
import com.tacticlogistics.integrador.files.clientes.tactic.oms.salidas.MapEntidadSalidaDecorator;
import com.tacticlogistics.integrador.files.handlers.ArchivoHandler;
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
import com.tacticlogistics.integrador.files.handlers.readers.Reader;
import com.tacticlogistics.integrador.files.handlers.readers.XMLFileReader;
import com.tacticlogistics.integrador.model.oms.Salida;
import com.tacticlogistics.integrador.model.oms.SalidaRepository;

@Component
public class PernodSalidasArchivoHandler extends ArchivoHandler<Salida,Long> {
	private static final String CODIGO_TIPO_ARCHIVO = "PERNOD_SALIDAS";

	private static final String SUBDIRECTORIO_RELATIVO = "OMS\\VENTAS";

	@Autowired
	private XMLFileReader reader;

	@Autowired
	private SalidaRepository repository;

	// ----------------------------------------------------------------------------------------------------------------
	//
	// ----------------------------------------------------------------------------------------------------------------
	@Override
	protected Reader getReader() {
		return reader;
	}

	@Override
	protected String getClienteCodigo() {
		return ClienteCodigoType.PERNOD.toString();
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
	protected Pattern getFileNamePattern() {
		return PATTERN_XML;
	}

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
						new IncluirCamposDecorator<Salida>(
							new CamposSplitterDecorator<Salida>(
								new CheckNumeroDeColumnasDecorator<Salida>(
									new CheckArchivoVacioDecorator<Salida>(
										new LineasSplitterDecorator<Salida>(
											new IncluirEncabezadoDecorator<Salida>(
												new NormalizarSeparadoresDeRegistroDecorator<Salida>(
													new MayusculasDecorator<Salida>()))))))))));
		// @formatter:on
	}
}
