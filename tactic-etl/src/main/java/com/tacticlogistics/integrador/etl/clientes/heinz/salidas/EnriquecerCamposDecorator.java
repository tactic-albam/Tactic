package com.tacticlogistics.integrador.etl.clientes.heinz.salidas;

import java.util.stream.Collectors;

import org.springframework.util.Assert;

import com.tacticlogistics.integrador.etl.handlers.decorators.Decorator;
import com.tacticlogistics.integrador.etl.handlers.decorators.Filter;
import com.tacticlogistics.integrador.etl.handlers.dto.ArchivoDTO;
import com.tacticlogistics.integrador.etl.model.etl.TipoArchivoConfiguracion;
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
		val registros = result.getRegistros();
		Assert.notEmpty(registros);

		val tipoArchivo = result.getTipoArchivo();
		Assert.notNull(tipoArchivo);

		// @formatter:off
		val configuraciones = tipoArchivo
				.getConfiguraciones()
				.stream()
				.filter(a -> a.getCodigo().endsWith(Salida.CANTIDAD))
				.collect(Collectors.toMap(TipoArchivoConfiguracion::getCodigo, TipoArchivoConfiguracion::getValor));
		// @formatter:on

		for (val registro : registros) {
			val terceroIdentificacion = registro.getDatos().get(Salida.TERCERO_IDENTIFICACION);
			val key = terceroIdentificacion + "_" + Salida.CANTIDAD;
			val campo = configuraciones.get(key);
			String cantidad = "";
			if(campo != null){
				cantidad = registro.getDatos().get(campo);
			}
			registro.getDatos().put(Salida.CANTIDAD, cantidad);
		}

		return result;
	}
}
