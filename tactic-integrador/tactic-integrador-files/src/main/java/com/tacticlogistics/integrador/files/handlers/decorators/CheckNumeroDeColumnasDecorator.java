package com.tacticlogistics.integrador.files.handlers.decorators;

import java.util.List;

import org.springframework.util.Assert;

import com.tacticlogistics.integrador.dto.ArchivoDTO;
import com.tacticlogistics.integrador.dto.RegistroDTO;

import lombok.val;

public class CheckNumeroDeColumnasDecorator<T> extends Decorator<T> {

	public CheckNumeroDeColumnasDecorator() {
		super();
	}

	public CheckNumeroDeColumnasDecorator(Filter<T> inner) {
		super(inner);
	}

	@Override
	public ArchivoDTO<T> transformar(ArchivoDTO<T> archivoDTO) {
		final val result = super.transformar(archivoDTO);
		Assert.notEmpty(result.getRegistros());

		val registros = result.getRegistros();
		String separador = result.getTipoArchivo().getRegExpSeparadorCampos();
		String[] linea = registros.get(0).getLinea().split(separador, -1);
		int numeroColumnas = linea.length;

		boolean error = checkNumeroColumnas(registros, numeroColumnas, separador);

		if (error) {
			String mensaje = "El archivo presenta errores en el numero de columnas";
			ETLRuntimeException.throwException(mensaje,result);
		}

		return result;
	}

	private boolean checkNumeroColumnas(final List<RegistroDTO<T>> registros, int numeroColumnas, String separador) {
		boolean result = false;
		for (val registro : registros) {
			int n = registro.getLinea().split(separador, -1).length;

			if (n != numeroColumnas) {
				result = true;
				String mensaje = "error número de columnas: Se esperaban %d columnas, pero el registro contiene %d";
				registro.getErrores().add(String.format(mensaje, numeroColumnas, n));
			}
		}

		return result;
	}
}
