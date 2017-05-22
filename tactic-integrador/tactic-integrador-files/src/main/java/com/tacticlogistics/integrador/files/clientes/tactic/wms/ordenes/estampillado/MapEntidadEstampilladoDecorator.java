package com.tacticlogistics.integrador.files.clientes.tactic.wms.ordenes.estampillado;

import java.time.LocalDate;

import org.springframework.util.Assert;

import com.tacticlogistics.integrador.dto.ArchivoDTO;
import com.tacticlogistics.integrador.dto.RegistroDTO;
import com.tacticlogistics.integrador.files.handlers.decorators.Filter;
import com.tacticlogistics.integrador.files.handlers.decorators.MapEntidadDecorator;
import com.tacticlogistics.integrador.model.etl.registro.EstadoRegistroType;
import com.tacticlogistics.integrador.model.oms.Estampillado;

import lombok.val;

public class MapEntidadEstampilladoDecorator extends MapEntidadDecorator<Estampillado> {

	public MapEntidadEstampilladoDecorator() {
		super();
	}

	public MapEntidadEstampilladoDecorator(Filter<Estampillado> inner) {
		super(inner);
	}

	@Override
	protected Estampillado map(ArchivoDTO<Estampillado> archivoDTO, RegistroDTO<Estampillado> registro) {
		val tipoArchivo = archivoDTO.getTipoArchivo();
		Assert.notNull(tipoArchivo);
		val archivo = archivoDTO.getArchivo();
		Assert.notNull(archivo);
		val datos = registro.getDatos();
		Assert.notEmpty(datos);

		LocalDate femi = getLocalDate(tipoArchivo, datos, Estampillado.FEMI);
		LocalDate fema = getLocalDate(tipoArchivo, datos, Estampillado.FEMA);
		Integer cantidad = getInteger(tipoArchivo, datos, Estampillado.CANTIDAD);

		// @formatter:off
 		val result = Estampillado.builder()
				.idArchivo(archivo.getId())
				.estadoRegistro(EstadoRegistroType.ESTRUCTURA_VALIDADA)
				.linea(registro.getLinea())
				.numeroLinea(registro.getNumeroLinea())
				.fechaCreacion(archivo.getFechaCreacion())
				.usuarioCreacion(archivo.getUsuarioCreacion())
				.fechaActualizacion(archivo.getFechaCreacion())
				.usuarioActualizacion(archivo.getUsuarioCreacion())
				
				.clienteCodigo(datos.get(Estampillado.CLIENTE_CODIGO))
				.servicioCodigo(datos.get(Estampillado.SERVICIO_CODIGO))
				.numeroOrden(datos.get(Estampillado.NUMERO_ORDEN))
				.femi(femi)
				.fema(fema)
				.productoCodigo(datos.get(Estampillado.PRODUCTO_CODIGO))
				.productoNombre(datos.get(Estampillado.PRODUCTO_NOMBRE))
				.cantidad(cantidad)
				.bodegaCodigo(datos.get(Estampillado.BODEGA_CODIGO))
				.estadoInventarioCodigo(datos.get(Estampillado.ESTADO_INVENTARIO_CODIGO))
				.documentoCliente(datos.get(Estampillado.DOCUMENTO_CLIENTE))
				.build();
		// @formatter:on

		return result;
	}
}