package com.tacticlogistics.integrador.files.clientes.tactic.wms.ordenes.ofertas;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.tacticlogistics.ClienteCodigoType;
import com.tacticlogistics.integrador.files.handlers.ArchivoHandler;
import com.tacticlogistics.integrador.files.handlers.decorators.CamposSplitterDecorator;
import com.tacticlogistics.integrador.files.handlers.decorators.CheckArchivoVacioDecorator;
import com.tacticlogistics.integrador.files.handlers.decorators.CheckNumeroDeColumnasDecorator;
import com.tacticlogistics.integrador.files.handlers.decorators.CheckRegistrosDuplicadosDecorator;
import com.tacticlogistics.integrador.files.handlers.decorators.CheckRestriccionesDeCamposDecorator;
import com.tacticlogistics.integrador.files.handlers.decorators.Decorator;
import com.tacticlogistics.integrador.files.handlers.decorators.IncluirCamposDecorator;
import com.tacticlogistics.integrador.files.handlers.decorators.LineasSplitterDecorator;
import com.tacticlogistics.integrador.files.handlers.decorators.MayusculasDecorator;
import com.tacticlogistics.integrador.files.handlers.readers.ExcelWorkSheetReaderDelta;
import com.tacticlogistics.integrador.files.handlers.readers.Reader;
import com.tacticlogistics.integrador.model.oms.Oferta;
import com.tacticlogistics.integrador.model.oms.OfertaRepository;

@Component
public class OfertaArchivoHandler extends ArchivoHandler<Oferta, Long> {
	private static final String CODIGO_TIPO_ARCHIVO = "OFERTAS";

	private static final String WORKSHEET_NAME = "ORDENES";

	@Autowired
	private ExcelWorkSheetReaderDelta reader;

	@Autowired
	private OfertaRepository repository;

	// ----------------------------------------------------------------------------------------------------------------
	//
	// ----------------------------------------------------------------------------------------------------------------
	@Override
	protected Reader getReader() {
		if (this.reader.getWorkSheetName() == null) {
			this.reader.setWorkSheetName(WORKSHEET_NAME);
		}
		return reader;
	}

	@Override
	protected String getCodigoTipoArchivo() {
		return CODIGO_TIPO_ARCHIVO;
	}

	@Override
	protected Path getCliente() {
		Path result = Paths.get(ClienteCodigoType.TACTIC.toString());
		return result;
	}

	@Override
	protected Path getSubDirectorioRelativo() {
		Path result = Paths.get("WMS\\ORDENES\\OFERTAS");
		return result;
	}

	@Override
	protected Pattern getFileNamePattern() {
		return PATTERN_XLS;
	}

	@Override
	protected Decorator<Oferta> getTransformador() {
		// @formatter:off
		return new MapEntidadOfertaDecorator(
				new CheckRegistrosDuplicadosDecorator<Oferta>(
					new CheckRestriccionesDeCamposDecorator<Oferta>(						
						new IncluirCamposDecorator<Oferta>(
							new CamposSplitterDecorator<Oferta>(
								new CheckNumeroDeColumnasDecorator<Oferta>(
										new CheckArchivoVacioDecorator<Oferta>(
											new LineasSplitterDecorator<Oferta>(
													new MayusculasDecorator<Oferta>()))))))));
		// @formatter:on
	}

	@Override
	protected JpaRepository<Oferta, Long> getRepository() {
		return repository;
	}
}
