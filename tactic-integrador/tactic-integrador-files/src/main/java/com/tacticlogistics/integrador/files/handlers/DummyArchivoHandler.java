package com.tacticlogistics.integrador.files.handlers;

import java.nio.file.Path;
import java.util.regex.Pattern;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.tacticlogistics.integrador.files.handlers.decorators.Decorator;
import com.tacticlogistics.integrador.files.handlers.readers.Reader;

@Component
public class DummyArchivoHandler extends ArchivoHandler<Object,Long> {

	@Override
	protected Reader getReader() {
		return null;
	}

	@Override
	protected String getCodigoTipoArchivo() {
		return null;
	}

	@Override
	protected Path getPathCliente() {
		return null;
	}

	@Override
	protected Path getPathDirectorioRelativo() {
		return null;
	}

	@Override
	protected Pattern getFileNamePattern() {
		return null;
	}

	@Override
	protected Decorator<Object> getTransformador() {
		return null;
	}

	@Override
	protected JpaRepository<Object, Long> getRepository() {
		return null;
	}

	@Override
	protected String getClienteCodigo() {
		return null;
	}

	@Override
	protected String getDirectorioRelativo() {
		return null;
	}

}
