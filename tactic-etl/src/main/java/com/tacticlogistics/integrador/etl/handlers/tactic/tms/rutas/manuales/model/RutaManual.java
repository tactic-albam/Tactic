package com.tacticlogistics.integrador.etl.handlers.tactic.tms.rutas.manuales.model;

import java.math.BigDecimal;
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
@Table(catalog = "tms", name = "rutas_manuales")
@Data
@Builder
public class RutaManual {

	public static final String CLIENTE_IDENTIFICACION = "CLIENTE_IDENTIFICACION";
	public static final String NUMERO_ORDEN = "NUMERO_ORDEN";
	public static final String PLACA = "PLACA";
	public static final String SECUENCIA = "SECUENCIA";
	public static final String CX = "CX";
	public static final String CY = "CY";
	public static final String FECHA_HORA_ESTIMADA_ENTREGA = "FECHA_HORA_ESTIMADA_ENTREGA";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_ruta_manual")
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

	private int secuencia;
	
	@Column(nullable = false)
	@NotNull
    private LocalDateTime fechaHoraEstimadaEntrega;
	
	@Column(nullable = false)
	@NotNull
	private BigDecimal cx; 

	@Column(nullable = false)
	@NotNull
	private BigDecimal cy; 

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
