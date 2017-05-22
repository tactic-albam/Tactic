package com.tacticlogistics.integrador.model.tms.rutas.rutacontrol;

import java.time.LocalDateTime;

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
@Table(catalog = "tms", name = "visitas_rutacontrol")
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class VisitaRutaControl extends Registro {

	private static final long serialVersionUID = 1L;

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

	@Column(length = 1000, nullable = false)
	@NotNull
	private String observaciones;

	private Integer idOrden;

	//@Enumerated(EnumType.STRING)
	@Column(name = "id_estado_transporte", length = 50)
	private String estadoTransporte;
	
	private Integer idCausalNovedadTransporte;

	@Builder
	public VisitaRutaControl(Long id, String clienteIdentificacion, String numeroOrden, String placa, String estado,
			LocalDateTime fechaHoraInicioVisita, LocalDateTime fechaHoraFinVisita, String causal,
			Integer valorRecaudado, String observaciones, Integer idOrden, String estadoTransporte,
			Integer idCausalNovedadTransporte,
			Long idArchivo, EstadoRegistroType estadoRegistro, int numeroLinea, String linea, 
			LocalDateTime fechaCreacion, String usuarioCreacion, 
			LocalDateTime fechaActualizacion, String usuarioActualizacion) {
		super(idArchivo, estadoRegistro, numeroLinea, linea, fechaCreacion, usuarioCreacion, fechaActualizacion, usuarioActualizacion);
		this.id = id;
		this.clienteIdentificacion = clienteIdentificacion;
		this.numeroOrden = numeroOrden;
		this.placa = placa;
		this.estado = estado;
		this.fechaHoraInicioVisita = fechaHoraInicioVisita;
		this.fechaHoraFinVisita = fechaHoraFinVisita;
		this.causal = causal;
		this.valorRecaudado = valorRecaudado;
		this.observaciones = observaciones;
		this.idOrden = idOrden;
		this.estadoTransporte = estadoTransporte;
		this.idCausalNovedadTransporte = idCausalNovedadTransporte;
	}
}
