package com.tacticlogistics.integrador.etl.decorators.checkers.types;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import com.tacticlogistics.integrador.etl.decorators.checkers.CampoChecker;
import com.tacticlogistics.integrador.etl.model.Campo;
import com.tacticlogistics.integrador.etl.model.TipoDatoType;

import lombok.val;

public class DateChecker  extends DataTypeChecker<ChronoLocalDate> {

	@Override
	protected ChronoLocalDate parse(Campo campo, String valor) {
		DateTimeFormatter formatter = campo.getDateTimeFormatter();
		try {
			val result = LocalDate.parse(valor, formatter);
			return result;
		} catch (DateTimeParseException e) {
			throw new IllegalArgumentException(e);
		}
	}

	@Override
	protected List<CampoChecker<ChronoLocalDate>> getCheckers() {
		val result = super.getCheckers();
		result.add(new DateMinChecker());
		result.add(new DateMaxChecker());
		result.add(new DateMinNumeroDeDiasChecker());
		result.add(new DateMaxNumeroDeDiasChecker());
		return result;
	}

	@Override
	protected String getEjemplosValidos(Campo campo) {
		DateTimeFormatter formatter = campo.getDateTimeFormatter();
		StringBuilder sb = new StringBuilder();
		sb.append(formatter.format(LocalDate.now()));
		return sb.toString();
	}

	@Override
	protected TipoDatoType getTipoDato() {
		return TipoDatoType.DATE;
	}
}