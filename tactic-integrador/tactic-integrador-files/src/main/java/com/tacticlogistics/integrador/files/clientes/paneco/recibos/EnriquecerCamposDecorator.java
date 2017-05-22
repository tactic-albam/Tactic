package com.tacticlogistics.integrador.files.clientes.paneco.recibos;

import java.time.LocalDate;

import org.springframework.util.Assert;

import com.tacticlogistics.integrador.dto.ArchivoDTO;
import com.tacticlogistics.integrador.files.handlers.decorators.Decorator;
import com.tacticlogistics.integrador.files.handlers.decorators.Filter;
import com.tacticlogistics.integrador.model.oms.Recibo;

import lombok.val;

public class EnriquecerCamposDecorator extends Decorator<Recibo> {

	private static final String CLIENTE_TRAE = "CLIENTE_TRAE";

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
		val campoFecha = result.getTipoArchivo().getCampoPorCodigo(Recibo.FEMA).get();
		val fecha = campoFecha.getDateTimeFormatter().format(LocalDate.now());

		for (val registro : registros) {
			val numeroOrden = registro.getDatos().get(Recibo.NUMERO_ORDEN);
			val prefijoOrden = "";
			val numeroOrdenSinPrefijo = numeroOrden;
			val clienteTrae = registro.getDatos().get(CLIENTE_TRAE);
			val requiereTransporte = ("N".equalsIgnoreCase(clienteTrae)) ? "S" : "N";

			registro.getDatos().put(Recibo.FEMI, fecha);
			registro.getDatos().put(Recibo.FEMA, fecha);
			registro.getDatos().put(Recibo.PREFIJO_ORDEN, prefijoOrden);
			registro.getDatos().put(Recibo.NUMERO_ORDEN_SIN_PREFIJO, numeroOrdenSinPrefijo);

			registro.getDatos().put(Recibo.REQUIERE_TRANSPORTE, requiereTransporte);
		}

		return result;
	}
}