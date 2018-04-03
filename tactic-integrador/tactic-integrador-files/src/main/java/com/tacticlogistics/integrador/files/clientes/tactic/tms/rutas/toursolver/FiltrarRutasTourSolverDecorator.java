package com.tacticlogistics.integrador.files.clientes.tactic.tms.rutas.toursolver;

import java.util.stream.Collectors;

import org.apache.commons.lang3.Validate;

import com.tacticlogistics.integrador.dto.ArchivoDTO;
import com.tacticlogistics.integrador.files.handlers.decorators.Decorator;
import com.tacticlogistics.integrador.files.handlers.decorators.Filter;
import com.tacticlogistics.integrador.model.tms.rutas.toursolver.RutaTourSolver;

import lombok.val;

public class FiltrarRutasTourSolverDecorator extends Decorator<RutaTourSolver> {

	public FiltrarRutasTourSolverDecorator() {
		super();
	}

	public FiltrarRutasTourSolverDecorator(Filter<RutaTourSolver> inner) {
		super(inner);
	}

	@Override
	public ArchivoDTO<RutaTourSolver> transformar(ArchivoDTO<RutaTourSolver> archivoDTO) {
		final val result = super.transformar(archivoDTO);
		Validate.notEmpty(result.getRegistros());

		// @formatter:off
		val registros = result
				.getRegistros()
				.stream()
				.filter(a -> !a.getDatos().get(RutaTourSolver.ID_ORDEN).isEmpty())
				.collect(Collectors.toList());

		result.setRegistros(registros);
		// @formatter:on

		return result;
	}
}
