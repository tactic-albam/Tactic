package com.tacticlogistics.integrador.etl.handlers.decorators.checkers;

import org.springframework.util.StringUtils;

import com.tacticlogistics.integrador.etl.model.etl.Campo;

public class ValorObligatorioChecker implements CampoChecker<String> {

	@Override
	public void check(Campo campo, String valor) {
		if (!StringUtils.hasLength(valor) && campo.isObligatorio()) {
			// @formatter:off
			String message = String.format(
					getMensajeDeError(),
					campo.getCodigo(), 
					campo.getNombre());
			// @formatter:on
			throw new RuntimeException(message);
		}
	}

	@Override
	public String getMensajeDeError() {
		return "%s:No se suministró un valor en el campo %s. Este campo es obligatorio y siempre debe ser diligenciado.";
	}
}
