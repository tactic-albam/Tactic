package com.tacticlogistics.integrador.files.clientes.heinz.cadenas;

import java.util.regex.Pattern;

import org.apache.commons.lang3.Validate;

import com.tacticlogistics.integrador.dto.ArchivoDTO;
import com.tacticlogistics.integrador.files.handlers.decorators.Decorator;
import com.tacticlogistics.integrador.files.handlers.decorators.Filter;

import lombok.val;

public class LimpiarFuncionTDecorator<T> extends Decorator<T> {

	public LimpiarFuncionTDecorator() {
		super();
	}

	public LimpiarFuncionTDecorator(Filter<T> inner) {
		super(inner);
	}

	@Override
	public ArchivoDTO<T> transformar(ArchivoDTO<T> archivoDTO) {
		final val result = super.transformar(archivoDTO);
		Validate.notEmpty(result.getRegistros());
		
		Pattern pattern = Pattern.compile((".*=T\\(\\\".*\\\"\\).*"));
		Pattern begin = Pattern.compile("=T\\(\\\"", Pattern.DOTALL);
		Pattern end = Pattern.compile("\\\"\\)", Pattern.DOTALL);
				
		val registros = result.getRegistros();
		for (val registro : registros) {
			String linea = registro.getLinea();
			if(pattern.matcher(linea).matches()){
				linea = begin.matcher(linea).replaceAll("");
				linea = end.matcher(linea).replaceAll("");
			}
			registro.setLinea(linea);
		}

		return result;
	}
}
