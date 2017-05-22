
package com.tacticlogistics.integrador.files.handlers.checkers.types;

import lombok.val;

public abstract class MinChecker<T extends Comparable<T>> extends MinMaxChecker<T> {
	@Override
	protected boolean comparar(T valor, T valorLimite) {
		val result = (valor.compareTo(valorLimite) == -1);
		return result;
	}

	@Override
	public String getMensajeDeError() {
		return "%s:El valor %d del campo %s debe ser mayor o igual que %s.";
	}
}