package com.tacticlogistics.integrador.files.handlers.checkers.types;

import java.math.BigDecimal;

import com.tacticlogistics.integrador.model.etl.tipoarchivo.Campo;

public class DecimalMinChecker extends MinChecker<BigDecimal> {
	@Override
	protected BigDecimal getValorLimite(Campo campo) {
		return campo.getValorDecimalMin();
	}
}
