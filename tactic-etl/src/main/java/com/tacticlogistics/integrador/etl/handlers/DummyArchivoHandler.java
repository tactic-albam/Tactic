package com.tacticlogistics.integrador.etl.handlers;

import java.nio.file.Path;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import com.tacticlogistics.integrador.etl.readers.Reader;

@Component
public class DummyArchivoHandler extends ArchivoHandler {

	@Override
	protected Reader getReader() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Path getCliente() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Path getDirectorioRelativo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Pattern getFileNamePattern() {
		// TODO Auto-generated method stub
		return null;
	}
}
