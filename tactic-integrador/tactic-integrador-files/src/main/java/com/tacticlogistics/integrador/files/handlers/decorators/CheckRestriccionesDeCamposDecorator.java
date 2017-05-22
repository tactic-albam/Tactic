package com.tacticlogistics.integrador.files.handlers.decorators;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.util.Assert;

import com.tacticlogistics.integrador.dto.ArchivoDTO;
import com.tacticlogistics.integrador.files.handlers.checkers.ExpresionRegularChecker;
import com.tacticlogistics.integrador.files.handlers.checkers.NumeroMaximoDeCaracteresChecker;
import com.tacticlogistics.integrador.files.handlers.checkers.TipoDeDatoChecker;
import com.tacticlogistics.integrador.files.handlers.checkers.ValorObligatorioChecker;
import com.tacticlogistics.integrador.files.handlers.checkers.ValoresPermitidosChecker;
import com.tacticlogistics.integrador.model.etl.tipoarchivo.Campo;

import lombok.val;

public class CheckRestriccionesDeCamposDecorator<T> extends Decorator<T> {

	public CheckRestriccionesDeCamposDecorator() {
		super();
	}

	public CheckRestriccionesDeCamposDecorator(Filter<T> inner) {
		super(inner);
	}

	@Override
	public ArchivoDTO<T> transformar(ArchivoDTO<T> archivoDTO) {
		final val result = super.transformar(archivoDTO);
		val tipoArchivo = result.getTipoArchivo();
		Assert.notNull(tipoArchivo);
		val registros = result.getRegistros();
		Assert.notEmpty(result.getRegistros());
		val campos = tipoArchivo.getCamposNoIgnorados();
		Assert.notEmpty(campos);

		boolean error = false;
		for (val registro : registros) {
			val errores = checkRestricciones(registro.getDatos(), campos);
			if (!errores.isEmpty()) {
				error = true;
				registro.getErrores().addAll(errores);
			}
		}

		if (error) {
			ETLRuntimeException.throwException("Se detectaron errores en el archivo", result);
		}

		return result;
	}

	private List<String> checkRestricciones(Map<String, String> registro, List<Campo> campos) {

		List<String> result = new ArrayList<>();
		for (Campo campo : campos) {
			if (campo.isIgnorar()) {
				continue;
			}
			String valor = registro.get(campo.getCodigo());

			try {
				(new NumeroMaximoDeCaracteresChecker()).check(campo, valor);
			} catch (RuntimeException e) {
				result.add(e.getMessage());
			}

			try {
				(new TipoDeDatoChecker()).check(campo, valor);
			} catch (RuntimeException e) {
				result.add(e.getMessage());
			}

			try {
				(new ValorObligatorioChecker()).check(campo, valor);
			} catch (RuntimeException e) {
				result.add(e.getMessage());
			}

			try {
				(new ExpresionRegularChecker()).check(campo, valor);
			} catch (RuntimeException e) {
				result.add(e.getMessage());
			}

			try {
				(new ValoresPermitidosChecker()).check(campo, valor);
			} catch (RuntimeException e) {
				result.add(e.getMessage());
			}
		}

		return result;
	}
}
