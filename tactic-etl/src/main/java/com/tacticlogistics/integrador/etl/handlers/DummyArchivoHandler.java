package com.tacticlogistics.integrador.etl.handlers;

import java.nio.file.Path;
import java.util.regex.Pattern;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.tacticlogistics.integrador.etl.decorators.Decorator;
import com.tacticlogistics.integrador.etl.readers.Reader;

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
	protected Path getCliente() {
		return null;
	}

	@Override
	protected Path getSubDirectorioRelativo() {
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

}
