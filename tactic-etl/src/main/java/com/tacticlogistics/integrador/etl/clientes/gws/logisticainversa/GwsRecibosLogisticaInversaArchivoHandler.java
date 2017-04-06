package com.tacticlogistics.integrador.etl.clientes.gws.logisticainversa;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.tacticlogistics.ClienteCodigoType;
import com.tacticlogistics.integrador.etl.clientes.tactic.oms.MapEntidadReciboDecorator;
import com.tacticlogistics.integrador.etl.handlers.ArchivoHandler;
import com.tacticlogistics.integrador.etl.handlers.decorators.CamposSplitterDecorator;
import com.tacticlogistics.integrador.etl.handlers.decorators.CheckArchivoVacioDecorator;
import com.tacticlogistics.integrador.etl.handlers.decorators.CheckNumeroDeColumnasDecorator;
import com.tacticlogistics.integrador.etl.handlers.decorators.CheckRegistrosDuplicadosDecorator;
import com.tacticlogistics.integrador.etl.handlers.decorators.CheckRestriccionesDeCamposDecorator;
import com.tacticlogistics.integrador.etl.handlers.decorators.Decorator;
import com.tacticlogistics.integrador.etl.handlers.decorators.IncluirCamposDecorator;
import com.tacticlogistics.integrador.etl.handlers.decorators.LineasSplitterDecorator;
import com.tacticlogistics.integrador.etl.handlers.decorators.MayusculasDecorator;
import com.tacticlogistics.integrador.etl.handlers.decorators.NormalizarSeparadoresDeRegistroDecorator;
import com.tacticlogistics.integrador.etl.handlers.readers.CharsetDetectorFileReaderBeta;
import com.tacticlogistics.integrador.etl.handlers.readers.Reader;
import com.tacticlogistics.integrador.etl.model.oms.Recibo;
import com.tacticlogistics.integrador.etl.model.oms.ReciboRepository;

@Component
public class GwsRecibosLogisticaInversaArchivoHandler extends ArchivoHandler<Recibo,Long> {
	private static final String CODIGO_TIPO_ARCHIVO = "GWS_LOGISTICA_INVERSA";

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
		Path result = Paths.get(ClienteCodigoType.GWS.toString());
		return result;
	}

	@Override
	protected Path getSubDirectorioRelativo() {
		Path result = Paths.get("ORDENES\\LOGISTICA_INVERSA");
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
												new NormalizarSeparadoresDeRegistroDecorator<Recibo>(
													new MayusculasDecorator<Recibo>(
				)))))))))));
		// @formatter:on
	}

	@Override
	protected JpaRepository<Recibo, Long> getRepository() {
		return repository;
	}
}
