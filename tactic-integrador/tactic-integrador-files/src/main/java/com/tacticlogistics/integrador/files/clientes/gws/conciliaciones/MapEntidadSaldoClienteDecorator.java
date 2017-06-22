package com.tacticlogistics.integrador.files.clientes.gws.conciliaciones;

import java.time.LocalDateTime;

import org.springframework.util.Assert;

import com.tacticlogistics.integrador.dto.ArchivoDTO;
import com.tacticlogistics.integrador.dto.RegistroDTO;
import com.tacticlogistics.integrador.files.handlers.decorators.Filter;
import com.tacticlogistics.integrador.files.handlers.decorators.MapEntidadDecorator;
import com.tacticlogistics.integrador.model.etl.registro.EstadoRegistroType;
import com.tacticlogistics.integrador.model.wms.conciliaciones.SaldoCliente;

import lombok.val;

public class MapEntidadSaldoClienteDecorator extends MapEntidadDecorator<SaldoCliente> {

	public MapEntidadSaldoClienteDecorator() {
		super();
	}

	public MapEntidadSaldoClienteDecorator(Filter<SaldoCliente> inner) {
		super(inner);
	}

	@Override
	protected SaldoCliente map(ArchivoDTO<SaldoCliente> archivoDTO, RegistroDTO<SaldoCliente> registro) {
		val tipoArchivo = archivoDTO.getTipoArchivo();
		Assert.notNull(tipoArchivo);
		val archivo = archivoDTO.getArchivo();
		Assert.notNull(archivo);
		val datos = registro.getDatos();
		Assert.notEmpty(datos);

		LocalDateTime fecha = getLocalDateTime (tipoArchivo, datos, SaldoCliente.FECHA);
		Integer cantidad = getInteger(tipoArchivo, datos, SaldoCliente.CANTIDAD);
		Integer valor = getInteger(tipoArchivo, datos, SaldoCliente.VALOR);

		// @formatter:off
 		val result = SaldoCliente.builder()
				.idArchivo(archivo.getId())
				.estadoRegistro(EstadoRegistroType.ESTRUCTURA_VALIDADA)
				.linea(registro.getLinea())
				.numeroLinea(registro.getNumeroLinea())
				.fechaCreacion(archivo.getFechaCreacion())
				.usuarioCreacion(archivo.getUsuarioCreacion())
				.fechaActualizacion(archivo.getFechaCreacion())
				.usuarioActualizacion(archivo.getUsuarioCreacion())
				
				.clienteCodigo(datos.get(SaldoCliente.CLIENTE_CODIGO))
				.fecha(fecha)
				.productoCodigo(datos.get(SaldoCliente.PRODUCTO_CODIGO))
				.cantidad(cantidad)
				.valor(valor)
				.bodegaCodigo(datos.get(SaldoCliente.BODEGA_CODIGO))
				.bodegaNombre(datos.get(SaldoCliente.BODEGA_NOMBRE))
				.build();
		// @formatter:on

		return result;
	}
}