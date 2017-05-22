package com.tacticlogistics.integrador.files.clientes.tactic.tms.rutas.moviles;

import java.util.stream.Collectors;

import org.springframework.util.Assert;

import com.tacticlogistics.integrador.dto.ArchivoDTO;
import com.tacticlogistics.integrador.files.handlers.decorators.Decorator;
import com.tacticlogistics.integrador.files.handlers.decorators.Filter;
import com.tacticlogistics.integrador.model.tms.rutas.moviles.MovilRutaControl;

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
