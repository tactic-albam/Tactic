package com.tacticlogistics.integrador.files.handlers.decorators;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import com.tacticlogistics.integrador.dto.ArchivoDTO;
import com.tacticlogistics.integrador.model.etl.tipoarchivo.Campo;

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
			String valor = campo.getValorPredeterminado();
			if (campo.isTruncarCaracteres()) {
				valor = StringUtils.left(valor, campo.getNumeroCaracteres());
			}
			map.put(campo.getCodigo(), valor);
		}

		for (val registro : registros) {
			registro.getDatos().putAll(map);
			registro.getOriginales().putAll(map);
		}

		return result;
	}
}
