package com.tacticlogistics.integrador.files.handlers.decorators;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import com.tacticlogistics.integrador.dto.ArchivoDTO;
import com.tacticlogistics.integrador.dto.RegistroDTO;

import lombok.val;

public class LineasSplitterDecorator<T> extends Decorator<T> {

	public LineasSplitterDecorator() {
		super();
	}

	public LineasSplitterDecorator(Filter<T> inner) {
		super(inner);
	}

	@Override
	public ArchivoDTO<T> transformar(ArchivoDTO<T> archivoDTO) {
		final val result = super.transformar(archivoDTO);
		Assert.notNull(result.getTipoArchivo());
		Assert.notNull(result.getDatos());

		String separador = result.getTipoArchivo().getRegExpSeparadorRegistros();
		String[] lineas = result.getDatos().split(separador,-1);

		List<RegistroDTO<T>> registros = split(lineas);
		
		result.setRegistros(registros);

		return result;
	}

	private List<RegistroDTO<T>> split(String[] lineas) {
		List<RegistroDTO<T>> result = new ArrayList<>();

		int i = 0;
		for (val linea : lineas) {
			if (StringUtils.hasLength(linea)) {
				// @formatter:off
				result.add(RegistroDTO
						.<T> builder()
						.linea(linea)
						.numeroLinea(i++)
						.build());
				// @formatter:on
			}
		}
		return result;
	}
}