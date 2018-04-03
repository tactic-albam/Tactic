package com.tacticlogistics.integrador.files.clientes.tactic.tms.visitas.cumplidos;

import java.util.stream.Collectors;

import org.apache.commons.lang3.Validate;

import com.tacticlogistics.integrador.dto.ArchivoDTO;
import com.tacticlogistics.integrador.files.handlers.decorators.Decorator;
import com.tacticlogistics.integrador.files.handlers.decorators.Filter;
import com.tacticlogistics.integrador.model.tms.cumplidos.CumplidoDigital;

import lombok.val;

public class FiltrarCumplidoDigitalDecorator extends Decorator<CumplidoDigital> {

	public FiltrarCumplidoDigitalDecorator() {
		super();
	}

	public FiltrarCumplidoDigitalDecorator(Filter<CumplidoDigital> inner) {
		super(inner);
	}

	@Override
	public ArchivoDTO<CumplidoDigital> transformar(ArchivoDTO<CumplidoDigital> archivoDTO) {
		final val result = super.transformar(archivoDTO);
		Validate.notEmpty(result.getRegistros());

		// @formatter:off
		val registros = result
				.getRegistros()
				.stream()
				.filter(a -> a.getDatos().get(CumplidoDigital.NOMBRE_ARCHIVO).endsWith(".PDF"))
				.collect(Collectors.toList());

		result.setRegistros(registros);
		// @formatter:on

		return result;
	}
}
