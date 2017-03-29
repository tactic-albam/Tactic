package com.tacticlogistics.integrador.etl.handlers.decorators.checkers.types;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.tacticlogistics.integrador.etl.handlers.decorators.checkers.CampoChecker;
import com.tacticlogistics.integrador.etl.model.etl.Campo;
import com.tacticlogistics.integrador.etl.model.etl.TipoDatoType;

import lombok.val;

public abstract class DataTypeChecker<T> implements CampoChecker<String> {
	@Override
	public void check(Campo campo, String valor) {
		if (StringUtils.hasLength(valor)) {
			T valorTest = null;
			try {
				valorTest = parse(campo, valor);
			} catch (IllegalArgumentException e) {
				String mensaje = gen(campo, valor);
				throw new IllegalArgumentException(mensaje, e);
			}
			val checkers = getCheckers();
			for (CampoChecker<T> checker : checkers) {
				checker.check(campo, valorTest);
			}
		}
	}

	abstract protected T parse(Campo campo, String valor);

	protected String gen(Campo campo, String valor) {
		String ejemplos = getEjemplosValidos(campo);
		String mensaje = String.format(getMensajeDeError(), campo.getCodigo(), valor, campo.getNombre(), getTipoDato(),
				ejemplos);
		return mensaje;

	}

	@Override
	public String getMensajeDeError() {
		return "%s:El valor %s del campo %s no es un valor de tipo %s valido.";
	}

	abstract protected String getEjemplosValidos(Campo campo);

	abstract protected TipoDatoType getTipoDato();

	protected List<CampoChecker<T>> getCheckers() {
		List<CampoChecker<T>> result = new ArrayList<>();
		return result;
	}
}