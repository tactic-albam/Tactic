package com.tacticlogistics.integrador.etl.handlers.tactic.tms.rutas.toursolver;

import java.util.stream.Collectors;

import org.springframework.util.Assert;

import com.tacticlogistics.integrador.etl.decorators.Decorator;
import com.tacticlogistics.integrador.etl.decorators.Filter;
import com.tacticlogistics.integrador.etl.dto.ArchivoDTO;
import com.tacticlogistics.integrador.etl.handlers.tactic.tms.rutas.toursolver.model.RutaTourSolver;

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
		Assert.notEmpty(result.getRegistros());

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
