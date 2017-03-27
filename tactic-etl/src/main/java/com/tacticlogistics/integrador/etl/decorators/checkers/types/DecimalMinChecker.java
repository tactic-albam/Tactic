package com.tacticlogistics.integrador.etl.decorators.checkers.types;

import java.math.BigDecimal;

import com.tacticlogistics.integrador.etl.model.Campo;

public class DecimalMinChecker extends MinChecker<BigDecimal> {
	@Override
	protected BigDecimal getValorLimite(Campo campo) {
		return campo.getValorDecimalMin();
	}
}
