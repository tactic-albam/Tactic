package com.tacticlogistics.integrador.files.handlers.checkers.types;

import java.time.LocalTime;

import com.tacticlogistics.integrador.model.etl.tipoarchivo.Campo;

public class TimeMinChecker extends MinChecker<LocalTime> {
	@Override
	protected LocalTime getValorLimite(Campo campo) {
		return campo.getValorHoraMin();
	}
}