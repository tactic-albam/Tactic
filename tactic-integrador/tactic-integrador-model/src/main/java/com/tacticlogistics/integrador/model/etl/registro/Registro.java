package com.tacticlogistics.integrador.model.etl.registro;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@MappedSuperclass
public class Registro implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(nullable = false)
	@NotNull
	private Long idArchivo;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "id_estado_registro", nullable = false, length = 50)
	@NotNull
	private EstadoRegistroType estadoRegistro;

	private int numeroLinea;
	
	@Column(nullable = false)
	@NotNull
	private String linea;
	
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
}