
package com.tacticlogistics.integrador.etl.handlers.decorators.checkers.types;

import lombok.val;

public abstract class MaxChecker<T extends Comparable<T>> extends MinMaxChecker<T> {
	@Override
	protected boolean comparar(T valor, T valorLimite) {
		val result = (valor.compareTo(valorLimite) == 1);
		return result;
	}

	@Override
	public String getMensajeDeError() {
		return "%s:El valor %d del campo %s debe ser menor o igual que %s.";
	}
}