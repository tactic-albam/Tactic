package com.tacticlogistics.integrador.etl.handlers.decorators.checkers.types;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;

import com.tacticlogistics.integrador.etl.model.etl.Campo;

public class DateMinNumeroDeDiasChecker extends MinChecker<ChronoLocalDate> {
	@Override
	protected LocalDate getValorLimite(Campo campo) {
		LocalDate result = null;
		if (campo.getValorEnteroMin() != null) {
			LocalDate.now().plusDays(campo.getValorEnteroMin());
		}
		return result;
	}
}