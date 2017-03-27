package com.tacticlogistics.integrador.etl.handlers.tactic.tms;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tacticlogistics.ClienteCodigoType;
import com.tacticlogistics.integrador.etl.handlers.ArchivoHandler;
import com.tacticlogistics.integrador.etl.readers.ExcelWorkSheetReaderBeta;
import com.tacticlogistics.integrador.etl.readers.Reader;

@Component
public class RutasTourSolverArchivoHandler extends ArchivoHandler {
	private static final String WORKSHEET_NAME = "Informe";
	@Autowired
	private ExcelWorkSheetReaderBeta reader;

	@Override
	protected Reader getReader() {
		if(this.reader.getWorkSheetName() == null){
			this.reader.setWorkSheetName(WORKSHEET_NAME);
		}
		return reader;
	}

	@Override
	protected Path getCliente() {
		Path result = Paths.get(ClienteCodigoType.TACTIC.toString());
		return result;
	}

	@Override
	protected Path getDirectorioRelativo() {
		Path result = Paths.get("TMS", "RUTAS", "TOURSOLVER");
		return result;
	}

	@Override
	protected Pattern getFileNamePattern() {
		return PATTERN_XLS;
	}
}
