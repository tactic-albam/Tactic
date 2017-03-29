package com.tacticlogistics.integrador.etl.handlers.decorators.checkers.types;

import java.time.LocalTime;

import com.tacticlogistics.integrador.etl.model.etl.Campo;

public class TimeMaxChecker extends MaxChecker<LocalTime> {
	@Override
	protected LocalTime getValorLimite(Campo campo) {
		return campo.getValorHoraMax();
	}
}
