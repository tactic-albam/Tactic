package com.tacticlogistics.integrador.files.clientes.tactic.oms;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

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

				.clienteCodigo(datos.get(Recibo.CLIENTE_CODIGO))
				.servicioCodigo(datos.get(Recibo.SERVICIO_CODIGO))
				.numeroOrden(datos.get(Recibo.NUMERO_ORDEN))
				.canalCodigo(datos.get(Recibo.CANAL_CODIGO))
				.terceroIdentificacion(datos.get(Recibo.TERCERO_IDENTIFICACION))
				.terceroSucursal(datos.get(Recibo.TERCERO_SUCURSAL))
				.terceroNombre(datos.get(Recibo.TERCERO_NOMBRE))
				.ciudadCodigo(datos.get(Recibo.CIUDAD_CODIGO))
				.direccion(datos.get(Recibo.DIRECCION))
				.puntoCodigo(datos.get(Recibo.PUNTO_CODIGO))
				.puntoNombre(datos.get(Recibo.PUNTO_NOMBRE))
				.femi(femi)
				.fema(fema)
				.homi(homi)
				.homa(homa)
				.contactoNombres(datos.get(Recibo.CONTACTO_NOMBRES))
				.contactoTelefonos(datos.get(Recibo.CONTACTO_TELEFONOS))
				.contactoEmail(datos.get(Recibo.CONTACTO_EMAIL))
				.notas(datos.get(Recibo.NOTAS))
				.requiereTransporte(datos.get(Recibo.REQUIERE_TRANSPORTE))
				.requiereAgendamiento(datos.get(Recibo.REQUIERE_AGENDAMIENTO))
				.productoCodigo(datos.get(Recibo.PRODUCTO_CODIGO))
				.productoCodigoAlterno(datos.get(Recibo.PRODUCTO_CODIGO_ALTERNO))
				.productoNombre(datos.get(Recibo.PRODUCTO_NOMBRE))
				.cantidad(cantidad)
				.bodegaDestinoCodigo(datos.get(Recibo.BODEGA_DESTINO_CODIGO))
				.estadoInventarioDestinoCodigo(datos.get(Recibo.ESTADO_INVENTARIO_DESTINO_CODIGO))
				.valorUnitarioDeclarado(valorUnitarioDeclarado)
				.lote(datos.get(Recibo.LOTE))
				.bl(datos.get(Recibo.BL))
				.contenedor(datos.get(Recibo.CONTENEDOR))
				.predistribucionCrossdock(datos.get(Recibo.PREDISTRIBUCION_CROSSDOCK))
				.predistribucionEvento(datos.get(Recibo.PREDISTRIBUCION_EVENTO))
				.prefijoOrden(datos.get(Recibo.PREFIJO_ORDEN))
				.numeroOrdenSinPrefijo(datos.get(Recibo.NUMERO_ORDEN_SIN_PREFIJO))
				.fechaOrden(fechaOrden)
				.documentoCliente(datos.get(Recibo.DOCUMENTO_CLIENTE))
				.fechaDocumentoCliente(fechaDocumentoCliente)
				.build();
		// @formatter:on

		return result;
	}
}