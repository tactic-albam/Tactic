package com.tacticlogistics.integrador.files.clientes.tactic.wms.ordenes.estampillado;

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
import com.tacticlogistics.integrador.model.oms.Estampillado;
import com.tacticlogistics.integrador.model.oms.EstampilladoRepository;

@Component
public class EstampilladoArchivoHandler extends ArchivoHandler<Estampillado, Long> {
	private static final String CODIGO_TIPO_ARCHIVO = "ESTAMPILLADOS";

	private static final String WORKSHEET_NAME = "ORDENES";

	@Autowired
	private ExcelWorkSheetReaderDelta reader;

	@Autowired
	private EstampilladoRepository repository;

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
		Path result = Paths.get("WMS\\ORDENES\\ESTAMPILLADOS");
		return result;
	}

	@Override
	protected Pattern getFileNamePattern() {
		return PATTERN_XLS;
	}

	@Override
	protected Decorator<Estampillado> getTransformador() {
		// @formatter:off
		return new MapEntidadEstampilladoDecorator(
				new CheckRegistrosDuplicadosDecorator<Estampillado>(
					new CheckRestriccionesDeCamposDecorator<Estampillado>(
						new IncluirCamposDecorator<Estampillado>(
							new IncluirCamposDecorator<Estampillado>(
								new CamposSplitterDecorator<Estampillado>(
									new CheckNumeroDeColumnasDecorator<Estampillado>(
										new CheckArchivoVacioDecorator<Estampillado>(
											new LineasSplitterDecorator<Estampillado>(
												new MayusculasDecorator<Estampillado>())))))))));
		// @formatter:on
	}

	@Override
	protected JpaRepository<Estampillado, Long> getRepository() {
		return repository;
	}
}
