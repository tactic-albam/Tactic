package com.tacticlogistics.integrador.files.handlers.checkers.types;

import com.tacticlogistics.integrador.model.etl.tipoarchivo.Campo;

public class IntegerMinChecker extends MinChecker<Long> {
	@Override
	protected Long getValorLimite(Campo campo) {
		return campo.getValorEnteroMin();
	}
}
