package com.tacticlogistics.integrador.etl.model;

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

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(catalog = "etl", name = "archivos")
@Data
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
	@NotNull
	private String nombre;

	@Enumerated(EnumType.STRING)
	@Column(name = "id_estado_archivo", nullable = false, length = 50)
	@NotNull
	private EstadoArchivoType estadoArchivo;

	@Column(nullable = false)
	@NotNull
	private LocalDateTime fechaRecepcion;
	private LocalDateTime fechaValidacion;
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
	private List<ArchivoError> errores = new ArrayList<>();
}
