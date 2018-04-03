package com.tacticlogistics.integrador.files.handlers.decorators;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

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
		Validate.notNull(result.getTipoArchivo());
		Validate.notNull(result.getDatos());

		String separadorRegistros = result.getTipoArchivo().getRegExpSeparadorRegistros();
		String separadorCampos = result.getTipoArchivo().getRegExpSeparadorCampos();
		String[] lineas = result.getDatos().split(separadorRegistros, -1);

		List<RegistroDTO<T>> registros = split(lineas,separadorCampos);

		result.setRegistros(registros);

		return result;
	}

	private List<RegistroDTO<T>> split(String[] lineas, String separadorCampos) {
		List<RegistroDTO<T>> result = new ArrayList<>();
		int i = 0;
		for (val linea : lineas) {
			if (StringUtils.isEmpty(linea)) {
				continue;
			}
			
			String lineaNormalizada = StringUtils.replacePattern(linea, separadorCampos, "");
			if(StringUtils.isEmpty(lineaNormalizada)){
				continue;
			}
			
			// @formatter:off
			result.add(RegistroDTO
					.<T> builder()
					.linea(linea)
					.numeroLinea(i++)
					.build());
			// @formatter:on
		}
		return result;
	}
}