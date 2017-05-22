package com.tacticlogistics.integrador.files.handlers.decorators;

import org.springframework.util.Assert;

import com.tacticlogistics.integrador.dto.ArchivoDTO;

import lombok.val;

public class MayusculasDecorator<T> extends Decorator<T> {

	public MayusculasDecorator() {
		super();
	}

	public MayusculasDecorator(Filter<T> inner) {
		super(inner);
	}

	@Override
	public ArchivoDTO<T> transformar(ArchivoDTO<T> archivoDTO) {
		final val result = super.transformar(archivoDTO);
		Assert.notNull(result.getDatos());
		
		val datos = result.getDatos().toUpperCase();
		
		result.setDatos(datos);
		return result;
	}
}
