package com.tacticlogistics.integrador.files.clientes.dicermex;

import org.springframework.util.Assert;

import com.tacticlogistics.integrador.dto.ArchivoDTO;
import com.tacticlogistics.integrador.dto.RegistroDTO;
import com.tacticlogistics.integrador.files.handlers.decorators.Filter;
import com.tacticlogistics.integrador.files.handlers.decorators.MapEntidadDecorator;
import com.tacticlogistics.integrador.model.clientes.dicermex.DicermexSalida;
import com.tacticlogistics.integrador.model.etl.registro.EstadoRegistroType;

import lombok.val;

public class MapEntidadSalidaDecorator extends MapEntidadDecorator<DicermexSalida> {

	public MapEntidadSalidaDecorator() {
		super();
	}

	public MapEntidadSalidaDecorator(Filter<DicermexSalida> inner) {
		super(inner);
	}

	@Override
	protected DicermexSalida map(ArchivoDTO<DicermexSalida> archivoDTO, RegistroDTO<DicermexSalida> registro) {
		val tipoArchivo = archivoDTO.getTipoArchivo();
		Assert.notNull(tipoArchivo);
		val archivo = archivoDTO.getArchivo();
		Assert.notNull(archivo);
		val datos = registro.getDatos();
		Assert.notEmpty(datos);

		val femi = getLocalDate(tipoArchivo, datos, DicermexSalida.FEMI);
		val fema = getLocalDate(tipoArchivo, datos, DicermexSalida.FEMA);
		val homi = getLocalTime(tipoArchivo, datos, DicermexSalida.HOMI);
		val homa = getLocalTime(tipoArchivo, datos, DicermexSalida.HOMA);
		Integer valorRecaudar = getInteger(tipoArchivo, datos, DicermexSalida.VALOR_RECAUDAR);

		// @formatter:off
		val result = 
				DicermexSalida.builder()
				.idArchivo(archivo.getId())
				.estadoRegistro(EstadoRegistroType.ESTRUCTURA_VALIDADA)
				.numeroLinea(registro.getNumeroLinea())
				.linea(registro.getLinea())
				.fechaCreacion(archivo.getFechaCreacion())
				.usuarioCreacion(archivo.getUsuarioCreacion())
				.fechaActualizacion(archivo.getFechaCreacion())
				.usuarioActualizacion(archivo.getUsuarioCreacion())

				.servicioCodigo(datos.get(DicermexSalida.SERVICIO_CODIGO))
				.prefijoOrden(datos.get(DicermexSalida.PREFIJO_ORDEN))
				.numeroOrden(datos.get(DicermexSalida.NUMERO_ORDEN))
				.femi(femi)
				.fema(fema)
				.homi(homi)
				.homa(homa)
				.canalCodigo(datos.get(DicermexSalida.CANAL_CODIGO))
				.terceroIdentificacion(datos.get(DicermexSalida.TERCERO_IDENTIFICACION))
				.terceroNombre(datos.get(DicermexSalida.TERCERO_NOMBRE))
				.ciudadCodigo(datos.get(DicermexSalida.CIUDAD_CODIGO))
				.direccion(datos.get(DicermexSalida.DIRECCION))
				.puntoNombre(datos.get(DicermexSalida.PUNTO_NOMBRE))
				.telefonoContacto(datos.get(DicermexSalida.TELEFONO_CONTACTO))
				.valorRecaudar(valorRecaudar)
				.notas(datos.get(DicermexSalida.NOTAS))
				.build();
		// @formatter:on

		return result;
	}
}