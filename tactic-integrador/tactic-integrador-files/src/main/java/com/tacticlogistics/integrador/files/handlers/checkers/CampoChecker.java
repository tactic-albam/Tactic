package com.tacticlogistics.integrador.files.handlers.checkers;

import com.tacticlogistics.integrador.model.etl.tipoarchivo.Campo;

public interface CampoChecker<T> {
	public void check(Campo campo, T valor);

	public String getMensajeDeError();
}
