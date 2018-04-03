package com.tacticlogistics.integrador.files.clientes.tactic.oms.recibos;

import org.apache.commons.lang3.Validate;

import com.tacticlogistics.integrador.dto.ArchivoDTO;
import com.tacticlogistics.integrador.files.handlers.decorators.Decorator;
import com.tacticlogistics.integrador.files.handlers.decorators.Filter;
import com.tacticlogistics.integrador.model.oms.Recibo;
import com.tacticlogistics.integrador.model.oms.Salida;

import lombok.val;

public class EnriquecerCamposDecorator extends Decorator<Recibo> {

	public EnriquecerCamposDecorator() {
		super();
	}

	public EnriquecerCamposDecorator(Filter<Recibo> inner) {
		super(inner);
	}

	@Override
	public ArchivoDTO<Recibo> transformar(ArchivoDTO<Recibo> archivoDTO) {
		final val result = super.transformar(archivoDTO);
		Validate.notEmpty(result.getRegistros());

		val registros = result.getRegistros();

		for (val registro : registros) {
			val numeroOrden = registro.getDatos().get(Salida.NUMERO_ORDEN);
			registro.getDatos().put(Salida.NUMERO_ORDEN_SIN_PREFIJO, numeroOrden);
		}

		return result;
	}

}
