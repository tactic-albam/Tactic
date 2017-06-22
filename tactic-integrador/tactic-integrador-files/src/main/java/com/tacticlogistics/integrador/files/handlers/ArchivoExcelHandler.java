package com.tacticlogistics.integrador.files.handlers;

import java.io.Serializable;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;

import com.tacticlogistics.integrador.files.handlers.readers.ExcelWorkSheetReaderDelta;
import com.tacticlogistics.integrador.files.handlers.readers.Reader;

public abstract class ArchivoExcelHandler<T, ID extends Serializable> extends ArchivoHandler<T,ID> {

	@Autowired 
	private ExcelWorkSheetReaderDelta reader;

	public ArchivoExcelHandler() {
		super();
	}

	@Override
	protected Pattern getFileNamePattern() {
		return PATTERN_XLS;
	}

	abstract protected  String getWorkSheetName();
	
	@Override
	protected Reader getReader() {
		if (this.reader.getWorkSheetName() == null) {
			this.reader.setWorkSheetName(getWorkSheetName());
		}
		return reader;
	}
}