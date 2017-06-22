package com.tacticlogistics.integrador.model.wms.conciliaciones;

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
@Table(catalog = "wms", name = "saldos_cliente")
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SaldoCliente extends Registro {

	private static final long serialVersionUID = 1L;

	public static final String CLIENTE_CODIGO = "CLIENTE_CODIGO";
	public static final String FECHA = "FECHA";
	public static final String PRODUCTO_CODIGO = "PRODUCTO_CODIGO";
	public static final String CANTIDAD = "CANTIDAD";
	public static final String VALOR = "VALOR";
	public static final String BODEGA_CODIGO = "BODEGA_CODIGO";
	public static final String BODEGA_NOMBRE = "BODEGA_NOMBRE";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 20, nullable = false)
	@NotNull
	private String clienteCodigo;

	@Column(nullable = true)
	private LocalDateTime fecha;

	@Column(length = 50, nullable = false)
	@NotNull
	private String productoCodigo;

	private int cantidad;

	private int valor;

	@Column(length = 50, nullable = false)
	@NotNull
	private String bodegaCodigo;

	@Column(length = 50, nullable = false)
	@NotNull
	private String bodegaNombre;

	@Builder
	public SaldoCliente(
			Long id, 

			String clienteCodigo,
			LocalDateTime fecha,
			String productoCodigo,
			int cantidad,
			int valor,
			String bodegaCodigo,
			String bodegaNombre,

			Long idArchivo, EstadoRegistroType estadoRegistro, int numeroLinea, String linea, 
			LocalDateTime fechaCreacion, String usuarioCreacion, 
			LocalDateTime fechaActualizacion, String usuarioActualizacion) {
		super(idArchivo, estadoRegistro, numeroLinea, linea, fechaCreacion, usuarioCreacion, fechaActualizacion, usuarioActualizacion);
		this.id = id;
		this.clienteCodigo = clienteCodigo;
		this.fecha = fecha;
		this.productoCodigo = productoCodigo;
		this.cantidad = cantidad;
		this.valor = valor;
		this.bodegaCodigo = bodegaCodigo;
		this.bodegaNombre = bodegaNombre;
	}
}
