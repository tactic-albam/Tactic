package com.tacticlogistics.integrador.etl.decorators.checkers.types;

import java.time.LocalTime;

import com.tacticlogistics.integrador.etl.model.Campo;

public class TimeMinChecker extends MinChecker<LocalTime> {
	@Override
	protected LocalTime getValorLimite(Campo campo) {
		return campo.getValorHoraMin();
	}
}