package com.tacticlogistics.integrador.etl.decorators.checkers;

import org.springframework.util.StringUtils;

import com.tacticlogistics.integrador.etl.model.Campo;

public class ExpresionRegularChecker implements CampoChecker<String> {

	@Override
	public void check(Campo campo, String valor) {
		if (StringUtils.hasLength(valor)) {
			if (campo.getPattern() != null) {
				if (!campo.getPattern().matcher(valor).matches()) {
					// @formatter:off
					String message = String.format(
							getMensajeDeError(),
							campo.getCodigo(), 
							valor, 
							campo.getNombre(), 
							campo.getExpresionRegular());
					// @formatter:on
					throw new RuntimeException(message);
				}
			}
		}
	}

	@Override
	public String getMensajeDeError() {
		return "%s:El valor %s del campo %s no cumple con el patron %s.";
	}
}