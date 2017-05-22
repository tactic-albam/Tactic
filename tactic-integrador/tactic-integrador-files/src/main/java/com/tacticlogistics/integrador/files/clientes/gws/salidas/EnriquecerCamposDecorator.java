package com.tacticlogistics.integrador.files.clientes.gws.salidas;

import org.springframework.util.Assert;

import com.tacticlogistics.integrador.dto.ArchivoDTO;
import com.tacticlogistics.integrador.dto.RegistroDTO;
import com.tacticlogistics.integrador.files.handlers.decorators.Decorator;
import com.tacticlogistics.integrador.files.handlers.decorators.Filter;
import com.tacticlogistics.integrador.model.oms.Salida;

import lombok.val;

public class EnriquecerCamposDecorator extends Decorator<Salida> {

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

		for (val registro : registros) {
			val prefijoOrden = registro.getDatos().get(Salida.PREFIJO_ORDEN);
			val numeroOrdenSinPrefijo = registro.getDatos().get(Salida.NUMERO_ORDEN_SIN_PREFIJO);
			val numeroOrden = prefijoOrden + "-" + numeroOrdenSinPrefijo;
			val requiereRecaudo = (registro.getDatos().get(Salida.REQUIERE_RECAUDO).isEmpty() ? "N" : "S");

			val canalCodigo = registro.getDatos().get(Salida.CANAL_CODIGO);
			if ("CADENAS".equals(canalCodigo)) {
				registro.getDatos().put(Salida.BODEGA_DESTINO_CODIGO, "");
			}

			registro.getDatos().put(Salida.NUMERO_ORDEN, numeroOrden);
			registro.getDatos().put(Salida.REQUIERE_RECAUDO, requiereRecaudo);

			cambiarDatosCantidad(registro);
		}

		return result;
	}

	private void cambiarDatosCantidad(final RegistroDTO<Salida> registro) {
		String cantidad = registro.getDatos().get(Salida.CANTIDAD);
		final StringBuilder sb = new StringBuilder();

		for (int i = 0; i < cantidad.length(); i++) {
			char c = cantidad.charAt(i);
			if (Character.isDigit(c)) {
				sb.append(c);
			} else {
				break;
			}
		}

		registro.getDatos().put(Salida.CANTIDAD, sb.toString());
	}
}
