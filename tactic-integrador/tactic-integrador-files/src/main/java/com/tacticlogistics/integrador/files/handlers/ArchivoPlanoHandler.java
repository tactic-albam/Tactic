package com.tacticlogistics.integrador.files.handlers;

import java.io.Serializable;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;

import com.tacticlogistics.integrador.files.handlers.readers.CharsetDetectorFileReaderBeta;
import com.tacticlogistics.integrador.files.handlers.readers.Reader;

public abstract class ArchivoPlanoHandler<T, ID extends Serializable> extends ArchivoHandler<T,ID> {

	@Autowired
	protected CharsetDetectorFileReaderBeta reader;

	public ArchivoPlanoHandler() {
		super();
	}
	
	@Override
	protected Pattern getFileNamePattern() {
		return PATTERN_TXT;
	}

	@Override
	protected Reader getReader() {
		return reader;
	}
}