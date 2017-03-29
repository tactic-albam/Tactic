package com.tacticlogistics.integrador.etl.clientes.heinz.maestros;

import java.math.BigDecimal;

import org.springframework.util.Assert;

import com.tacticlogistics.integrador.etl.clientes.heinz.maestros.model.ProductoPrecio;
import com.tacticlogistics.integrador.etl.handlers.decorators.Filter;
import com.tacticlogistics.integrador.etl.handlers.decorators.MapEntidadDecorator;
import com.tacticlogistics.integrador.etl.handlers.dto.ArchivoDTO;
import com.tacticlogistics.integrador.etl.handlers.dto.RegistroDTO;

import lombok.val;

public class MapEntidadProductoPrecioDecorator extends MapEntidadDecorator<ProductoPrecio> {

	public MapEntidadProductoPrecioDecorator() {
		super();
	}

	public MapEntidadProductoPrecioDecorator(Filter<ProductoPrecio> inner) {
		super(inner);
	}

	@Override
	protected ProductoPrecio map(ArchivoDTO<ProductoPrecio> archivoDTO, RegistroDTO<ProductoPrecio> registro) {
		val tipoArchivo = archivoDTO.getTipoArchivo();
		Assert.notNull(tipoArchivo);
		val archivo = archivoDTO.getArchivo();
		Assert.notNull(archivo);
		val datos = registro.getDatos();
		Assert.notEmpty(datos);
		BigDecimal valorUnitario = getBigDecimal(tipoArchivo, datos, ProductoPrecio.VALOR_UNITARIO);
		Integer redondeo = getInteger(tipoArchivo, datos, ProductoPrecio.REDONDEO);

		// @formatter:off
		val result = 
				ProductoPrecio.builder()
				.idArchivo(archivo.getId())
				.clienteCodigo(datos.get(ProductoPrecio.CLIENTE_CODIGO))
				.terceroIdentificacion(datos.get(ProductoPrecio.TERCERO_IDENTIFICACION))
				.productoCodigo(datos.get(ProductoPrecio.PRODUCTO_CODIGO))
				.productoNombre(datos.get(ProductoPrecio.PRODUCTO_NOMBRE))
				.valorUnitario(valorUnitario)
				.redondeo(redondeo)
				.linea(registro.getLinea())
				.fechaCreacion(archivo.getFechaCreacion())
				.usuarioCreacion(archivo.getUsuarioCreacion())
				.build();
		// @formatter:on

		return result;
	}
}