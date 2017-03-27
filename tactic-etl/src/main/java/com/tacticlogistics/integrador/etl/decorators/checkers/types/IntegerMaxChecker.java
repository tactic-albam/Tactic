package com.tacticlogistics.integrador.etl.decorators.checkers.types;

import com.tacticlogistics.integrador.etl.model.Campo;

public class IntegerMaxChecker extends MaxChecker<Long> {
	@Override
	protected Long getValorLimite(Campo campo) {
		return campo.getValorEnteroMax();
	}
}
