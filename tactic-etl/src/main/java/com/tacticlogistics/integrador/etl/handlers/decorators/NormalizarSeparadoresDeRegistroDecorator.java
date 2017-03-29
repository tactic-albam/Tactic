package com.tacticlogistics.integrador.etl.handlers.decorators;

import org.springframework.util.Assert;

import com.tacticlogistics.integrador.etl.handlers.dto.ArchivoDTO;

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
		Assert.notNull(result.getDatos());

		val datos = result.getDatos().replaceAll("\\r\\n", "\n").replaceAll("\\n\\n", "\n");
		result.setDatos(datos);
		return result;
	}
}
