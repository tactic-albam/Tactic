package com.tacticlogistics.integrador.files.handlers.decorators;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

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
		Validate.notEmpty(result.getRegistros());

		val registros = result.getRegistros();
		val tipoArchivo = result.getTipoArchivo();
		val campos = tipoArchivo.getCamposIncluidos();

		Map<String, String> constantes = new HashMap<>();
		Map<String, String> variables = new HashMap<>();
		
		for (Campo campo : campos) {
			String valor = campo.getValorPredeterminado();
			
			if (campo.isTruncarCaracteres()) {
				valor = StringUtils.left(valor, campo.getNumeroCaracteres());
			}
			
			if(StringUtils.startsWith(valor, "${") && StringUtils.endsWith(valor, "}")){
				val key = StringUtils.mid(valor, 2, valor.length()-3);
				variables.put(campo.getCodigo(), key);
			}else{
				constantes.put(campo.getCodigo(), valor);
			}
		}

		for (val registro : registros) {
			registro.getDatos().putAll(constantes);
			registro.getOriginales().putAll(constantes);
		}

		for (String campo : variables.keySet()) {
			String key = variables.get(campo);
			for (val registro : registros) {
				String valor = registro.getDatos().get(key);
				registro.getDatos().put(campo, valor);
				registro.getOriginales().put(campo, valor);
			}
		}

		return result;
	}
}
