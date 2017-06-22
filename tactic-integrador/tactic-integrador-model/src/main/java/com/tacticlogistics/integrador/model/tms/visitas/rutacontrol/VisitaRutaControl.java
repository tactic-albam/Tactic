package com.tacticlogistics.integrador.model.tms.visitas.rutacontrol;

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

import org.hibernate.validator.constraints.NotEmpty;

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
	public static final String RESPONSABLE_DIRECTO = "RESPONSABLE_DIRECTO";
	public static final String RESPONSABLE_INTERNO = "RESPONSABLE_INTERNO";
	public static final String FECHA_REPROGRAMACION = "FECHA_REPROGRAMACION";
	public static final String HOMI_REPROGRAMACION = "HOMI_REPROGRAMACION";
	public static final String HOMA_REPROGRAMACION = "HOMA_REPROGRAMACION";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 20, nullable = false)
	@NotEmpty
	private String clienteIdentificacion;

	@Column(length = 20, nullable = false)
	@NotEmpty
	private String numeroOrden;

	@Column(length = 20, nullable = false)
	@NotEmpty
	private String placa;

	@Column(length = 50, nullable = false)
	@NotEmpty
	private String estado;

	private LocalDateTime fechaHoraInicioVisita;

	private LocalDateTime fechaHoraFinVisita;

	@Column(length = 50, nullable = false)
	@NotNull
	private String causal;

	private Integer valorRecaudado;

	@Column(length = 1000, nullable = false)
	@NotNull
	private String observaciones;

	@Column(length = 50, nullable = false)
	@NotNull
	private String responsableDirecto;

	@Column(length = 50, nullable = false)
	@NotNull
	private String responsableInterno;

	private LocalDate fechaReprogramacion;

	private LocalTime homiReprogramacion;

	private LocalTime homaReprogramacion;

	private Long idCliente;

	private Long idOrden;

	@Column(length = 50)
	private String idEstadoTransporte;
	
	private Long idCausalNovedadTransporte;
	
	private Long idResponsableDirecto;
	
	private Long idResponsableInterno;

	// @formatter:off
	@Builder
	public VisitaRutaControl(
			Long id, String clienteIdentificacion, String numeroOrden, String placa, 
			String estado,
			LocalDateTime fechaHoraInicioVisita, LocalDateTime fechaHoraFinVisita, 
			String causal, Integer valorRecaudado, String observaciones, 
			String responsableDirecto, String responsableInterno,
			LocalDate fechaReprogramacion, LocalTime homiReprogramacion, LocalTime homaReprogramacion,
			Long idCliente, 
			Long idOrden, 
			String idEstadoTransporte, 
			Long idCausalNovedadTransporte,
			Long idResponsableDirecto, 
			Long idResponsableInterno,
			
			Long idArchivo, EstadoRegistroType estadoRegistro, int numeroLinea, String linea, 
			LocalDateTime fechaCreacion, String usuarioCreacion, LocalDateTime fechaActualizacion,
			String usuarioActualizacion) {
		super(idArchivo, estadoRegistro, numeroLinea, linea, fechaCreacion, usuarioCreacion, fechaActualizacion, usuarioActualizacion);
		// @formatter:on
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
		this.responsableDirecto = responsableDirecto;
		this.responsableInterno = responsableInterno;
		this.fechaReprogramacion = fechaReprogramacion;
		this.homiReprogramacion = homiReprogramacion;
		this.homaReprogramacion = homaReprogramacion;
		this.idCliente = idCliente;
		this.idOrden = idOrden;
		this.idEstadoTransporte = idEstadoTransporte;
		this.idCausalNovedadTransporte = idCausalNovedadTransporte;
		this.idResponsableDirecto = idResponsableDirecto;
		this.idResponsableInterno = idResponsableInterno;
	}
}
