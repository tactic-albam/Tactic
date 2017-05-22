package com.tacticlogistics.integrador.files.handlers.checkers.types;

import java.time.LocalTime;

import com.tacticlogistics.integrador.model.etl.tipoarchivo.Campo;

import lombok.val;

public class TimeMaxNumeroDeDiasChecker extends MaxChecker<LocalTime> {
	@Override
	protected LocalTime getValorLimite(Campo campo) {
		LocalTime result = null;
		val minutos = campo.getValorEnteroMax();
		if (minutos != null) {
			LocalTime.now().plusMinutes(minutos);
		}
		return result;
	}
}