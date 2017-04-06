package com.tacticlogistics.integrador.etl.clientes.tactic.oms;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.util.Assert;

import com.tacticlogistics.integrador.etl.handlers.decorators.Filter;
import com.tacticlogistics.integrador.etl.handlers.decorators.MapEntidadDecorator;
import com.tacticlogistics.integrador.etl.handlers.dto.ArchivoDTO;
import com.tacticlogistics.integrador.etl.handlers.dto.RegistroDTO;
import com.tacticlogistics.integrador.etl.model.oms.Salida;

import lombok.val;

public class MapEntidadSalidaDecorator extends MapEntidadDecorator<Salida> {

	public MapEntidadSalidaDecorator() {
		super();
	}

	public MapEntidadSalidaDecorator(Filter<Salida> inner) {
		super(inner);
	}

	@Override
	protected Salida map(ArchivoDTO<Salida> archivoDTO, RegistroDTO<Salida> registro) {
		val tipoArchivo = archivoDTO.getTipoArchivo();
		Assert.notNull(tipoArchivo);
		val archivo = archivoDTO.getArchivo();
		Assert.notNull(archivo);
		val datos = registro.getDatos();
		Assert.notEmpty(datos);


		LocalDate femi = getLocalDate(tipoArchivo, datos, Salida.FEMI);
		LocalDate fema = getLocalDate(tipoArchivo, datos, Salida.FEMA);
		LocalTime homi = getLocalTime(tipoArchivo, datos, Salida.HOMI);
		LocalTime homa = getLocalTime(tipoArchivo, datos, Salida.HOMA);
		Integer valorRecaudar = getInteger(tipoArchivo, datos, Salida.VALOR_RECAUDAR);
		Integer cantidad = getInteger(tipoArchivo, datos, Salida.CANTIDAD);
		BigDecimal valorUnitarioDeclarado = getBigDecimal(tipoArchivo, datos, Salida.VALOR_UNITARIO_DECLARADO);
		BigDecimal valorVentaLinea = getBigDecimal(tipoArchivo, datos, Salida.VALOR_VENTA_LINEA);
		LocalDate fechaOrden = getLocalDate(tipoArchivo, datos, Salida.FECHA_ORDEN);
		LocalDate fechaDocumentoCliente = getLocalDate(tipoArchivo, datos, Salida.FECHA_DOCUMENTO_CLIENTE);

		// @formatter:off
		val result = 
				Salida.builder()
				.idArchivo(archivo.getId())
				.linea(registro.getLinea())
				.fechaCreacion(archivo.getFechaCreacion())
				.usuarioCreacion(archivo.getUsuarioCreacion())
				.clienteCodigo(datos.get(Salida.CLIENTE_CODIGO))
				.servicioCodigo(datos.get(Salida.SERVICIO_CODIGO))
				.numeroOrden(datos.get(Salida.NUMERO_ORDEN))
				.canalCodigo(datos.get(Salida.CANAL_CODIGO))
				.terceroIdentificacion(datos.get(Salida.TERCERO_IDENTIFICACION))
				.terceroSucursal(datos.get(Salida.TERCERO_SUCURSAL))
				.terceroNombre(datos.get(Salida.TERCERO_NOMBRE))
				.ciudadCodigo(datos.get(Salida.CIUDAD_CODIGO))
				.direccion(datos.get(Salida.DIRECCION))
				.puntoCodigo(datos.get(Salida.PUNTO_CODIGO))
				.puntoNombre(datos.get(Salida.PUNTO_NOMBRE))
				.femi(femi)
				.fema(fema)
				.homi(homi)
				.homa(homa)
				.contactoNombres(datos.get(Salida.CONTACTO_NOMBRES))
				.contactoTelefonos(datos.get(Salida.CONTACTO_TELEFONOS))
				.contactoEmail(datos.get(Salida.CONTACTO_EMAIL))
				.notas(datos.get(Salida.NOTAS))
				.requiereTransporte(datos.get(Salida.REQUIERE_TRANSPORTE))
				.autorizadoIdentificacion(datos.get(Salida.AUTORIZADO_IDENTIFICACION))
				.autorizadoNombres(datos.get(Salida.AUTORIZADO_NOMBRES))
				.requiereRecaudo(datos.get(Salida.REQUIERE_RECAUDO))
				.valorRecaudar(valorRecaudar)
				.requiereAgendamiento(datos.get(Salida.REQUIERE_AGENDAMIENTO))
				.productoCodigo(datos.get(Salida.PRODUCTO_CODIGO))
				.productoCodigoAlterno(datos.get(Salida.PRODUCTO_CODIGO_ALTERNO))
				.productoNombre(datos.get(Salida.PRODUCTO_NOMBRE))
				.cantidad(cantidad)
				.bodegaOrigenCodigo(datos.get(Salida.BODEGA_ORIGEN_CODIGO))
				.bodegaDestinoCodigo(datos.get(Salida.BODEGA_DESTINO_CODIGO))
				.valorUnitarioDeclarado(valorUnitarioDeclarado)
				.valorVentaLinea(valorVentaLinea)
				.lote(datos.get(Salida.LOTE))
				.predistribucionCrossdock(datos.get(Salida.PREDISTRIBUCION_CROSSDOCK))
				.predistribucionEvento(datos.get(Salida.PREDISTRIBUCION_EVENTO))
				.prefijoOrden(datos.get(Salida.PREFIJO_ORDEN))
				.numeroOrdenSinPrefijo(datos.get(Salida.NUMERO_ORDEN_SIN_PREFIJO))
				.fechaOrden(fechaOrden)
				.documentoCliente(datos.get(Salida.DOCUMENTO_CLIENTE))
				.fechaDocumentoCliente(fechaDocumentoCliente)
				.build();
		// @formatter:on

		return result;
	}
}