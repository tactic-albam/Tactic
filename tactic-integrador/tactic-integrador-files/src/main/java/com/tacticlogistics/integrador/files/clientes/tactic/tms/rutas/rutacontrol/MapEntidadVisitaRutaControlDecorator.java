package com.tacticlogistics.integrador.files.clientes.tactic.tms.rutas.rutacontrol;

import java.time.LocalDateTime;

import org.springframework.util.Assert;

import com.tacticlogistics.integrador.dto.ArchivoDTO;
import com.tacticlogistics.integrador.dto.RegistroDTO;
import com.tacticlogistics.integrador.files.handlers.decorators.Filter;
import com.tacticlogistics.integrador.files.handlers.decorators.MapEntidadDecorator;
import com.tacticlogistics.integrador.model.etl.registro.EstadoRegistroType;
import com.tacticlogistics.integrador.model.tms.rutas.rutacontrol.VisitaRutaControl;

import lombok.val;

public class MapEntidadVisitaRutaControlDecorator extends MapEntidadDecorator<VisitaRutaControl> {

	public MapEntidadVisitaRutaControlDecorator() {
		super();
	}

	public MapEntidadVisitaRutaControlDecorator(Filter<VisitaRutaControl> inner) {
		super(inner);
	}

	@Override
	protected VisitaRutaControl map(ArchivoDTO<VisitaRutaControl> archivoDTO, RegistroDTO<VisitaRutaControl> registro) {
		val tipoArchivo = archivoDTO.getTipoArchivo();
		Assert.notNull(tipoArchivo);
		val archivo = archivoDTO.getArchivo();
		Assert.notNull(archivo);
		val datos = registro.getDatos();
		Assert.notEmpty(datos);

		Integer valorRecaudado = getInteger(tipoArchivo, datos, VisitaRutaControl.VALOR_RECAUDADO);
		LocalDateTime fechaInicio = getLocalDateTime(tipoArchivo, datos, VisitaRutaControl.FECHA_HORA_INICIO_VISITA);
		LocalDateTime fechaFin = getLocalDateTime(tipoArchivo, datos, VisitaRutaControl.FECHA_HORA_FIN_VISITA);

		// @formatter:off
		val result = VisitaRutaControl.builder()
				.idArchivo(archivo.getId())
				.estadoRegistro(EstadoRegistroType.ESTRUCTURA_VALIDADA)
				.linea(registro.getLinea())
				.fechaCreacion(archivo.getFechaCreacion())
				.usuarioCreacion(archivo.getUsuarioCreacion())
				.fechaActualizacion(archivo.getFechaCreacion())
				.usuarioActualizacion(archivo.getUsuarioCreacion())				
				
				.clienteIdentificacion(datos.get(VisitaRutaControl.CLIENTE_IDENTIFICACION))
				.numeroOrden(datos.get(VisitaRutaControl.NUMERO_ORDEN))
				.placa(datos.get(VisitaRutaControl.PLACA))
				.estado(datos.get(VisitaRutaControl.ESTADO))
				.fechaHoraInicioVisita(fechaInicio)
				.fechaHoraFinVisita(fechaFin)
				.causal(datos.get(VisitaRutaControl.CAUSAL))
				.valorRecaudado(valorRecaudado)
				.observaciones(datos.get(VisitaRutaControl.OBSERVACION))
				.build();
		// @formatter:on

		return result;
	}
}