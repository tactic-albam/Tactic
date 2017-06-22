package com.tacticlogistics.integrador.model.tms.rutas.manuales;

import java.math.BigDecimal;
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
import lombok.ToString;

@Entity
@Table(catalog = "tms", name = "rutas_manuales")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RutaManual extends Registro {

	private static final long serialVersionUID = 1L;

	public static final String CLIENTE_IDENTIFICACION = "CLIENTE_IDENTIFICACION";
	public static final String NUMERO_ORDEN = "NUMERO_ORDEN";
	public static final String PLACA = "PLACA";
	public static final String CX = "CX";
	public static final String CY = "CY";
	public static final String FECHA_HORA_ESTIMADA_ENTREGA = "FECHA_HORA_ESTIMADA_ENTREGA";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	@Column(nullable = false)
	@NotNull
    private LocalDateTime fechaHoraEstimadaEntrega;
	
	@Column(nullable = false)
	@NotNull
	private BigDecimal cx; 

	@Column(nullable = false)
	@NotNull
	private BigDecimal cy; 

	private Long idOrden;

	@Builder
	public RutaManual(
			Long id, String clienteIdentificacion, String numeroOrden, String placa, 
			LocalDateTime fechaHoraEstimadaEntrega, BigDecimal cx, BigDecimal cy, 
			Long idOrden,
			Long idArchivo, EstadoRegistroType estadoRegistro, int numeroLinea, String linea, 
			LocalDateTime fechaCreacion, String usuarioCreacion, 
			LocalDateTime fechaActualizacion, String usuarioActualizacion) {
		super(idArchivo, estadoRegistro, numeroLinea, linea, fechaCreacion, usuarioCreacion, fechaActualizacion, usuarioActualizacion);
		this.id = id;
		this.clienteIdentificacion = clienteIdentificacion;
		this.numeroOrden = numeroOrden;
		this.placa = placa;
		this.fechaHoraEstimadaEntrega = fechaHoraEstimadaEntrega;
		this.cx = cx;
		this.cy = cy;
		this.idOrden = idOrden;
	}
}
