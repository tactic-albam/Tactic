package com.tacticlogistics.integrador.etl.clientes.tactic.tms.rutas.moviles.model;

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
@Table(catalog = "tms", name = "moviles_rutacontrol")
@Data
@Builder
public class MovilRutaControl {

	public static final String MOVIL = "MOVIL";
	public static final String PLACA = "PLACA";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_movil_rutacontrol")
	private Long id;

	@Column(nullable = false)
	@NotNull
	private Long idArchivo;

	@Column(length = 20, nullable = false)
	@NotNull
	private String movil;

	@Column(length = 20, nullable = false)
	@NotNull
	private String placa;

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
