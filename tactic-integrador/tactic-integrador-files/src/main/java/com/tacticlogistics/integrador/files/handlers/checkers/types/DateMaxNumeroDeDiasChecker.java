package com.tacticlogistics.integrador.files.handlers.checkers.types;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;

import com.tacticlogistics.integrador.model.etl.tipoarchivo.Campo;

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