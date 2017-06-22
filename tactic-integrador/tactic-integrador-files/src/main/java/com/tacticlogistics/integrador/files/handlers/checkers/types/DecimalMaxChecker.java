package com.tacticlogistics.integrador.files.handlers.checkers.types;

import java.math.BigDecimal;

import com.tacticlogistics.integrador.model.etl.tipoarchivo.Campo;

public class DecimalMaxChecker extends MaxChecker<BigDecimal> {
	@Override
	protected BigDecimal getValorLimite(Campo campo) {
		return campo.getValorDecimalMax();
	}
	
	@Override
	public String getMensajeDeError() {
		return "%s:El valor %f del campo %s debe ser menor o igual que %s.";
	}
}
