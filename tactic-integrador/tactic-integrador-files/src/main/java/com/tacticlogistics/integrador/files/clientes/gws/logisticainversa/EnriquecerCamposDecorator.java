package com.tacticlogistics.integrador.files.clientes.gws.logisticainversa;

import org.springframework.util.Assert;

import com.tacticlogistics.ClienteCodigoType;
import com.tacticlogistics.integrador.dto.ArchivoDTO;
import com.tacticlogistics.integrador.files.handlers.decorators.Decorator;
import com.tacticlogistics.integrador.files.handlers.decorators.Filter;
import com.tacticlogistics.integrador.model.oms.Recibo;

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
		Assert.notEmpty(result.getRegistros());

		val registros = result.getRegistros();
		val clienteCodigo = ClienteCodigoType.GWS.toString();

		for (val registro : registros) {
			val numeroOrden = registro.getDatos().get(Recibo.NUMERO_ORDEN);
			val prefijoOrden = "";
			val numeroOrdenSinPrefijo = numeroOrden;

			registro.getDatos().put(Recibo.PREFIJO_ORDEN, prefijoOrden);
			registro.getDatos().put(Recibo.NUMERO_ORDEN_SIN_PREFIJO, numeroOrdenSinPrefijo);

			registro.getDatos().put(Recibo.CLIENTE_CODIGO, clienteCodigo);
		}

		return result;
	}
}
