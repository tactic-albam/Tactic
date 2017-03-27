package com.tacticlogistics.integrador.etl.handlers.dicermex;

import org.springframework.util.Assert;

import com.tacticlogistics.integrador.etl.decorators.Filter;
import com.tacticlogistics.integrador.etl.decorators.MapEntidadDecorator;
import com.tacticlogistics.integrador.etl.dto.ArchivoDTO;
import com.tacticlogistics.integrador.etl.dto.RegistroDTO;
import com.tacticlogistics.integrador.etl.handlers.dicermex.model.Salida;

import lombok.val;

public class MapEntidadSalidaDecorator extends MapEntidadDecorator<Salida> {

	public MapEntidadSalidaDecorator() {
		super();
	}

	public MapEntidadSalidaDecorator(Filter<Salida> inner) {
		super(inner);
	}

	@Override
	protected Salida map(ArchivoDTO<Salida> archivoDTO, RegistroDTO<Salida> registro) {
		val tipoArchivo = archivoDTO.getTipoArchivo();
		Assert.notNull(tipoArchivo);
		val archivo = archivoDTO.getArchivo();
		Assert.notNull(archivo);
		val datos = registro.getDatos();
		Assert.notEmpty(datos);

		val femi = getLocalDate(tipoArchivo, datos, Salida.FEMI);
		val fema = getLocalDate(tipoArchivo, datos, Salida.FEMA);
		val homi = getLocalTime(tipoArchivo, datos, Salida.HOMI);
		val homa = getLocalTime(tipoArchivo, datos, Salida.HOMA);
		Integer valorRecaudar = getInteger(tipoArchivo, datos, Salida.VALOR_RECAUDAR);

		// @formatter:off
		val result = 
				Salida.builder()
				.idArchivo(archivo.getId())
				.linea(registro.getLinea())
				.fechaCreacion(archivo.getFechaCreacion())
				.usuarioCreacion(archivo.getUsuarioCreacion())
				.servicioCodigo(datos.get(Salida.SERVICIO_CODIGO))
				.prefijoOrden(datos.get(Salida.PREFIJO_ORDEN))
				.numeroOrden(datos.get(Salida.NUMERO_ORDEN))
				.femi(femi)
				.fema(fema)
				.homi(homi)
				.homa(homa)
				.canalCodigo(datos.get(Salida.CANAL_CODIGO))
				.terceroIdentificacion(datos.get(Salida.TERCERO_IDENTIFICACION))
				.terceroNombre(datos.get(Salida.TERCERO_NOMBRE))
				.ciudadCodigo(datos.get(Salida.CIUDAD_CODIGO))
				.direccion(datos.get(Salida.DIRECCION))
				.puntoNombre(datos.get(Salida.PUNTO_NOMBRE))
				.telefonoContacto(datos.get(Salida.TELEFONO_CONTACTO))
				.valorRecaudar(valorRecaudar)
				.notas(datos.get(Salida.NOTAS))
				.build();
		// @formatter:on

		return result;
	}
}