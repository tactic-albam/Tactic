package com.tacticlogistics.integrador.etl.clientes.dicermex;

import org.springframework.util.Assert;

import com.tacticlogistics.integrador.etl.clientes.dicermex.model.DicermexLineaSalida;
import com.tacticlogistics.integrador.etl.handlers.decorators.Filter;
import com.tacticlogistics.integrador.etl.handlers.decorators.MapEntidadDecorator;
import com.tacticlogistics.integrador.etl.handlers.dto.ArchivoDTO;
import com.tacticlogistics.integrador.etl.handlers.dto.RegistroDTO;

import lombok.val;

public class MapEntidadLineaSalidaDecorator extends MapEntidadDecorator<DicermexLineaSalida> {

	public MapEntidadLineaSalidaDecorator() {
		super();
	}

	public MapEntidadLineaSalidaDecorator(Filter<DicermexLineaSalida> inner) {
		super(inner);
	}

	@Override
	protected DicermexLineaSalida map(ArchivoDTO<DicermexLineaSalida> archivoDTO, RegistroDTO<DicermexLineaSalida> registro) {
		val tipoArchivo = archivoDTO.getTipoArchivo();
		Assert.notNull(tipoArchivo);
		val archivo = archivoDTO.getArchivo();
		Assert.notNull(archivo);
		val datos = registro.getDatos();
		Assert.notEmpty(datos);

		Integer cantidad = getInteger(tipoArchivo, datos, DicermexLineaSalida.CANTIDAD);

		// @formatter:off
		val result = 
				DicermexLineaSalida.builder()
				.idArchivo(archivo.getId())
				.linea(registro.getLinea())
				.fechaCreacion(archivo.getFechaCreacion())
				.usuarioCreacion(archivo.getUsuarioCreacion())
				.prefijoOrden(datos.get(DicermexLineaSalida.PREFIJO_ORDEN))
				.numeroOrden(datos.get(DicermexLineaSalida.NUMERO_ORDEN))
				.productoCodigo(datos.get(DicermexLineaSalida.PRODUCTO_CODIGO))
				.cantidad(cantidad)
				.unidadCodigo(datos.get(DicermexLineaSalida.UNIDAD_CODIGO))
				.bodegaOrigenCodigo(datos.get(DicermexLineaSalida.BODEGA_ORIGEN_CODIGO))
				.bodegaDestinoCodigo(datos.get(DicermexLineaSalida.BODEGA_DESTINO_CODIGO))
				.build();
		// @formatter:on

		return result;
	}
}