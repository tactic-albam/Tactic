package com.tacticlogistics.integrador.files.handlers.decorators;

import org.apache.commons.lang3.Validate;

import com.tacticlogistics.integrador.dto.ArchivoDTO;

import lombok.val;

public class CheckArchivoVacioDecorator<T> extends Decorator<T> {

	public CheckArchivoVacioDecorator() {
		super();
	}

	public CheckArchivoVacioDecorator(Filter<T> inner) {
		super(inner);
	}

	@Override
	public ArchivoDTO<T> transformar(ArchivoDTO<T> archivoDTO) {
		final val result = super.transformar(archivoDTO);
		Validate.notEmpty(result.getRegistros());

		if (result.getRegistros().isEmpty()) {
			throw new RuntimeException("El archivo se encuentra vacío");
		}

		return result;
	}
}
