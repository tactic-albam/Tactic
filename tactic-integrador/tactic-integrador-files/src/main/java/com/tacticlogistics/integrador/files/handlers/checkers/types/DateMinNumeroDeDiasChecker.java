package com.tacticlogistics.integrador.files.handlers.checkers.types;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;

import com.tacticlogistics.integrador.model.etl.tipoarchivo.Campo;

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