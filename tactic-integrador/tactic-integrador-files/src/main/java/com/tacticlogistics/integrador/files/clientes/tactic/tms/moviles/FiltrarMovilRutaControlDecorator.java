package com.tacticlogistics.integrador.files.clientes.tactic.tms.moviles;

import java.util.stream.Collectors;

import org.apache.commons.lang3.Validate;

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
		Validate.notEmpty(result.getRegistros());

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
