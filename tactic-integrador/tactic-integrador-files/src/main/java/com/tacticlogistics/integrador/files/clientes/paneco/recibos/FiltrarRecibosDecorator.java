package com.tacticlogistics.integrador.files.clientes.paneco.recibos;

import java.util.stream.Collectors;

import org.springframework.util.Assert;

import com.tacticlogistics.integrador.dto.ArchivoDTO;
import com.tacticlogistics.integrador.files.handlers.decorators.Decorator;
import com.tacticlogistics.integrador.files.handlers.decorators.Filter;
import com.tacticlogistics.integrador.model.oms.Recibo;

import lombok.val;

public class FiltrarRecibosDecorator extends Decorator<Recibo> {

	public FiltrarRecibosDecorator() {
		super();
	}

	public FiltrarRecibosDecorator(Filter<Recibo> inner) {
		super(inner);
	}

	@Override
	public ArchivoDTO<Recibo> transformar(ArchivoDTO<Recibo> archivoDTO) {
		final val result = super.transformar(archivoDTO);
		Assert.notEmpty(result.getRegistros());

		// @formatter:off
		val registros = result
				.getRegistros()
				.stream()
				.filter(a -> !a.getDatos().get(Recibo.PRODUCTO_CODIGO).isEmpty())
				.collect(Collectors.toList());

		result.setRegistros(registros);
		// @formatter:on

		return result;
	}
}
