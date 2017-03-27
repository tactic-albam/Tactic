package com.tacticlogistics.integrador.etl.handlers.tactic.tms.rutas.moviles;

import java.util.stream.Collectors;

import org.springframework.util.Assert;

import com.tacticlogistics.integrador.etl.decorators.Decorator;
import com.tacticlogistics.integrador.etl.decorators.Filter;
import com.tacticlogistics.integrador.etl.dto.ArchivoDTO;
import com.tacticlogistics.integrador.etl.handlers.tactic.tms.rutas.moviles.model.MovilRutaControl;

import lombok.val;

public class FiltrarMovilRutaControlDecorator extends Decorator<MovilRutaControl> {

	public FiltrarMovilRutaControlDecorator() {
		super();
	}

	public FiltrarMovilRutaControlDecorator(Filter<MovilRutaControl> inner) {
		super(inner);
	}

	@Override
	public ArchivoDTO<MovilRutaControl> transformar(ArchivoDTO<MovilRutaControl> archivoDTO) {
		final val result = super.transformar(archivoDTO);
		Assert.notEmpty(result.getRegistros());

		// @formatter:off
		val registros = result
				.getRegistros()
				.stream()
				.filter(a -> a.getDatos().get(MovilRutaControl.MOVIL).startsWith("TACTIC_"))
				.collect(Collectors.toList());

		result.setRegistros(registros);
		// @formatter:on

		return result;
	}
}
