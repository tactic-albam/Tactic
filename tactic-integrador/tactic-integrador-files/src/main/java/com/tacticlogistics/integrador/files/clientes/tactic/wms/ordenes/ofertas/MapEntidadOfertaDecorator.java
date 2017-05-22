package com.tacticlogistics.integrador.files.clientes.tactic.wms.ordenes.ofertas;

import java.time.LocalDate;

import org.springframework.util.Assert;

import com.tacticlogistics.integrador.dto.ArchivoDTO;
import com.tacticlogistics.integrador.dto.RegistroDTO;
import com.tacticlogistics.integrador.files.handlers.decorators.Filter;
import com.tacticlogistics.integrador.files.handlers.decorators.MapEntidadDecorator;
import com.tacticlogistics.integrador.model.etl.registro.EstadoRegistroType;
import com.tacticlogistics.integrador.model.oms.Oferta;

import lombok.val;

public class MapEntidadOfertaDecorator extends MapEntidadDecorator<Oferta> {

	public MapEntidadOfertaDecorator() {
		super();
	}

	public MapEntidadOfertaDecorator(Filter<Oferta> inner) {
		super(inner);
	}

	@Override
	protected Oferta map(ArchivoDTO<Oferta> archivoDTO, RegistroDTO<Oferta> registro) {
		val tipoArchivo = archivoDTO.getTipoArchivo();
		Assert.notNull(tipoArchivo);
		val archivo = archivoDTO.getArchivo();
		Assert.notNull(archivo);
		val datos = registro.getDatos();
		Assert.notEmpty(datos);

		LocalDate femi = getLocalDate(tipoArchivo, datos, Oferta.FEMI);
		LocalDate fema = getLocalDate(tipoArchivo, datos, Oferta.FEMA);
		Integer cantidad = getInteger(tipoArchivo, datos, Oferta.CANTIDAD);

		// @formatter:off
		val result = Oferta.builder()
				.idArchivo(archivo.getId())
				.estadoRegistro(EstadoRegistroType.ESTRUCTURA_VALIDADA)
				.linea(registro.getLinea())
				.numeroLinea(registro.getNumeroLinea())
				.fechaCreacion(archivo.getFechaCreacion())
				.usuarioCreacion(archivo.getUsuarioCreacion())
				.fechaActualizacion(archivo.getFechaCreacion())
				.usuarioActualizacion(archivo.getUsuarioCreacion())
				
				.clienteCodigo(datos.get(Oferta.CLIENTE_CODIGO))
				.servicioCodigo(datos.get(Oferta.SERVICIO_CODIGO))
				.numeroOrden(datos.get(Oferta.NUMERO_ORDEN))
				.femi(femi)
				.fema(fema)
				.productoCodigo(datos.get(Oferta.PRODUCTO_CODIGO))
				.productoNombre(datos.get(Oferta.PRODUCTO_NOMBRE))
				.cantidad(cantidad)
				.bodegaCodigo(datos.get(Oferta.BODEGA_CODIGO))
				.estadoInventarioCodigo(datos.get(Oferta.ESTADO_INVENTARIO_CODIGO))
				.build();
		// @formatter:on

		return result;
	}
}