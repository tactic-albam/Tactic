package com.tacticlogistics.integrador.etl.handlers.tactic.tms.rutas.manuales;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.util.Assert;

import com.tacticlogistics.integrador.etl.decorators.Filter;
import com.tacticlogistics.integrador.etl.decorators.MapEntidadDecorator;
import com.tacticlogistics.integrador.etl.dto.ArchivoDTO;
import com.tacticlogistics.integrador.etl.dto.RegistroDTO;
import com.tacticlogistics.integrador.etl.handlers.tactic.tms.rutas.manuales.model.RutaManual;
import com.tacticlogistics.integrador.etl.handlers.tactic.tms.rutas.toursolver.model.RutaTourSolver;

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
		Assert.notNull(tipoArchivo);
		val archivo = archivoDTO.getArchivo();
		Assert.notNull(archivo);
		val datos = registro.getDatos();
		Assert.notEmpty(datos);

		int secuencia = getInteger(tipoArchivo, datos, RutaManual.SECUENCIA);
		LocalDateTime fechaHora = getLocalDateTime(tipoArchivo, datos, RutaManual.FECHA_HORA_ESTIMADA_ENTREGA);
		BigDecimal cx = getBigDecimal(tipoArchivo, datos, RutaManual.CX);
		BigDecimal cy = getBigDecimal(tipoArchivo, datos, RutaManual.CY);

		// @formatter:off
		val result = 
				RutaManual.builder()
				.idArchivo(archivo.getId())
				.clienteIdentificacion(datos.get(RutaManual.CLIENTE_IDENTIFICACION))
				.numeroOrden(datos.get(RutaManual.NUMERO_ORDEN))
				.placa(datos.get(RutaTourSolver.PLACA))
				.secuencia(secuencia)
				.fechaHoraEstimadaEntrega(fechaHora)
				.cx(cx)
				.cy(cy)
				.linea(registro.getLinea())
				.fechaCreacion(archivo.getFechaCreacion())
				.usuarioCreacion(archivo.getUsuarioCreacion())
				.build();
		// @formatter:on

		return result;
	}
}
