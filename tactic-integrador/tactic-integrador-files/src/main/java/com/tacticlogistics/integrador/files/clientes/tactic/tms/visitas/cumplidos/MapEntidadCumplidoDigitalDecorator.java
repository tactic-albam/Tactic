package com.tacticlogistics.integrador.files.clientes.tactic.tms.visitas.cumplidos;

import org.apache.commons.lang3.Validate;

import com.tacticlogistics.integrador.dto.ArchivoDTO;
import com.tacticlogistics.integrador.dto.RegistroDTO;
import com.tacticlogistics.integrador.files.handlers.decorators.Filter;
import com.tacticlogistics.integrador.files.handlers.decorators.MapEntidadDecorator;
import com.tacticlogistics.integrador.model.etl.registro.EstadoRegistroType;
import com.tacticlogistics.integrador.model.tms.cumplidos.CumplidoDigital;

import lombok.val;

public class MapEntidadCumplidoDigitalDecorator extends MapEntidadDecorator<CumplidoDigital> {

	public MapEntidadCumplidoDigitalDecorator() {
		super();
	}

	public MapEntidadCumplidoDigitalDecorator(Filter<CumplidoDigital> inner) {
		super(inner);
	}

	@Override
	protected CumplidoDigital map(ArchivoDTO<CumplidoDigital> archivoDTO, RegistroDTO<CumplidoDigital> registro) {
		val tipoArchivo = archivoDTO.getTipoArchivo();
		Validate.notNull(tipoArchivo);
		val archivo = archivoDTO.getArchivo();
		Validate.notNull(archivo);
		val datos = registro.getDatos();
		Validate.notEmpty(datos);

		// @formatter:off
		val result = CumplidoDigital.builder()
				.idArchivo(archivo.getId())
				.estadoRegistro(EstadoRegistroType.ESTRUCTURA_VALIDADA)
				.linea(registro.getLinea())
				.fechaCreacion(archivo.getFechaCreacion())
				.usuarioCreacion(archivo.getUsuarioCreacion())
				.fechaActualizacion(archivo.getFechaCreacion())
				.usuarioActualizacion(archivo.getUsuarioCreacion())

				.nombreArchivo(datos.get(CumplidoDigital.NOMBRE_ARCHIVO))
				.clienteNombre(datos.get(CumplidoDigital.CLIENTE_NOMBRE))
				.numeroOrden(datos.get(CumplidoDigital.NUMERO_ORDEN))
				.build();
		// @formatter:on

		return result;
	}
}
