package com.tacticlogistics.integrador.etl.handlers;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@MappedSuperclass
public class EtlEntidadBase implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(nullable = false)
	@NotNull
	private Long idArchivo;
	
	@Column(nullable = false)
	@NotNull
	private String linea;
	
	@Column(nullable = false)
	@NotNull
	private LocalDateTime fechaCreacion;
	
	@Column(length = 50, nullable = false)
	@NotNull
	private String usuarioCreacion;
}