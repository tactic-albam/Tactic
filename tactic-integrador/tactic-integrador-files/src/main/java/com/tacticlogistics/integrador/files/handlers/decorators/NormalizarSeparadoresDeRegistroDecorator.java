package com.tacticlogistics.integrador.files.handlers.decorators;

import org.apache.commons.lang3.Validate;

import com.tacticlogistics.integrador.dto.ArchivoDTO;

import lombok.val;

public class NormalizarSeparadoresDeRegistroDecorator<T> extends Decorator<T> {

	public NormalizarSeparadoresDeRegistroDecorator() {
		super();
	}

	public NormalizarSeparadoresDeRegistroDecorator(Filter<T> inner) {
		super(inner);
	}

	@Override
	public ArchivoDTO<T> transformar(ArchivoDTO<T> archivoDTO) {
		final val result = super.transformar(archivoDTO);
		Validate.notNull(result.getDatos());

		val datos = result.getDatos().replaceAll("\\r\\n", "\n").replaceAll("\\n\\n", "\n");
		result.setDatos(datos);
		return result;
	}
}
