package com.tacticlogistics.integrador.files.clientes.tactic.tms.rutas.manuales;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.apache.commons.lang3.Validate;

import com.tacticlogistics.integrador.dto.ArchivoDTO;
import com.tacticlogistics.integrador.dto.RegistroDTO;
import com.tacticlogistics.integrador.files.handlers.decorators.Filter;
import com.tacticlogistics.integrador.files.handlers.decorators.MapEntidadDecorator;
import com.tacticlogistics.integrador.model.etl.registro.EstadoRegistroType;
import com.tacticlogistics.integrador.model.tms.rutas.manuales.RutaManual;
import com.tacticlogistics.integrador.model.tms.rutas.toursolver.RutaTourSolver;

import lombok.val;

public class MapEntidadRutaManualDecorator extends MapEntidadDecorator<RutaManual> {

	public MapEntidadRutaManualDecorator() {
		super();
	}

	public MapEntidadRutaManualDecorator(Filter<RutaManual> inner) {
		super(inner);
	}

	@Override
	protected RutaManual map(ArchivoDTO<RutaManual> archivoDTO, RegistroDTO<RutaManual> registro) {
		val tipoArchivo = archivoDTO.getTipoArchivo();
		Validate.notNull(tipoArchivo);
		val archivo = archivoDTO.getArchivo();
		Validate.notNull(archivo);
		val datos = registro.getDatos();
		Validate.notEmpty(datos);

		LocalDateTime fechaHora = getLocalDateTime(tipoArchivo, datos, RutaManual.FECHA_HORA_ESTIMADA_ENTREGA);
		BigDecimal cx = getBigDecimal(tipoArchivo, datos, RutaManual.CX);
		BigDecimal cy = getBigDecimal(tipoArchivo, datos, RutaManual.CY);

		// @formatter:off
		val result = RutaManual.builder()
				.idArchivo(archivo.getId())
				.estadoRegistro(EstadoRegistroType.ESTRUCTURA_VALIDADA)
				.numeroLinea(registro.getNumeroLinea())
				.linea(registro.getLinea())
				.fechaCreacion(archivo.getFechaCreacion())
				.usuarioCreacion(archivo.getUsuarioCreacion())
				.fechaActualizacion(archivo.getFechaCreacion())
				.usuarioActualizacion(archivo.getUsuarioCreacion())
				
				.clienteIdentificacion(datos.get(RutaManual.CLIENTE_IDENTIFICACION))
				.numeroOrden(datos.get(RutaManual.NUMERO_ORDEN))
				.placa(datos.get(RutaTourSolver.PLACA))
				.fechaHoraEstimadaEntrega(fechaHora)
				.cx(cx)
				.cy(cy)
				.build();
		// @formatter:on

		return result;
	}
}
