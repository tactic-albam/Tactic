package com.tacticlogistics.integrador.etl.decorators.checkers.types;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import com.tacticlogistics.integrador.etl.decorators.checkers.CampoChecker;
import com.tacticlogistics.integrador.etl.model.Campo;
import com.tacticlogistics.integrador.etl.model.TipoDatoType;

import lombok.val;

public class TimeChecker extends DataTypeChecker<LocalTime> {
	@Override
	protected LocalTime parse(Campo campo, String valor) {
		DateTimeFormatter formatter = campo.getDateTimeFormatter();
		try {
			val result = LocalTime.parse(valor, formatter);
			return result;
		} catch (DateTimeParseException e) {
			throw new IllegalArgumentException(e);
		}
	}

	@Override
	protected List<CampoChecker<LocalTime>> getCheckers() {
		val result = super.getCheckers();
		result.add(new TimeMinChecker());
		result.add(new TimeMaxChecker());
		result.add(new TimeMinNumeroDeDiasChecker());
		result.add(new TimeMaxNumeroDeDiasChecker());
		return result;
	}

	@Override
	protected String getEjemplosValidos(Campo campo) {
		DateTimeFormatter formatter = campo.getDateTimeFormatter();
		StringBuilder sb = new StringBuilder();
		sb.append(formatter.format(LocalTime.now()));
		return sb.toString();
	}

	@Override
	protected TipoDatoType getTipoDato() {
		return TipoDatoType.TIME;
	}
}