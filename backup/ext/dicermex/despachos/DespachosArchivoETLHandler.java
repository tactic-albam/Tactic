package com.tacticlogistics.integrador.ext.dicermex.despachos;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.tacticlogistics.integrador.etl.file.ArchivoHandler;
import com.tacticlogistics.integrador.etl.file.FileExtractor;
import com.tacticlogistics.integrador.etl.file.FileLoader;
import com.tacticlogistics.integrador.ext.dicermex.despachos.dto.DespachoDTO;

//@Component
public class DespachosArchivoETLHandler extends ArchivoHandler {

	private static final Pattern PATTERN = Pattern.compile("(?i:(.*)ESBOrdenesDeDespacho(Parcial?)_(\\d+)\\.txt)");

	@Value("${etl.directorio.entradas}")
	private String subDirectorioDeEntradas;
	
	@Autowired
	private DespachosETLFileExtractor etlFileExtractor;
	
	@Override
	protected Pattern getPattern() {
		return PATTERN;
	}
	
	@Override
	protected String getWorkingDirectory() {
		return subDirectorioDeEntradas;
	}

	@Override
	protected FileExtractor<DespachoDTO> getFileExtractor() {
		return etlFileExtractor;
	}

	@Override
	protected FileLoader getFileLoader() {
		return new FileLoader();
	}
}
