package com.tacticlogistics.integrador.etl.decorators.checkers.types;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;

import com.tacticlogistics.integrador.etl.model.Campo;

public class DateMinChecker extends MinChecker<ChronoLocalDate> {
	@Override
	protected LocalDate getValorLimite(Campo campo) {
		return campo.getValorFechaMin();
	}
}