package com.tacticlogistics.integrador.etl.handlers.tactic.tms.cumplidos.digitales.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Data;

@Entity
@Table(catalog = "tms", name = "cumplidos_digitales")
@Data
@Builder
public class CumplidoDigital {

	public static final String NOMBRE_ARCHIVO = "NOMBRE_ARCHIVO";
	public static final String CLIENTE_NOMBRE = "CLIENTE_NOMBRE";
	public static final String NUMERO_ORDEN = "NUMERO_ORDEN";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cumplido_digital")
	private Long id;

	@Column(nullable = false)
	@NotNull
	private Long idArchivo;

	@Column(length = 300, nullable = false)
	@NotNull
	private String nombreArchivo;

	@Column(length = 100, nullable = false)
	@NotNull
	private String clienteNombre;

	@Column(length = 300, nullable = false)
	@NotNull
	private String numeroOrden;

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
