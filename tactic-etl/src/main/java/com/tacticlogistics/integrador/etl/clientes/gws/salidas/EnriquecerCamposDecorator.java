package com.tacticlogistics.integrador.etl.clientes.gws.salidas;

import org.springframework.util.Assert;

import com.tacticlogistics.integrador.etl.handlers.decorators.Decorator;
import com.tacticlogistics.integrador.etl.handlers.decorators.Filter;
import com.tacticlogistics.integrador.etl.handlers.dto.ArchivoDTO;
import com.tacticlogistics.integrador.etl.handlers.dto.RegistroDTO;
import com.tacticlogistics.integrador.etl.model.oms.Salida;

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
		//val clienteCodigo = ClienteCodigoType.GWS.toString();

		// val campoRequiereTransporte =
		// result.getTipoArchivo().getCampoPorCodigo(Salida.REQUIERE_TRANSPORTE).get();
		// val campoRequiereAgendamiento =
		// result.getTipoArchivo().getCampoPorCodigo(Salida.REQUIERE_AGENDAMIENTO).get();

		for (val registro : registros) {
			val prefijoOrden = registro.getDatos().get(Salida.PREFIJO_ORDEN);
			val numeroOrdenSinPrefijo = registro.getDatos().get(Salida.NUMERO_ORDEN_SIN_PREFIJO);
			val numeroOrden = prefijoOrden + "-" + numeroOrdenSinPrefijo;
			val requiereRecaudo = (registro.getDatos().get(Salida.REQUIERE_RECAUDO).isEmpty() ? "N" : "S");

			registro.getDatos().put(Salida.NUMERO_ORDEN, numeroOrden);
			registro.getDatos().put(Salida.REQUIERE_RECAUDO, requiereRecaudo);

			// registro.getDatos().put(Salida.CLIENTE_CODIGO, clienteCodigo);
			// registro.getDatos().put(Salida.TERCERO_SUCURSAL, "");
			// registro.getDatos().put(Salida.TERCERO_NOMBRE, "");
			// registro.getDatos().put(Salida.PUNTO_CODIGO, "");
			// registro.getDatos().put(Salida.PRODUCTO_CODIGO_ALTERNO, "");
			// registro.getDatos().put(Salida.VALOR_VENTA_LINEA, "");
			// registro.getDatos().put(Salida.LOTE, "");
			// registro.getDatos().put(Salida.REQUIERE_TRANSPORTE,
			// campoRequiereTransporte.getValorPredeterminado());
			// registro.getDatos().put(Salida.AUTORIZADO_IDENTIFICACION, "");
			// registro.getDatos().put(Salida.AUTORIZADO_NOMBRES, "");
			// registro.getDatos().put(Salida.REQUIERE_AGENDAMIENTO,
			// campoRequiereAgendamiento.getValorPredeterminado());
			// registro.getDatos().put(Salida.CONTACTO_NOMBRES, "");
			// registro.getDatos().put(Salida.CONTACTO_TELEFONOS, "");
			// registro.getDatos().put(Salida.CONTACTO_EMAIL, "");
			// registro.getDatos().put(Salida.FECHA_ORDEN, "");
			// registro.getDatos().put(Salida.FECHA_DOCUMENTO_CLIENTE, "");

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
