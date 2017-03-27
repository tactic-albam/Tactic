package com.tacticlogistics.integrador.etl.handlers.tactic.tms.rutas.moviles;

import org.springframework.util.Assert;

import com.tacticlogistics.integrador.etl.decorators.Filter;
import com.tacticlogistics.integrador.etl.decorators.MapEntidadDecorator;
import com.tacticlogistics.integrador.etl.dto.ArchivoDTO;
import com.tacticlogistics.integrador.etl.dto.RegistroDTO;
import com.tacticlogistics.integrador.etl.handlers.tactic.tms.rutas.moviles.model.MovilRutaControl;

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
		Assert.notNull(tipoArchivo);
		val archivo = archivoDTO.getArchivo();
		Assert.notNull(archivo);
		val datos = registro.getDatos();
		Assert.notEmpty(datos);

		// @formatter:off
		val result = 
				MovilRutaControl.builder()
				.idArchivo(archivo.getId())
				.movil(datos.get(MovilRutaControl.MOVIL))
				.placa(datos.get(MovilRutaControl.PLACA))
				.linea(registro.getLinea())
				.fechaCreacion(archivo.getFechaCreacion())
				.usuarioCreacion(archivo.getUsuarioCreacion())
				.build();
		// @formatter:on

		return result;
	}
}