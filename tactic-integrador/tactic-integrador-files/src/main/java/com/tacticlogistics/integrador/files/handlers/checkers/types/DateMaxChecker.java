package com.tacticlogistics.integrador.files.handlers.checkers.types;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;

import com.tacticlogistics.integrador.model.etl.tipoarchivo.Campo;

public class DateMaxChecker extends MaxChecker<ChronoLocalDate> {
	@Override
	protected LocalDate getValorLimite(Campo campo) {
		return campo.getValorFechaMax();
	}
}
