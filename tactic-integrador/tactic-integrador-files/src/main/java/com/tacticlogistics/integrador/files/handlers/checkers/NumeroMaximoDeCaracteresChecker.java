package com.tacticlogistics.integrador.files.handlers.checkers;

import org.springframework.util.StringUtils;

import com.tacticlogistics.integrador.model.etl.tipoarchivo.Campo;

public class NumeroMaximoDeCaracteresChecker implements CampoChecker<String> {
	@Override
	public void check(Campo campo, String valor) {
		if (StringUtils.hasLength(valor)) {
			if (valor.length() > campo.getNumeroCaracteres()) {
			// @formatter:off
			String message = String.format(
					getMensajeDeError(),
					campo.getCodigo(), 
					valor, 
					campo.getNombre(), 
					valor.length(), 
					campo.getNumeroCaracteres());
			// @formatter:on
				throw new RuntimeException(message);
			}
		}
	}

	@Override
	public String getMensajeDeError() {
		return "%s:El valor %s del campo %s contiene %d caracteres, el maxímo numero de caracteres es %d.";
	}
}