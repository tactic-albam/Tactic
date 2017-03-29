package com.tacticlogistics.integrador.etl.handlers.decorators.checkers.types;

import java.time.LocalTime;

import com.tacticlogistics.integrador.etl.model.etl.Campo;

import lombok.val;

public class TimeMinNumeroDeDiasChecker extends MinChecker<LocalTime> {
	@Override
	protected LocalTime getValorLimite(Campo campo) {
		LocalTime result = null;
		val minutos = campo.getValorEnteroMin();
		if (minutos != null) {
			LocalTime.now().plusMinutes(minutos);
		}
		return result;
	}
}