package com.tacticlogistics.integrador.files.clientes.tactic.oms.manufacturas;

import java.time.LocalDate;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import com.tacticlogistics.integrador.dto.ArchivoDTO;
import com.tacticlogistics.integrador.dto.RegistroDTO;
import com.tacticlogistics.integrador.files.handlers.decorators.Filter;
import com.tacticlogistics.integrador.files.handlers.decorators.MapEntidadDecorator;
import com.tacticlogistics.integrador.model.etl.registro.EstadoRegistroType;
import com.tacticlogistics.integrador.model.oms.Manufactura;

import lombok.val;

public class MapEntidadManufacturaDecorator extends MapEntidadDecorator<Manufactura> {

	public MapEntidadManufacturaDecorator() {
		super();
	}

	public MapEntidadManufacturaDecorator(Filter<Manufactura> inner) {
		super(inner);
	}

	@Override
	protected Manufactura map(ArchivoDTO<Manufactura> archivoDTO, RegistroDTO<Manufactura> registro) {
		val tipoArchivo = archivoDTO.getTipoArchivo();
		Assert.notNull(tipoArchivo);
		val archivo = archivoDTO.getArchivo();
		Assert.notNull(archivo);
		val datos = registro.getDatos();
		Assert.notEmpty(datos);

		LocalDate femi = getLocalDate(tipoArchivo, datos, Manufactura.FEMI);
		LocalDate fema = getLocalDate(tipoArchivo, datos, Manufactura.FEMA);
		Integer cantidad = getInteger(tipoArchivo, datos, Manufactura.CANTIDAD);

		// @formatter:off
 		val result = Manufactura.builder()
				.idArchivo(archivo.getId())
				.estadoRegistro(EstadoRegistroType.ESTRUCTURA_VALIDADA)
				.linea(registro.getLinea())
				.numeroLinea(registro.getNumeroLinea())
				.fechaCreacion(archivo.getFechaCreacion())
				.usuarioCreacion(archivo.getUsuarioCreacion())
				.fechaActualizacion(archivo.getFechaCreacion())
				.usuarioActualizacion(archivo.getUsuarioCreacion())
				
				.clienteCodigo(StringUtils.defaultString(datos.get(Manufactura.CLIENTE_CODIGO)))
				.servicioCodigo(StringUtils.defaultString(datos.get(Manufactura.SERVICIO_CODIGO)))
				.numeroOrden(StringUtils.defaultString(datos.get(Manufactura.NUMERO_ORDEN)))
				.femi(femi)
				.fema(fema)
				.productoCodigo(StringUtils.defaultString(datos.get(Manufactura.PRODUCTO_CODIGO)))
				.productoNombre(StringUtils.defaultString(datos.get(Manufactura.PRODUCTO_NOMBRE)))
				.unidadCodigo(StringUtils.defaultString(datos.get(Manufactura.UNIDAD_CODIGO)))
				.cantidad(cantidad)
				.bodegaOrigenCodigo(StringUtils.defaultString(datos.get(Manufactura.BODEGA_ORIGEN_CODIGO)))
				.estadoInventarioOrigenCodigo(StringUtils.defaultString(datos.get(Manufactura.ESTADO_INVENTARIO_ORIGEN_CODIGO)))
				.bodegaDestinoCodigo(StringUtils.defaultString(datos.get(Manufactura.BODEGA_DESTINO_CODIGO)))
				.estadoInventarioDestinoCodigo(StringUtils.defaultString(datos.get(Manufactura.ESTADO_INVENTARIO_DESTINO_CODIGO)))
				.lote(StringUtils.defaultString(datos.get(Manufactura.LOTE)))
				.notas(StringUtils.defaultString(datos.get(Manufactura.NOTAS)))
				.documentoCliente(StringUtils.defaultString(datos.get(Manufactura.DOCUMENTO_CLIENTE)))
				.build();
		// @formatter:on

		return result;
	}
}