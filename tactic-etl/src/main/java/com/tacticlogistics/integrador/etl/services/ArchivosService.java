package com.tacticlogistics.integrador.etl.services;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.tacticlogistics.integrador.etl.handlers.decorators.ETLErrorDTO;
import com.tacticlogistics.integrador.etl.handlers.dto.ArchivoDTO;
import com.tacticlogistics.integrador.etl.model.etl.Archivo;
import com.tacticlogistics.integrador.etl.model.etl.ArchivoError;
import com.tacticlogistics.integrador.etl.model.etl.ArchivoRepository;
import com.tacticlogistics.integrador.etl.model.etl.EstadoArchivoType;
import com.tacticlogistics.integrador.etl.model.etl.TipoArchivo;

import lombok.val;

@Service
public class ArchivosService {

	@Autowired
	private ArchivoRepository repository;

	@Transactional
	public <T> ArchivoDTO<T> crearArchivo(Path pathArchivo, TipoArchivo tipoArchivo) {
		LocalDateTime fechaCreacion = LocalDateTime.now();
		String usuarioCreacion = System.getProperty("user.name");

		Archivo archivo = new Archivo();
		archivo.setIdTipoArchivo(tipoArchivo.getId());
		archivo.setNombre(pathArchivo.getFileName().toString());
		archivo.setEstadoArchivo(EstadoArchivoType.NO_VALIDADO);
		archivo.setFechaRecepcion(fechaCreacion);
		archivo.setFechaCreacion(fechaCreacion);
		archivo.setUsuarioCreacion(usuarioCreacion);
		archivo.setFechaActualizacion(fechaCreacion);
		archivo.setUsuarioActualizacion(usuarioCreacion);

		archivo = repository.save(archivo);

		// @formatter:off
		val result = ArchivoDTO.<T>builder()
			.pathArchivo(pathArchivo)
			.tipoArchivo(tipoArchivo)
			.archivo(archivo)
			.build();
		// @formatter:on

		return result;
	}

	@Transactional
	public Archivo marcarValido(Archivo archivo) {
		Assert.notNull(archivo);

		LocalDateTime fechaActualizacion = LocalDateTime.now();
		archivo.setEstadoArchivo(EstadoArchivoType.VALIDADO);
		archivo.setFechaValidacion(fechaActualizacion);
		archivo.setFechaActualizacion(fechaActualizacion);
		archivo.setUsuarioActualizacion(archivo.getUsuarioActualizacion());

		val result = repository.save(archivo);

		return result;
	}

	@Transactional
	public Archivo marcarNoValidoPorEstructura(Archivo archivo, List<ETLErrorDTO> errores) {
		Assert.notNull(archivo);
		Assert.notEmpty(errores);

		LocalDateTime fechaActualizacion = LocalDateTime.now();
		archivo.setEstadoArchivo(EstadoArchivoType.ERROR_ESTRUCTURA);
		archivo.setFechaValidacion(fechaActualizacion);
		archivo.setFechaActualizacion(fechaActualizacion);
		archivo.setUsuarioActualizacion(archivo.getUsuarioActualizacion());
		for (val linea : errores) {
			for (val error : linea.getErrores()) {
				val e = new ArchivoError(linea.getNumeroLinea(), linea.getLinea(), error);
				archivo.getErrores().add(e);
			}
		}
		val result = repository.save(archivo);

		return result;
	}

	@Transactional
	public Archivo marcarNoValidoPorExcepcion(Archivo archivo, Throwable e) {
		Assert.notNull(archivo);
		Assert.notNull(e);
		String message = StringUtils.defaultString(e.getMessage(),e.getClass().getName());


		LocalDateTime fechaActualizacion = LocalDateTime.now();
		archivo.setEstadoArchivo(EstadoArchivoType.ERROR_ESTRUCTURA);
		archivo.setFechaValidacion(fechaActualizacion);
		archivo.setFechaActualizacion(fechaActualizacion);
		archivo.setUsuarioActualizacion(archivo.getUsuarioActualizacion());

		val error = new ArchivoError(0, "OCURRIO UNA ERROR GRAVE DURANTE EL PROCESAMIENTO DEL ARCHIVO", message);
		archivo.getErrores().add(error);

		val result = repository.save(archivo);

		return result;
	}
}