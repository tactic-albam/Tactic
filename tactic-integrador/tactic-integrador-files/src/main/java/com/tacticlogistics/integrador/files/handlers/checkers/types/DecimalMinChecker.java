package com.tacticlogistics.integrador.files.handlers.checkers.types;

import java.math.BigDecimal;

import com.tacticlogistics.integrador.model.etl.tipoarchivo.Campo;

public class DecimalMinChecker extends MinChecker<BigDecimal> {
	@Override
	protected BigDecimal getValorLimite(Campo campo) {
		return campo.getValorDecimalMin();
	}
	
	@Override
	public String getMensajeDeError() {
		return "%s:El valor %f del campo %s debe ser mayor o igual que %s.";
	}
}
