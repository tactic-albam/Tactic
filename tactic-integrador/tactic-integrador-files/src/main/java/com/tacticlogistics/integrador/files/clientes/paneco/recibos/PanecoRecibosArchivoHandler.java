package com.tacticlogistics.integrador.files.clientes.paneco.recibos;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.tacticlogistics.ClienteCodigoType;
import com.tacticlogistics.integrador.files.clientes.tactic.oms.MapEntidadReciboDecorator;
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
import com.tacticlogistics.integrador.files.handlers.readers.CharsetDetectorFileReaderBeta;
import com.tacticlogistics.integrador.files.handlers.readers.Reader;
import com.tacticlogistics.integrador.model.oms.Recibo;
import com.tacticlogistics.integrador.model.oms.ReciboRepository;

@Component
public class PanecoRecibosArchivoHandler extends ArchivoHandler<Recibo,Long> {
	private static final String CODIGO_TIPO_ARCHIVO = "PANECO_RECIBOS";

	@Autowired
	private CharsetDetectorFileReaderBeta reader;

	@Autowired
	private ReciboRepository repository;

	// ----------------------------------------------------------------------------------------------------------------
	//
	// ----------------------------------------------------------------------------------------------------------------
	@Override
	protected Reader getReader() {
		return reader;
	}

	@Override
	protected String getCodigoTipoArchivo() {
		return CODIGO_TIPO_ARCHIVO;
	}

	@Override
	protected Path getCliente() {
		Path result = Paths.get(ClienteCodigoType.PANECO.toString());
		return result;
	}

	@Override
	protected Path getSubDirectorioRelativo() {
		Path result = Paths.get("ORDENES\\COMPRAS");
		return result;
	}

	@Override
	protected Pattern getFileNamePattern() {
		return PATTERN_TXT;
	}
	
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
												new IncluirEncabezadoDecorator<Recibo>(
													new NormalizarSeparadoresDeRegistroDecorator<Recibo>(
														new MayusculasDecorator<Recibo>(
				))))))))))));
		// @formatter:on
	}

	@Override
	protected JpaRepository<Recibo, Long> getRepository() {
		return repository;
	}
}
