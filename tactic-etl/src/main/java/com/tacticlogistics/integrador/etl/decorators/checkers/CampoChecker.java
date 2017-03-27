package com.tacticlogistics.integrador.etl.decorators.checkers;

import com.tacticlogistics.integrador.etl.model.Campo;

public interface CampoChecker<T> {
	public void check(Campo campo, T valor);

	public String getMensajeDeError();
}
