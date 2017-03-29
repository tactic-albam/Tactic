package com.tacticlogistics.integrador.etl.handlers.decorators;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import com.tacticlogistics.integrador.etl.handlers.dto.ArchivoDTO;

import lombok.val;

public class IncluirEncabezadoDecorator<T> extends Decorator<T> {

	public IncluirEncabezadoDecorator() {
		super();
	}

	public IncluirEncabezadoDecorator(Filter<T> inner) {
		super(inner);
	}

	@Override
	public ArchivoDTO<T> transformar(ArchivoDTO<T> archivoDTO) {
		final val result = super.transformar(archivoDTO);
		val tipoArchivo = result.getTipoArchivo();
		Assert.notNull(tipoArchivo);
		Assert.notNull(result.getDatos());

		List<String> campos = tipoArchivo
				.getCamposNoIncluidos()
				.stream()
				.map(a -> a.getNombre())
				.collect(Collectors.toList());
		
		String encabezados = StringUtils.collectionToDelimitedString(campos, tipoArchivo.getUnescapedSeparadorCampos());
		
		// @formatter:off
		result.setDatos(
				(new StringBuilder())
				.append(encabezados)
				.append(tipoArchivo.getUnescapedSeparadorRegistros())
				.append(result.getDatos())
				.toString()				
				);
		// @formatter:on

		return result;
	}

}
