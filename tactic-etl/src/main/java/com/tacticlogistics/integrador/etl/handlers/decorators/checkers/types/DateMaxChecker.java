package com.tacticlogistics.integrador.etl.handlers.decorators.checkers.types;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;

import com.tacticlogistics.integrador.etl.model.etl.Campo;

public class DateMaxChecker extends MaxChecker<ChronoLocalDate> {
	@Override
	protected LocalDate getValorLimite(Campo campo) {
		return campo.getValorFechaMax();
	}
}