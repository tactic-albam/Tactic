package com.tacticlogistics.integrador.etl.handlers.tactic.tms.cumplidos.digitales;

import java.util.stream.Collectors;

import org.springframework.util.Assert;

import com.tacticlogistics.integrador.etl.decorators.Decorator;
import com.tacticlogistics.integrador.etl.decorators.Filter;
import com.tacticlogistics.integrador.etl.dto.ArchivoDTO;
import com.tacticlogistics.integrador.etl.handlers.tactic.tms.cumplidos.digitales.model.CumplidoDigital;

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
		Assert.notEmpty(result.getRegistros());

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
