package com.tacticlogistics.integrador.etl.handlers.decorators;

import java.util.HashMap;
import java.util.Map;

import org.springframework.util.Assert;

import com.tacticlogistics.integrador.etl.handlers.dto.ArchivoDTO;
import com.tacticlogistics.integrador.etl.model.etl.Campo;

import lombok.val;

public class IncluirCamposDecorator<T> extends Decorator<T> {

	public IncluirCamposDecorator() {
		super();
	}

	public IncluirCamposDecorator(Filter<T> inner) {
		super(inner);
	}

	@Override
	public ArchivoDTO<T> transformar(ArchivoDTO<T> archivoDTO) {
		final val result = super.transformar(archivoDTO);
		Assert.notEmpty(result.getRegistros());

		val registros = result.getRegistros();
		val tipoArchivo = result.getTipoArchivo();
		val campos = tipoArchivo.getCamposIncluidos();

		Map<String, String> map = new HashMap<>();
		for (Campo campo : campos) {
			map.put(campo.getCodigo(), campo.getValorPredeterminado());
		}

		for (val registro : registros) {
			registro.getDatos().putAll(map);
			registro.getOriginales().putAll(map);
		}

		return result;
	}
}
