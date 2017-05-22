package com.tacticlogistics.integrador.files.handlers.checkers;

import org.springframework.util.StringUtils;

import com.tacticlogistics.integrador.files.handlers.checkers.types.DateChecker;
import com.tacticlogistics.integrador.files.handlers.checkers.types.DateTimeChecker;
import com.tacticlogistics.integrador.files.handlers.checkers.types.DecimalChecker;
import com.tacticlogistics.integrador.files.handlers.checkers.types.IntegerChecker;
import com.tacticlogistics.integrador.files.handlers.checkers.types.TimeChecker;
import com.tacticlogistics.integrador.model.etl.tipoarchivo.Campo;

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
