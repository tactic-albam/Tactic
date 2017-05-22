package com.tacticlogistics.integrador.files.handlers.checkers.types;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.List;

import com.tacticlogistics.integrador.files.handlers.checkers.CampoChecker;
import com.tacticlogistics.integrador.model.etl.tipoarchivo.Campo;
import com.tacticlogistics.integrador.model.etl.tipoarchivo.TipoDatoType;

import lombok.val;

public class DecimalChecker extends DataTypeChecker<BigDecimal> {
	@Override
	protected BigDecimal parse(Campo campo, String valor) {
		DecimalFormat formatter = campo.getDecimalFormat();
		try {
			val result = (BigDecimal) formatter.parse(valor);
			return result;
		} catch (ParseException e) {
			throw new IllegalArgumentException(e);
		}
	}

	@Override
	protected String getEjemplosValidos(Campo campo) {
		DecimalFormat formatter = campo.getDecimalFormat();

		StringBuilder sb = new StringBuilder();
		sb.append(formatter.format(-123.123456));
		sb.append(",");
		sb.append(formatter.format(123.123456));
		sb.append(",");
		sb.append(formatter.format(123));
		sb.append(",");
		sb.append(formatter.format(0.123456));

		return sb.toString();
	}

	@Override
	protected List<CampoChecker<BigDecimal>> getCheckers() {
		val result = super.getCheckers();
		result.add(new DecimalMinChecker());
		result.add(new DecimalMaxChecker());
		return result;
	}

	@Override
	protected TipoDatoType getTipoDato() {
		return TipoDatoType.DECIMAL;
	}
}