package com.tacticlogistics.integrador.etl.handlers.dicermex;

import org.springframework.util.Assert;

import com.tacticlogistics.integrador.etl.decorators.Filter;
import com.tacticlogistics.integrador.etl.decorators.MapEntidadDecorator;
import com.tacticlogistics.integrador.etl.dto.ArchivoDTO;
import com.tacticlogistics.integrador.etl.dto.RegistroDTO;
import com.tacticlogistics.integrador.etl.handlers.dicermex.model.LineaSalida;

import lombok.val;

public class MapEntidadLineaSalidaDecorator extends MapEntidadDecorator<LineaSalida> {

	public MapEntidadLineaSalidaDecorator() {
		super();
	}

	public MapEntidadLineaSalidaDecorator(Filter<LineaSalida> inner) {
		super(inner);
	}

	@Override
	protected LineaSalida map(ArchivoDTO<LineaSalida> archivoDTO, RegistroDTO<LineaSalida> registro) {
		val tipoArchivo = archivoDTO.getTipoArchivo();
		Assert.notNull(tipoArchivo);
		val archivo = archivoDTO.getArchivo();
		Assert.notNull(archivo);
		val datos = registro.getDatos();
		Assert.notEmpty(datos);

		Integer cantidad = getInteger(tipoArchivo, datos, LineaSalida.CANTIDAD);

		// @formatter:off
		val result = 
				LineaSalida.builder()
				.idArchivo(archivo.getId())
				.linea(registro.getLinea())
				.fechaCreacion(archivo.getFechaCreacion())
				.usuarioCreacion(archivo.getUsuarioCreacion())
				.prefijoOrden(datos.get(LineaSalida.PREFIJO_ORDEN))
				.numeroOrden(datos.get(LineaSalida.NUMERO_ORDEN))
				.productoCodigo(datos.get(LineaSalida.PRODUCTO_CODIGO))
				.cantidad(cantidad)
				.unidadCodigo(datos.get(LineaSalida.UNIDAD_CODIGO))
				.bodegaOrigenCodigo(datos.get(LineaSalida.BODEGA_ORIGEN_CODIGO))
				.bodegaDestinoCodigo(datos.get(LineaSalida.BODEGA_DESTINO_CODIGO))
				.build();
		// @formatter:on

		return result;
	}
}