package com.tacticlogistics.integrador.files.handlers.checkers.types;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;

import com.tacticlogistics.integrador.model.etl.tipoarchivo.Campo;

public class DateMinChecker extends MinChecker<ChronoLocalDate> {
	@Override
	protected LocalDate getValorLimite(Campo campo) {
		return campo.getValorFechaMin();
	}
}