package com.tacticlogistics.integrador.files.clientes.tactic.oms.salidas;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

import com.tacticlogistics.integrador.dto.ArchivoDTO;
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
		Validate.notEmpty(result.getRegistros());

		val registros = result.getRegistros();
		Map<String, Boolean> crossdock = new HashMap<>();
		for (val registro : registros) {
			val clienteCodigo = StringUtils.defaultString(registro.getDatos().get(Salida.CLIENTE_CODIGO));
			val numeroOrden = StringUtils.defaultString(registro.getDatos().get(Salida.NUMERO_ORDEN));
			val puntoFinal = StringUtils.defaultString(registro.getDatos().get(Salida.PREDISTRIBUCION_CROSSDOCK));

			String key = clienteCodigo + "\t" + numeroOrden;
			if (!crossdock.containsKey(key)) {
				crossdock.put(key, false);
			}
			if (!StringUtils.isEmpty(puntoFinal)) {
				if (!crossdock.get(key)) {
					crossdock.put(key, true);
				}
			}
		}

		for (val registro : registros) {
			val clienteCodigo = StringUtils.defaultString(registro.getDatos().get(Salida.CLIENTE_CODIGO));
			val numeroOrden = StringUtils.defaultString(registro.getDatos().get(Salida.NUMERO_ORDEN));

			String key = clienteCodigo + "\t" + numeroOrden;
			String servicioCodigo = registro.getDatos().get(Salida.SERVICIO_CODIGO);

			if (StringUtils.isEmpty(servicioCodigo)) {
				if (crossdock.get(key)) {
					servicioCodigo = "XD";
				} else {
					servicioCodigo = "VENTAS";
				}
			}

			registro.getDatos().put(Salida.NUMERO_ORDEN_SIN_PREFIJO, numeroOrden);
			registro.getDatos().put(Salida.SERVICIO_CODIGO, servicioCodigo);
		}

		return result;
	}

}
