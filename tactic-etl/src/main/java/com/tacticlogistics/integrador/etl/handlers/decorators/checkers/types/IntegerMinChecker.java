package com.tacticlogistics.integrador.etl.handlers.decorators.checkers.types;

import com.tacticlogistics.integrador.etl.model.etl.Campo;

public class IntegerMinChecker extends MinChecker<Long> {
	@Override
	protected Long getValorLimite(Campo campo) {
		return campo.getValorEnteroMin();
	}
}
