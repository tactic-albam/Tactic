package com.tacticlogistics.integrador.etl.handlers.decorators;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;

import com.tacticlogistics.integrador.etl.handlers.dto.ArchivoDTO;
import com.tacticlogistics.integrador.etl.handlers.dto.RegistroDTO;
import com.tacticlogistics.integrador.etl.model.etl.Campo;

import lombok.val;

public class CamposSplitterDecorator<T> extends Decorator<T> {

	public CamposSplitterDecorator() {
		super();
	}

	public CamposSplitterDecorator(Filter<T> inner) {
		super(inner);
	}

	@Override
	public ArchivoDTO<T> transformar(ArchivoDTO<T> archivoDTO) {
		final val result = super.transformar(archivoDTO);
		Assert.notEmpty(result.getRegistros());

		val registros = result.getRegistros();
		val tipoArchivo = result.getTipoArchivo();
		String separador = tipoArchivo.getRegExpSeparadorCampos();
		val campos = tipoArchivo.getCamposNoIncluidos();
		boolean error = false;

		Map<String, Integer> mapping = getMapping(registros.get(0), campos, separador);

		for (int i = 1; i < registros.size(); i++) {
			String datos[] = registros.get(i).getLinea().split(separador, -1);
			val registro = registros.get(i);
			registro.getDatos().clear();
			registro.getOriginales().clear();

			try {
				val map = getDatos(campos, mapping, datos);
				registro.getDatos().putAll(map);
				registro.getOriginales().putAll(map);
			} catch (RuntimeException e) {
				error = true;
				registro.getErrores().add(StringUtils.defaultString(e.getMessage()));
			}
		}

		if (error) {
			ETLRuntimeException.throwException(
					"Ocurrieron errores durante la separación de los campos de los registros del archivo", result);
		}

		registros.remove(0);
		return result;
	}

	private Map<String, Integer> getMapping(RegistroDTO<T> registro, List<Campo> campos, String separador) {
		Map<String, Integer> result = new HashMap<>();
		List<String> errores = new ArrayList<>();

		String[] datos = registro.getLinea().split(separador, -1);
		for (int i = 0; i < datos.length; i++) {
			datos[i] = datos[i].trim();
		}

		for (Campo campo : campos) {
			boolean encontrado = false;
			for (int i = 0; i < datos.length; i++) {
				if (campo.getNombre().equalsIgnoreCase(datos[i])) {
					result.put(campo.getCodigo(), i);
					encontrado = true;
					break;
				}
			}

			if (!encontrado) {
				errores.add(campo.getNombre());
			}
		}

		if (!errores.isEmpty()) {
			String mensaje = "error columnas: La siguientes columnas no se encontraron en el archivo: %s";
			throw new RuntimeException(String.format(mensaje, StringUtils.join(errores, ",")));
		}

		return result;
	}

	private Map<String, String> getDatos(List<Campo> campos, Map<String, Integer> mapping, String[] datos) {
		Map<String, String> result = new HashMap<>();
		StringBuilder sb = new StringBuilder();
		for (Campo campo : campos) {
			String key = campo.getCodigo();

			try {
				String valor = getDato(datos, key, mapping);
				if (valor.isEmpty() && !campo.getValorPredeterminado().isEmpty()) {
					valor = campo.getValorPredeterminado();
				}
				if (campo.isTruncarCaracteres()) {
					int numeroCaracteres = campo.getNumeroCaracteres();
					if (valor.length() > numeroCaracteres) {
						valor = StringUtils.left(valor, numeroCaracteres);
					}
				}
				result.put(key, valor);
			} catch (IndexOutOfBoundsException e) {
				String mensaje = "No se encontró el campo %s en la posición %d";
				mensaje = String.format(mensaje, key, mapping.get(key));
				sb.append(mensaje);
				sb.append(",");
			} catch (RuntimeException e) {
				String mensaje = "Ocurrio el siguiente error al leer el campo %s en la posición %d";
				mensaje = String.format(mensaje, key, mapping.get(key));
				sb.append(mensaje);
				sb.append(",");
			}
		}
		if (sb.length() > 0) {
			throw new RuntimeException(sb.toString());
		}

		return result;
	}

	private String getDato(String[] datos, String key, Map<String, Integer> mapping) {
		return datos[mapping.get(key)].trim();
	}
}
