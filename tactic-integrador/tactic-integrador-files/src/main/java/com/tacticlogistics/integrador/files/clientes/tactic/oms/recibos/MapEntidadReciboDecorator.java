package com.tacticlogistics.integrador.files.clientes.tactic.oms.recibos;

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
import com.tacticlogistics.integrador.model.oms.Recibo;

import lombok.val;

public class MapEntidadReciboDecorator extends MapEntidadDecorator<Recibo> {

	public MapEntidadReciboDecorator() {
		super();
	}

	public MapEntidadReciboDecorator(Filter<Recibo> inner) {
		super(inner);
	}

	@Override
	protected Recibo map(ArchivoDTO<Recibo> archivoDTO, RegistroDTO<Recibo> registro) {
		val tipoArchivo = archivoDTO.getTipoArchivo();
		Assert.notNull(tipoArchivo);
		val archivo = archivoDTO.getArchivo();
		Assert.notNull(archivo);
		val datos = registro.getDatos();
		Assert.notEmpty(datos);


		LocalDate femi = getLocalDate(tipoArchivo, datos, Recibo.FEMI);
		LocalDate fema = getLocalDate(tipoArchivo, datos, Recibo.FEMA);
		LocalTime homi = getLocalTime(tipoArchivo, datos, Recibo.HOMI);
		LocalTime homa = getLocalTime(tipoArchivo, datos, Recibo.HOMA);
		Integer cantidad = getInteger(tipoArchivo, datos, Recibo.CANTIDAD);
		BigDecimal valorUnitarioDeclarado = getBigDecimal(tipoArchivo, datos, Recibo.VALOR_UNITARIO_DECLARADO);
		LocalDate fechaOrden = getLocalDate(tipoArchivo, datos, Recibo.FECHA_ORDEN);
		LocalDate fechaDocumentoCliente = getLocalDate(tipoArchivo, datos, Recibo.FECHA_DOCUMENTO_CLIENTE);

		// @formatter:off
		val result = 
				Recibo.builder()
				.idArchivo(archivo.getId())
				.estadoRegistro(EstadoRegistroType.ESTRUCTURA_VALIDADA)
				.numeroLinea(registro.getNumeroLinea())
				.linea(registro.getLinea())
				.fechaCreacion(archivo.getFechaCreacion())
				.usuarioCreacion(archivo.getUsuarioCreacion())
				.fechaActualizacion(archivo.getFechaCreacion())
				.usuarioActualizacion(archivo.getUsuarioCreacion())

				.clienteCodigo(StringUtils.defaultString(datos.get(Recibo.CLIENTE_CODIGO)))
				.servicioCodigo(StringUtils.defaultString(datos.get(Recibo.SERVICIO_CODIGO)))
				.numeroOrden(StringUtils.defaultString(datos.get(Recibo.NUMERO_ORDEN)))
				.canalCodigo(StringUtils.defaultString(datos.get(Recibo.CANAL_CODIGO)))
				.terceroIdentificacion(StringUtils.defaultString(datos.get(Recibo.TERCERO_IDENTIFICACION)))
				.terceroSucursal(StringUtils.defaultString(datos.get(Recibo.TERCERO_SUCURSAL)))
				.terceroNombre(StringUtils.defaultString(datos.get(Recibo.TERCERO_NOMBRE)))
				.ciudadCodigo(StringUtils.defaultString(datos.get(Recibo.CIUDAD_CODIGO)))
				.direccion(StringUtils.defaultString(datos.get(Recibo.DIRECCION)))
				.puntoCodigo(StringUtils.defaultString(datos.get(Recibo.PUNTO_CODIGO)))
				.puntoNombre(StringUtils.defaultString(datos.get(Recibo.PUNTO_NOMBRE)))
				.femi(femi)
				.fema(fema)
				.homi(homi)
				.homa(homa)
				.contactoNombres(StringUtils.defaultString(datos.get(Recibo.CONTACTO_NOMBRES)))
				.contactoTelefonos(StringUtils.defaultString(datos.get(Recibo.CONTACTO_TELEFONOS)))
				.contactoEmail(StringUtils.defaultString(datos.get(Recibo.CONTACTO_EMAIL)))
				.notas(StringUtils.defaultString(datos.get(Recibo.NOTAS)))
				.requiereTransporte(StringUtils.defaultString(datos.get(Recibo.REQUIERE_TRANSPORTE)))
				.requiereAgendamiento(StringUtils.defaultString(datos.get(Recibo.REQUIERE_AGENDAMIENTO)))
				.productoCodigo(StringUtils.defaultString(datos.get(Recibo.PRODUCTO_CODIGO)))
				.productoNombre(StringUtils.defaultString(datos.get(Recibo.PRODUCTO_NOMBRE)))
				.unidadCodigo(StringUtils.defaultString(datos.get(Recibo.UNIDAD_CODIGO)))
				.cantidad(cantidad)
				.bodegaDestinoCodigo(StringUtils.defaultString(datos.get(Recibo.BODEGA_DESTINO_CODIGO)))
				.estadoInventarioDestinoCodigo(StringUtils.defaultString(datos.get(Recibo.ESTADO_INVENTARIO_DESTINO_CODIGO)))
				.valorUnitarioDeclarado(valorUnitarioDeclarado)
				.lote(StringUtils.defaultString(datos.get(Recibo.LOTE)))
				.bl(StringUtils.defaultString(datos.get(Recibo.BL)))
				.contenedor(StringUtils.defaultString(datos.get(Recibo.CONTENEDOR)))
				.prefijoOrden(StringUtils.defaultString(datos.get(Recibo.PREFIJO_ORDEN)))
				.numeroOrdenSinPrefijo(StringUtils.defaultString(datos.get(Recibo.NUMERO_ORDEN_SIN_PREFIJO)))
				.fechaOrden(fechaOrden)
				.documentoCliente(StringUtils.defaultString(datos.get(Recibo.DOCUMENTO_CLIENTE)))
				.fechaDocumentoCliente(fechaDocumentoCliente)
				.build();
		// @formatter:on

		return result;
	}
}