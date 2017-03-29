package com.tacticlogistics.integrador.etl.handlers.decorators;

import org.springframework.util.Assert;

import com.tacticlogistics.integrador.etl.handlers.dto.ArchivoDTO;

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
		Assert.notNull(archivoDTO.getPathArchivo());
		Assert.notNull(archivoDTO.getTipoArchivo());

		if (inner != null) {
			return inner.transformar(archivoDTO);
		} else {
			return archivoDTO;
		}
	}
}
