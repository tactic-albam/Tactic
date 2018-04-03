package com.tacticlogistics.integrador.model.etl.archivo;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;
import org.apache.commons.lang3.Validate;
import org.hibernate.validator.constraints.NotEmpty;

import com.tacticlogistics.integrador.dto.ErrorArchivoDTO;

import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.val;

@Entity
@Table(catalog = "etl", name = "archivos")
@Data
@Setter(AccessLevel.PROTECTED)
@RequiredArgsConstructor
public class Archivo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_archivo")
	private Long id;

	@Column(nullable = false)
	@NotNull
	private Long idTipoArchivo;

	@Column(length = 300, nullable = false)
	@NotEmpty
	private String nombre;

	@Enumerated(EnumType.STRING)
	@Column(name = "id_estado_archivo", nullable = false, length = 50)
	@NotNull
	private EstadoArchivoType estado;
	private boolean novedades;

	private LocalDateTime fechaValidacionEstructura;
	private LocalDateTime fechaHomologacion;
	private LocalDateTime fechaEnriquecimiento;
	private LocalDateTime fechaValidacionNegocio;
	private LocalDateTime fechaProcesamiento;

	@Column(nullable = false)
	@NotNull
	private LocalDateTime fechaCreacion;

	@Column(length = 50, nullable = false)
	@NotNull
	private String usuarioCreacion;

	@Column(nullable = false)
	@NotNull
	private LocalDateTime fechaActualizacion;

	@Column(length = 50, nullable = false)
	@NotNull
	private String usuarioActualizacion;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "id_archivo", nullable = false)
	private List<ErrorArchivo> errores = new ArrayList<>();

	public static Archivo crear(Long idTipoArchivo, Path path) {
		Archivo result = new Archivo();
		result.setIdTipoArchivo(idTipoArchivo);
		result.setNombre(path.getFileName().toString());

		EstadoArchivoType estado = EstadoArchivoType.NO_PROCESADO;
		LocalDateTime fecha = LocalDateTime.now();
		result.actualizar(estado, fecha);

		result.setFechaCreacion(fecha);
		result.setUsuarioCreacion(SystemUtils.USER_NAME);

		return result;
	}

	public void marcarValidoPorEstructura() {
		EstadoArchivoType estado = EstadoArchivoType.ESTRUCTURA_VALIDADA;
		LocalDateTime fecha = LocalDateTime.now();
		this.actualizar(estado, fecha);

		this.setFechaValidacionEstructura(fecha);
	}

	public void marcarNoValidoPorEstructura(List<ErrorArchivoDTO> errores) {
		EstadoArchivoType estado = EstadoArchivoType.ERROR_ESTRUCTURA;
		LocalDateTime fecha = LocalDateTime.now();
		this.actualizar(estado, fecha, errores);

		this.setFechaValidacionEstructura(fecha);
	}

	public void marcarHomologado(List<ErrorArchivoDTO> errores) {
		EstadoArchivoType estado = EstadoArchivoType.HOMOLOGADO;
		LocalDateTime fecha = LocalDateTime.now();
		this.actualizar(estado, fecha, errores);

		this.setFechaHomologacion(fecha);
	}

	public void marcarEnriquecido(List<ErrorArchivoDTO> errores) {
		EstadoArchivoType estado = EstadoArchivoType.ENRIQUECIDO;
		LocalDateTime fecha = LocalDateTime.now();
		this.actualizar(estado, fecha, errores);

		this.setFechaEnriquecimiento(fecha);
	}

	public void marcarValidadoPorNegocio(List<ErrorArchivoDTO> errores) {
		EstadoArchivoType estado = EstadoArchivoType.VALIDADO;
		LocalDateTime fecha = LocalDateTime.now();
		this.actualizar(estado, fecha, errores);

		this.setFechaValidacionNegocio(fecha);
	}

	public void marcarProcesado(List<ErrorArchivoDTO> errores) {
		EstadoArchivoType estado = EstadoArchivoType.PROCESADO;
		LocalDateTime fecha = LocalDateTime.now();
		this.actualizar(estado, fecha, errores);

		this.setFechaProcesamiento(fecha);
	}

	public void marcarNoValidoPorExcepcion(Throwable e) {
		String message = StringUtils.defaultString(e.getMessage(), e.getClass().getName());
		List<String> errores = new ArrayList<>();
		errores.add(message);

		val error = new ErrorArchivoDTO(0, "ERROR A NIVEL DE ARCHIVO", errores);

		EstadoArchivoType estado = EstadoArchivoType.ERROR_FATAL;
		LocalDateTime fecha = LocalDateTime.now();
		this.actualizar(estado, fecha, error);
	}

	private void actualizar(EstadoArchivoType estado, LocalDateTime fecha) {
		this.actualizar(estado, fecha, (ErrorArchivoDTO) null);
	}

	private void actualizar(EstadoArchivoType estado, LocalDateTime fecha, ErrorArchivoDTO error) {
		List<ErrorArchivoDTO> errores = null;
		if (error != null) {
			errores = new ArrayList<>();
			errores.add(error);
		}
		this.actualizar(estado, fecha, errores);
	}

	private void actualizar(EstadoArchivoType estado, LocalDateTime fecha, List<ErrorArchivoDTO> errores) {
		Validate.notNull(estado);
		Validate.notNull(fecha);

		this.setFechaActualizacion(fecha);
		this.setUsuarioActualizacion(SystemUtils.USER_NAME);

		boolean novedades = false;

		if (errores != null) {
			novedades = errores.size() > 0;
			for (val linea : errores) {
				for (val error : linea.getErrores()) {
					val e = new ErrorArchivo(linea.getNumeroLinea(), linea.getLinea(), error);
					this.getErrores().add(e);
				}
			}
		}

		this.setEstado(estado);
		this.setNovedades(novedades);
	}

}
