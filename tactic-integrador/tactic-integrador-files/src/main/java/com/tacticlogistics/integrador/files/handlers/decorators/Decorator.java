package com.tacticlogistics.integrador.files.handlers.decorators;

import org.apache.commons.lang3.Validate;

import com.tacticlogistics.integrador.dto.ArchivoDTO;

public class Decorator<T> implements Filter<T> {

	private Filter<T> inner;

	public Decorator() {
		inner = null;
	}

	public Decorator(Filter<T> inner) {
		this.inner = inner;
	}

	@Override
	public ArchivoDTO<T> transformar(ArchivoDTO<T> archivoDTO) {
		Validate.notNull(archivoDTO.getPathArchivo());
		Validate.notNull(archivoDTO.getTipoArchivo());

		if (inner != null) {
			return inner.transformar(archivoDTO);
		} else {
			return archivoDTO;
		}
	}
}
