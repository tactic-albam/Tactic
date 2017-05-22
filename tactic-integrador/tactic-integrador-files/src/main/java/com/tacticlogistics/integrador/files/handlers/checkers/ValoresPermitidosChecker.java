package com.tacticlogistics.integrador.files.handlers.checkers;

import org.springframework.util.StringUtils;

import com.tacticlogistics.integrador.model.etl.tipoarchivo.Campo;

import lombok.val;

public class ValoresPermitidosChecker implements CampoChecker<String> {
	@Override
	public void check(Campo campo, String valor) {
		if (StringUtils.hasLength(valor)) {
			val valoresPermitidos = campo.valoresPermitidos();
			if (valoresPermitidos != null) {
				if (!valoresPermitidos.contains(valor)) {
					// @formatter:off
					String message = String.format(
							getMensajeDeError(),
							campo.getCodigo(), 
							valor, 
							campo.getNombre(), 
							campo.getValoresPermitidos());
					// @formatter:on
					throw new RuntimeException(message);
				}
			}
		}
	}

	@Override
	public String getMensajeDeError() {
		return "%s:El valor %s del campo %s no se encuentra entre los valores permitidos, los cuales son:%s.";
	}
}
