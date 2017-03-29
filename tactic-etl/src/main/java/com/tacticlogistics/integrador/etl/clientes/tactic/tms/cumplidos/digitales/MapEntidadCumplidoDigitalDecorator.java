package com.tacticlogistics.integrador.etl.clientes.tactic.tms.cumplidos.digitales;

import org.springframework.util.Assert;

import com.tacticlogistics.integrador.etl.clientes.tactic.tms.cumplidos.digitales.model.CumplidoDigital;
import com.tacticlogistics.integrador.etl.handlers.decorators.Filter;
import com.tacticlogistics.integrador.etl.handlers.decorators.MapEntidadDecorator;
import com.tacticlogistics.integrador.etl.handlers.dto.ArchivoDTO;
import com.tacticlogistics.integrador.etl.handlers.dto.RegistroDTO;

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
		Assert.notNull(tipoArchivo);
		val archivo = archivoDTO.getArchivo();
		Assert.notNull(archivo);
		val datos = registro.getDatos();
		Assert.notEmpty(datos);

		// @formatter:off
		val result = 
				CumplidoDigital.builder()
				.idArchivo(archivo.getId())
				.nombreArchivo(datos.get(CumplidoDigital.NOMBRE_ARCHIVO))
				.clienteNombre(datos.get(CumplidoDigital.CLIENTE_NOMBRE))
				.numeroOrden(datos.get(CumplidoDigital.NUMERO_ORDEN))
				.linea(registro.getLinea())
				.fechaCreacion(archivo.getFechaCreacion())
				.usuarioCreacion(archivo.getUsuarioCreacion())
				.build();
		// @formatter:on

		return result;
	}
}
