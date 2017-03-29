package com.tacticlogistics.integrador.etl.handlers.decorators.checkers;

import com.tacticlogistics.integrador.etl.model.etl.Campo;

public interface CampoChecker<T> {
	public void check(Campo campo, T valor);

	public String getMensajeDeError();
}
