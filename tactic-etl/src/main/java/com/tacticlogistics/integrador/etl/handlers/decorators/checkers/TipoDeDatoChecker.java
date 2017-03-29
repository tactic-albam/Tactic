package com.tacticlogistics.integrador.etl.handlers.decorators.checkers;

import org.springframework.util.StringUtils;

import com.tacticlogistics.integrador.etl.handlers.decorators.checkers.types.DateChecker;
import com.tacticlogistics.integrador.etl.handlers.decorators.checkers.types.DateTimeChecker;
import com.tacticlogistics.integrador.etl.handlers.decorators.checkers.types.DecimalChecker;
import com.tacticlogistics.integrador.etl.handlers.decorators.checkers.types.IntegerChecker;
import com.tacticlogistics.integrador.etl.handlers.decorators.checkers.types.TimeChecker;
import com.tacticlogistics.integrador.etl.model.etl.Campo;

public class TipoDeDatoChecker implements CampoChecker<String> {

	@Override
	public void check(Campo campo, String valor) {
		if (StringUtils.hasLength(valor)) {
			switch (campo.getTipoDato()) {
			case INTEGER:
				(new IntegerChecker()).check(campo, valor);
				break;
			case DECIMAL:
				(new DecimalChecker()).check(campo, valor);
				break;
			case DATETIME:
				(new DateTimeChecker()).check(campo, valor);
				break;
			case DATE:
				(new DateChecker()).check(campo, valor);
				break;
			case TIME:
				(new TimeChecker()).check(campo, valor);
				break;
			default:
				break;
			}
		}
	}

	@Override
	public String getMensajeDeError() {
		return "";
	}

}
