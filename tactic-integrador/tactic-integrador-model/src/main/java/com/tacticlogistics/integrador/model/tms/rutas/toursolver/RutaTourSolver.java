package com.tacticlogistics.integrador.model.tms.rutas.toursolver;

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

import com.tacticlogistics.integrador.model.etl.registro.EstadoRegistroType;
import com.tacticlogistics.integrador.model.etl.registro.Registro;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(catalog = "tms", name = "rutas_toursolver")
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RutaTourSolver extends Registro {

	private static final long serialVersionUID = 1L;

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

	@Builder
	public RutaTourSolver(Long id, String clienteCodigo, long idOrden, String placa, int secuencia,
			LocalDate fechaEstimadaEntrega, LocalTime horaEstimadaEntrega,
			Long idArchivo, EstadoRegistroType estadoRegistro, int numeroLinea, String linea, 
			LocalDateTime fechaCreacion, String usuarioCreacion, 
			LocalDateTime fechaActualizacion, String usuarioActualizacion) {
		super(idArchivo, estadoRegistro, numeroLinea, linea, fechaCreacion, usuarioCreacion, fechaActualizacion, usuarioActualizacion);
		this.id = id;
		this.clienteCodigo = clienteCodigo;
		this.idOrden = idOrden;
		this.placa = placa;
		this.secuencia = secuencia;
		this.fechaEstimadaEntrega = fechaEstimadaEntrega;
		this.horaEstimadaEntrega = horaEstimadaEntrega;
	}
}
