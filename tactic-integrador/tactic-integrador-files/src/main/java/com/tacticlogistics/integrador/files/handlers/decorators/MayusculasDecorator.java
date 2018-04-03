package com.tacticlogistics.integrador.files.handlers.decorators;

import org.apache.commons.lang3.Validate;

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
		Validate.notNull(result.getDatos());
		
		val datos = result.getDatos().toUpperCase();
		
		result.setDatos(datos);
		return result;
	}
}
