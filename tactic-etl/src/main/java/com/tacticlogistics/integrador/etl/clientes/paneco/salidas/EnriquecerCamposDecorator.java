package com.tacticlogistics.integrador.etl.clientes.paneco.salidas;

import org.springframework.util.Assert;

import com.tacticlogistics.ClienteCodigoType;
import com.tacticlogistics.integrador.etl.handlers.decorators.Decorator;
import com.tacticlogistics.integrador.etl.handlers.decorators.Filter;
import com.tacticlogistics.integrador.etl.handlers.dto.ArchivoDTO;
import com.tacticlogistics.integrador.etl.model.oms.Recibo;
import com.tacticlogistics.integrador.etl.model.oms.Salida;

import lombok.val;

public class EnriquecerCamposDecorator extends Decorator<Salida> {

	private static final String CLIENTE_RECOGE = "CLIENTE_RECOGE";

	public EnriquecerCamposDecorator() {
		super();
	}

	public EnriquecerCamposDecorator(Filter<Salida> inner) {
		super(inner);
	}

	@Override
	public ArchivoDTO<Salida> transformar(ArchivoDTO<Salida> archivoDTO) {
		final val result = super.transformar(archivoDTO);
		Assert.notEmpty(result.getRegistros());

		val registros = result.getRegistros();
		val clienteCodigo = ClienteCodigoType.PANECO.toString();

		for (val registro : registros) {
			val prefijoOrden = registro.getDatos().get(Salida.PREFIJO_ORDEN);
			val numeroOrdenSinPrefijo = registro.getDatos().get(Salida.NUMERO_ORDEN_SIN_PREFIJO);
			val numeroOrden = prefijoOrden + "-" + numeroOrdenSinPrefijo;
			val clienteRecoge = registro.getDatos().get(CLIENTE_RECOGE);
			val requiereTransporte = ("N".equalsIgnoreCase(clienteRecoge)) ? "S" : "N";
			
			val femi = registro.getDatos().get(Salida.FEMA);

			registro.getDatos().put(Salida.FEMI, femi);
			registro.getDatos().put(Salida.NUMERO_ORDEN, numeroOrden);

			registro.getDatos().put(Salida.CLIENTE_CODIGO, clienteCodigo);
			registro.getDatos().put(Recibo.REQUIERE_TRANSPORTE, requiereTransporte);
		}

		return result;
	}
}