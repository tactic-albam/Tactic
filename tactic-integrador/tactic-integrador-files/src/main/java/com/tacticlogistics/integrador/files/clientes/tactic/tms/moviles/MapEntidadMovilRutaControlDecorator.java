package com.tacticlogistics.integrador.files.clientes.tactic.tms.moviles;

import org.apache.commons.lang3.Validate;

import com.tacticlogistics.integrador.dto.ArchivoDTO;
import com.tacticlogistics.integrador.dto.RegistroDTO;
import com.tacticlogistics.integrador.files.handlers.decorators.Filter;
import com.tacticlogistics.integrador.files.handlers.decorators.MapEntidadDecorator;
import com.tacticlogistics.integrador.model.etl.registro.EstadoRegistroType;
import com.tacticlogistics.integrador.model.tms.rutas.moviles.MovilRutaControl;

import lombok.val;

public class MapEntidadMovilRutaControlDecorator extends MapEntidadDecorator<MovilRutaControl> {

	public MapEntidadMovilRutaControlDecorator() {
		super();
	}

	public MapEntidadMovilRutaControlDecorator(Filter<MovilRutaControl> inner) {
		super(inner);
	}

	@Override
	protected MovilRutaControl map(ArchivoDTO<MovilRutaControl> archivoDTO, RegistroDTO<MovilRutaControl> registro) {
		val tipoArchivo = archivoDTO.getTipoArchivo();
		Validate.notNull(tipoArchivo);
		val archivo = archivoDTO.getArchivo();
		Validate.notNull(archivo);
		val datos = registro.getDatos();
		Validate.notEmpty(datos);

		// @formatter:off
		val result = MovilRutaControl.builder()
				.idArchivo(archivo.getId())
				.estadoRegistro(EstadoRegistroType.ESTRUCTURA_VALIDADA)
				.linea(registro.getLinea())
				.fechaCreacion(archivo.getFechaCreacion())
				.usuarioCreacion(archivo.getUsuarioCreacion())
				.fechaActualizacion(archivo.getFechaCreacion())
				.usuarioActualizacion(archivo.getUsuarioCreacion())
				
				.movil(datos.get(MovilRutaControl.MOVIL))
				.placa(datos.get(MovilRutaControl.PLACA))
				.build();
		// @formatter:on

		return result;
	}
}