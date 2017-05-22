package com.tacticlogistics.integrador.model.tms.cumplidos;

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
@Table(catalog = "tms", name = "cumplidos_digitales")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CumplidoDigital extends Registro {

	private static final long serialVersionUID = 1L;

	public static final String NOMBRE_ARCHIVO = "NOMBRE_ARCHIVO";
	public static final String CLIENTE_NOMBRE = "CLIENTE_NOMBRE";
	public static final String NUMERO_ORDEN = "NUMERO_ORDEN";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cumplido_digital")
	private Long id;

	@Column(length = 300, nullable = false)
	@NotNull
	private String nombreArchivo;

	@Column(length = 100, nullable = false)
	@NotNull
	private String clienteNombre;

	@Column(length = 300, nullable = false)
	@NotNull
	private String numeroOrden;
	
	private Long idCliente;

	@Builder
	public CumplidoDigital(Long id, String nombreArchivo, String clienteNombre, String numeroOrden, 
			Long idCliente,
			Long idArchivo, EstadoRegistroType estadoRegistro, int numeroLinea, String linea, 
			LocalDateTime fechaCreacion, String usuarioCreacion, 
			LocalDateTime fechaActualizacion, String usuarioActualizacion) {
		super(idArchivo, estadoRegistro, numeroLinea, linea, fechaCreacion, usuarioCreacion, fechaActualizacion, usuarioActualizacion);
		this.id = id;
		this.nombreArchivo = nombreArchivo;
		this.clienteNombre = clienteNombre;
		this.numeroOrden = numeroOrden;
		this.idCliente = idCliente;
	}


	
}
