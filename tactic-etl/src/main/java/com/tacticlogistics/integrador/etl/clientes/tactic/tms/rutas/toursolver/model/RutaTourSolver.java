package com.tacticlogistics.integrador.etl.clientes.tactic.tms.rutas.toursolver.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

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
@Table(catalog = "tms", name = "rutas_toursolver")
@Data
@Builder
public class RutaTourSolver {

	public static final String CLIENTE_CODIGO = "CLIENTE_CODIGO";
	public static final String ID_ORDEN = "ID_ORDEN";
	public static final String PLACA = "PLACA";
	public static final String SECUENCIA = "SECUENCIA";
	public static final String FECHA_ESTIMADA_ENTREGA = "FECHA_ESTIMADA_ENTREGA";
	public static final String HORA_ESTIMADA_ENTREGA = "HORA_ESTIMADA_ENTREGA";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_ruta_toursolver")
	private Long id;
	
	@Column(nullable = false)
	@NotNull
	private Long idArchivo;
	
	@Column(length = 50, nullable = false)
	@NotNull
	private String clienteCodigo;

	private long idOrden;
	
	@Column(length = 20, nullable = false)
	@NotNull
	private String placa;

	private int secuencia;
	
	@Column(nullable = false)
	@NotNull
    private LocalDate fechaEstimadaEntrega;
	
	@Column(nullable = false)
	@NotNull
	private LocalTime horaEstimadaEntrega; 

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
