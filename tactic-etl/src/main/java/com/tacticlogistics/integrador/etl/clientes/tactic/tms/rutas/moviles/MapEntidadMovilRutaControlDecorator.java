package com.tacticlogistics.integrador.etl.clientes.tactic.tms.rutas.moviles;

import org.springframework.util.Assert;

import com.tacticlogistics.integrador.etl.clientes.tactic.tms.rutas.moviles.model.MovilRutaControl;
import com.tacticlogistics.integrador.etl.handlers.decorators.Filter;
import com.tacticlogistics.integrador.etl.handlers.decorators.MapEntidadDecorator;
import com.tacticlogistics.integrador.etl.handlers.dto.ArchivoDTO;
import com.tacticlogistics.integrador.etl.handlers.dto.RegistroDTO;

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