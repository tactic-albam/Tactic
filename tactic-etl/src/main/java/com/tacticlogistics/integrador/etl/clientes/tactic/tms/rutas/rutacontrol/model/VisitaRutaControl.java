package com.tacticlogistics.integrador.etl.clientes.tactic.tms.rutas.rutacontrol.model;

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
@Table(catalog = "tms", name = "visitas_rutacontrol")
@Data
@Builder
public class VisitaRutaControl {

	public static final String CLIENTE_IDENTIFICACION = "CLIENTE_IDENTIFICACION";
	public static final String NUMERO_ORDEN = "NUMERO_ORDEN";
	public static final String PLACA = "PLACA";
	public static final String ESTADO = "ESTADO";
	public static final String FECHA_HORA_INICIO_VISITA = "FECHA_HORA_INICIO_VISITA";
	public static final String FECHA_HORA_FIN_VISITA = "FECHA_HORA_FIN_VISITA";
	public static final String CAUSAL = "CAUSAL";
	public static final String VALOR_RECAUDADO = "VALOR_RECAUDADO";
	public static final String OBSERVACION = "OBSERVACION";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_visita_rutacontrol")
	private Long id;

	@Column(nullable = false)
	@NotNull
	private Long idArchivo;

	@Column(length = 20, nullable = false)
	@NotNull
	private String clienteIdentificacion;

	@Column(length = 20, nullable = false)
	@NotNull
	private String numeroOrden;

	@Column(length = 20, nullable = false)
	@NotNull
	private String placa;

	@Column(length = 50, nullable = false)
	@NotNull
	private String estado;

	@Column(nullable = false)
	private LocalDateTime fechaHoraInicioVisita;

	@Column(nullable = false)
	private LocalDateTime fechaHoraFinVisita;

	@Column(length = 50, nullable = false)
	@NotNull
	private String causal;

	private Integer valorRecaudado;

	@Column(length = 200, nullable = false)
	@NotNull
	private String observaciones;

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
