package com.tacticlogistics.integrador.files.clientes.tactic.oms.salidas;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import com.tacticlogistics.integrador.dto.ArchivoDTO;
import com.tacticlogistics.integrador.dto.RegistroDTO;
import com.tacticlogistics.integrador.files.handlers.decorators.Filter;
import com.tacticlogistics.integrador.files.handlers.decorators.MapEntidadDecorator;
import com.tacticlogistics.integrador.model.etl.registro.EstadoRegistroType;
import com.tacticlogistics.integrador.model.oms.Salida;

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
		LocalDate fechaOrden = getLocalDate(tipoArchivo, datos, Salida.FECHA_ORDEN);
		LocalDate fechaDocumentoCliente = getLocalDate(tipoArchivo, datos, Salida.FECHA_DOCUMENTO_CLIENTE);

		// @formatter:off
		val result = 
				Salida.builder()
				.idArchivo(archivo.getId())
				.estadoRegistro(EstadoRegistroType.ESTRUCTURA_VALIDADA)
				.numeroLinea(registro.getNumeroLinea())
				.linea(registro.getLinea())
				.fechaCreacion(archivo.getFechaCreacion())
				.usuarioCreacion(archivo.getUsuarioCreacion())
				.fechaActualizacion(archivo.getFechaCreacion())
				.usuarioActualizacion(archivo.getUsuarioCreacion())
				
				.clienteCodigo(StringUtils.defaultString(datos.get(Salida.CLIENTE_CODIGO)))
				.servicioCodigo(StringUtils.defaultString(datos.get(Salida.SERVICIO_CODIGO)))
				.numeroOrden(StringUtils.defaultString(datos.get(Salida.NUMERO_ORDEN)))
				.canalCodigo(StringUtils.defaultString(datos.get(Salida.CANAL_CODIGO)))
				.terceroIdentificacion(StringUtils.defaultString(datos.get(Salida.TERCERO_IDENTIFICACION)))
				.terceroSucursal(StringUtils.defaultString(datos.get(Salida.TERCERO_SUCURSAL)))
				.terceroNombre(StringUtils.defaultString(datos.get(Salida.TERCERO_NOMBRE)))
				.ciudadCodigo(StringUtils.defaultString(datos.get(Salida.CIUDAD_CODIGO)))
				.direccion(StringUtils.defaultString(datos.get(Salida.DIRECCION)))
				.puntoCodigo(StringUtils.defaultString(datos.get(Salida.PUNTO_CODIGO)))
				.puntoNombre(StringUtils.defaultString(datos.get(Salida.PUNTO_NOMBRE)))
				.femi(femi)
				.fema(fema)
				.homi(homi)
				.homa(homa)
				.contactoNombres(StringUtils.defaultString(datos.get(Salida.CONTACTO_NOMBRES)))
				.contactoTelefonos(StringUtils.defaultString(datos.get(Salida.CONTACTO_TELEFONOS)))
				.contactoEmail(StringUtils.defaultString(datos.get(Salida.CONTACTO_EMAIL)))
				.notas(StringUtils.defaultString(datos.get(Salida.NOTAS)))
				.requiereTransporte(StringUtils.defaultString(datos.get(Salida.REQUIERE_TRANSPORTE)))
				.autorizadoIdentificacion(StringUtils.defaultString(datos.get(Salida.AUTORIZADO_IDENTIFICACION)))
				.autorizadoNombres(StringUtils.defaultString(datos.get(Salida.AUTORIZADO_NOMBRES)))
				.requiereRecaudo(StringUtils.defaultString(datos.get(Salida.REQUIERE_RECAUDO)))
				.valorRecaudar(valorRecaudar)
				.requiereAgendamiento(StringUtils.defaultString(datos.get(Salida.REQUIERE_AGENDAMIENTO)))
				.productoCodigo(StringUtils.defaultString(datos.get(Salida.PRODUCTO_CODIGO)))
				.productoNombre(StringUtils.defaultString(datos.get(Salida.PRODUCTO_NOMBRE)))
				.unidadCodigo(StringUtils.defaultString(datos.get(Salida.UNIDAD_CODIGO)))
				.cantidad(cantidad)
				.bodegaOrigenCodigo(StringUtils.defaultString(datos.get(Salida.BODEGA_ORIGEN_CODIGO)))
				.estadoInventarioOrigenCodigo(StringUtils.defaultString(datos.get(Salida.ESTADO_INVENTARIO_ORIGEN_CODIGO)))
				.bodegaDestinoCodigo(StringUtils.defaultString(datos.get(Salida.BODEGA_DESTINO_CODIGO)))
				.estadoInventarioDestinoCodigo(StringUtils.defaultString(datos.get(Salida.ESTADO_INVENTARIO_DESTINO_CODIGO)))
				.valorUnitarioDeclarado(valorUnitarioDeclarado)
				.lote(StringUtils.defaultString(datos.get(Salida.LOTE)))
				.predistribucionCrossdock(StringUtils.defaultString(datos.get(Salida.PREDISTRIBUCION_CROSSDOCK)))
				.predistribucionEvento(StringUtils.defaultString(datos.get(Salida.PREDISTRIBUCION_EVENTO)))
				.prefijoOrden(StringUtils.defaultString(datos.get(Salida.PREFIJO_ORDEN)))
				.numeroOrdenSinPrefijo(StringUtils.defaultString(datos.get(Salida.NUMERO_ORDEN_SIN_PREFIJO)))
				.fechaOrden(fechaOrden)
				.documentoCliente(StringUtils.defaultString(datos.get(Salida.DOCUMENTO_CLIENTE)))
				.fechaDocumentoCliente(fechaDocumentoCliente)
				.build();
		// @formatter:on

		return result;
	}
}