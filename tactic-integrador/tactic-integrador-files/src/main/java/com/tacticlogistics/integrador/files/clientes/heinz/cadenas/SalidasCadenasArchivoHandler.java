package com.tacticlogistics.integrador.files.clientes.heinz.cadenas;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.tacticlogistics.ClienteCodigoType;
import com.tacticlogistics.integrador.files.handlers.ArchivoHandler;
import com.tacticlogistics.integrador.files.handlers.decorators.CamposSplitterDecorator;
import com.tacticlogistics.integrador.files.handlers.decorators.CheckArchivoVacioDecorator;
import com.tacticlogistics.integrador.files.handlers.decorators.CheckNumeroDeColumnasDecorator;
import com.tacticlogistics.integrador.files.handlers.decorators.Decorator;
import com.tacticlogistics.integrador.files.handlers.decorators.IncluirCamposDecorator;
import com.tacticlogistics.integrador.files.handlers.decorators.LineasSplitterDecorator;
import com.tacticlogistics.integrador.files.handlers.decorators.MayusculasDecorator;
import com.tacticlogistics.integrador.files.handlers.decorators.NormalizarSeparadoresDeRegistroDecorator;
import com.tacticlogistics.integrador.files.handlers.readers.ExcelWorkSheetReaderDelta;
import com.tacticlogistics.integrador.files.handlers.readers.Reader;
import com.tacticlogistics.integrador.model.oms.Salida;
import com.tacticlogistics.integrador.model.oms.SalidaRepository;

@Component
public abstract class SalidasCadenasArchivoHandler extends ArchivoHandler<Salida,Long> {
	
	private static final String WORKSHEET_NAME = "0";

	@Autowired
	private ExcelWorkSheetReaderDelta reader;

	@Autowired
	private SalidaRepository repository;

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
	protected Path getCliente() {
		Path result = Paths.get(ClienteCodigoType.HEINZ.toString());
		return result;
	}

	@Override
	protected Path getSubDirectorioRelativo() {
		Path result = Paths.get("CADENAS");
		return result;
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

	@Override
	protected JpaRepository<Salida, Long> getRepository() {
		return repository;
	}
}