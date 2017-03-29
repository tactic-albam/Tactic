package com.tacticlogistics.integrador.etl.clientes.gws.salidas;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.util.Assert;

import com.tacticlogistics.integrador.etl.clientes.heinz.salidas.model.SalidaCadena;
import com.tacticlogistics.integrador.etl.handlers.decorators.Filter;
import com.tacticlogistics.integrador.etl.handlers.decorators.MapEntidadDecorator;
import com.tacticlogistics.integrador.etl.handlers.dto.ArchivoDTO;
import com.tacticlogistics.integrador.etl.handlers.dto.RegistroDTO;

import lombok.val;

public class MapEntidadSalidaDecorator extends MapEntidadDecorator<SalidaCadena> {

	public MapEntidadSalidaDecorator() {
		super();
	}

	public MapEntidadSalidaDecorator(Filter<SalidaCadena> inner) {
		super(inner);
	}

	@Override
	protected SalidaCadena map(ArchivoDTO<SalidaCadena> archivoDTO, RegistroDTO<SalidaCadena> registro) {
		val tipoArchivo = archivoDTO.getTipoArchivo();
		Assert.notNull(tipoArchivo);
		val archivo = archivoDTO.getArchivo();
		Assert.notNull(archivo);
		val datos = registro.getDatos();
		Assert.notEmpty(datos);

		Integer cantidad = getInteger(tipoArchivo, datos, SalidaCadena.CANTIDAD);
		BigDecimal valorDeclarado = getBigDecimal(tipoArchivo, datos, SalidaCadena.VALOR_DECLARADO);
		Integer cantidadPuntoVenta = getInteger(tipoArchivo, datos, SalidaCadena.CANTIDAD_PUNTO_VENTA);
		LocalDateTime femi = getLocalDateTime(tipoArchivo, datos, SalidaCadena.FEMI);
		LocalDateTime fema = getLocalDateTime(tipoArchivo, datos, SalidaCadena.FEMA);

		// @formatter:off
		val result = 
				SalidaCadena.builder()
				.idArchivo(archivo.getId())
				.numeroOrden(datos.get(SalidaCadena.NUMERO_ORDEN))
				.femi(femi)
				.fema(fema)
				.terceroIdentificacion(datos.get(SalidaCadena.TERCERO_IDENTIFICACION))
				.terceroNombre(datos.get(SalidaCadena.TERCERO_NOMBRE))
				.direccion(datos.get(SalidaCadena.DIRECCION))
				.puntoNombre(datos.get(SalidaCadena.PUNTO_NOMBRE))
				.productoCodigo(datos.get(SalidaCadena.PRODUCTO_CODIGO))
				.productoNombre(datos.get(SalidaCadena.PRODUCTO_NOMBRE))
				.productoEanTercero(datos.get(SalidaCadena.PRODUCTO_EAN_TERCERO))
				.productoPluTercero(datos.get(SalidaCadena.PRODUCTO_PLU_TERCERO))
				.cantidad(cantidad)
				.valorDeclarado(valorDeclarado)
				.cantidadPuntoVenta(cantidadPuntoVenta)
				.linea(registro.getLinea())
				.fechaCreacion(archivo.getFechaCreacion())
				.usuarioCreacion(archivo.getUsuarioCreacion())
				.build();
		// @formatter:on

		return result;
	}
}