package com.tacticlogistics.integrador.etl.decorators.checkers.types;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;

import com.tacticlogistics.integrador.etl.model.Campo;

public class DateMaxNumeroDeDiasChecker extends MaxChecker<ChronoLocalDate> {
	@Override
	protected LocalDate getValorLimite(Campo campo) {
		LocalDate result = null;
		if (campo.getValorEnteroMax() != null) {
			LocalDate.now().plusDays(campo.getValorEnteroMax());
		}
		return result;
	}
}