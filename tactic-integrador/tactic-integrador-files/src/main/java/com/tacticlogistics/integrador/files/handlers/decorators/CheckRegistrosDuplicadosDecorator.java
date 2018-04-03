package com.tacticlogistics.integrador.files.handlers.decorators;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.Validate;
import org.springframework.util.StringUtils;

import com.tacticlogistics.integrador.dto.ArchivoDTO;
import com.tacticlogistics.integrador.dto.RegistroDTO;

import lombok.val;

public class CheckRegistrosDuplicadosDecorator<T> extends Decorator<T> {

	public CheckRegistrosDuplicadosDecorator() {
		super();
	}

	public CheckRegistrosDuplicadosDecorator(Filter<T> inner) {
		super(inner);
	}

	@Override
	public ArchivoDTO<T> transformar(ArchivoDTO<T> archivoDTO) {
		final val result = super.transformar(archivoDTO);
		Validate.notEmpty(result.getRegistros());

		val registros = result.getRegistros();
		List<String> llaves = result.getTipoArchivo().getLlaves();
		boolean error = false;

		for (String llave : llaves) {
			// @formatter:off
			List<String> campos = result
					.getTipoArchivo()
					.getCodigosDeCamposPorLlave(llave);
			// @formatter:on
			val duplicados = checkDuplicados(registros, campos);

			if (duplicados.size() > 1) {
				error = true;
				String mensaje = String.format("error llave duplicada: Los valores de los siguientes campos vienen duplicados en el archivo: %s", StringUtils.collectionToCommaDelimitedString(campos)); 
				for (RegistroDTO<T> registro: duplicados) {
					registro.getErrores().add(mensaje);
				}
			}
		}
		
		if (error) {
			ETLRuntimeException.throwException("Ocurrieron errores durante la verificacion de valores unicos en el archivo", result);
		}

		return result;
	}

	private List<RegistroDTO<T>> checkDuplicados(List<RegistroDTO<T>> registros, List<String> campos) {
		List<RegistroDTO<T>> result = new ArrayList<>();

		Map<String, List<RegistroDTO<T>>> duplicados = new HashMap<>();

		for (val registro : registros) {
			StringBuilder sb = new StringBuilder();
			for (String campo : campos) {
				sb.append(registro.getDatos().get(campo)).append("|");
			}

			String key = sb.toString();
			if (!duplicados.containsKey(key)) {
				List<RegistroDTO<T>> value = new ArrayList<>();
				duplicados.put(key, value);
			}
			duplicados.get(key).add(registro);
		}

		// @formatter:off
		duplicados
		.values()
		.stream()
		.filter(a -> a.size() > 1)
		.forEach(a -> {
			result.addAll(a);
		});
		// @formatter:on

		return result;
	}
}