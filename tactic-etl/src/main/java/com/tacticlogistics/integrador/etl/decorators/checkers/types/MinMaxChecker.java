package com.tacticlogistics.integrador.etl.decorators.checkers.types;

import com.tacticlogistics.integrador.etl.decorators.checkers.CampoChecker;
import com.tacticlogistics.integrador.etl.model.Campo;

public abstract class MinMaxChecker<T extends Comparable<T>> implements CampoChecker<T> {
	@Override
	public void check(Campo campo, T valor) {
		T valorLimite = getValorLimite(campo);
		if (valorLimite != null) {
			if (comparar(valor, valorLimite)) {
				String mensaje = String.format(getMensajeDeError(), campo.getCodigo(), valor, campo.getNombre(),
						valorLimite);
				throw new IllegalArgumentException(mensaje);
			}
		}
	}

	abstract protected T getValorLimite(Campo campo);

	abstract protected boolean comparar(T valor, T valorLimite);
}