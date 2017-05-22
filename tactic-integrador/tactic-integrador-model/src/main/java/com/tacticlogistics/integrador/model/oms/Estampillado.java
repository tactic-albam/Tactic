package com.tacticlogistics.integrador.model.oms;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
@Table(catalog = "oms", name = "estampillados")
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Estampillado extends Registro {

	private static final long serialVersionUID = 1L;

	public static final String CLIENTE_CODIGO = "CLIENTE_CODIGO";
	public static final String SERVICIO_CODIGO = "SERVICIO_CODIGO";
	public static final String NUMERO_ORDEN = "NUMERO_ORDEN";

	public static final String FEMI = "FEMI";
	public static final String FEMA = "FEMA";
	public static final String PRODUCTO_CODIGO = "PRODUCTO_CODIGO";
	public static final String PRODUCTO_NOMBRE = "PRODUCTO_NOMBRE";
	public static final String CANTIDAD = "CANTIDAD";

	public static final String BODEGA_CODIGO = "BODEGA_CODIGO";
	public static final String ESTADO_INVENTARIO_CODIGO = "ESTADO_INVENTARIO_CODIGO";

	public static final String DOCUMENTO_CLIENTE = "DOCUMENTO_CLIENTE";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_estampillado")
	private Long id;

	@Column(length = 20, nullable = false)
	@NotEmpty
	private String clienteCodigo;

	@Column(length = 50, nullable = false)
	@NotEmpty
	private String servicioCodigo;

	@Column(length = 20, nullable = false)
	@NotEmpty
	private String numeroOrden;

	private LocalDate femi;
	private LocalDate fema;

	@Column(length = 50, nullable = false)
	@NotNull
	private String productoCodigo;

	@Column(length = 200, nullable = false)
	@NotNull
	private String productoNombre;

	private int cantidad;

	@Column(length = 50, nullable = false)
	@NotEmpty
	private String bodegaCodigo;

	@Column(length = 50, nullable = false)
	@NotNull
	private String estadoInventarioCodigo;

	@Column(length = 20, nullable = false)
	@NotNull
	private String documentoCliente;

	private Long idCliente;
	private Long idServicio;
	private Long idProducto;
	private Long idBodega;
	@Column(length = 50)
	private String idEstadoInventario;
	
	@Builder
	public Estampillado(Long id, String clienteCodigo, String servicioCodigo, String numeroOrden, LocalDate femi,
			LocalDate fema, String productoCodigo, String productoNombre, int cantidad, String bodegaCodigo,
			String estadoInventarioCodigo, String documentoCliente, Long idCliente, Long idServicio, Long idProducto,
			Long idBodega, String idEstadoInventario,
			Long idArchivo, EstadoRegistroType estadoRegistro, int numeroLinea, String linea, 
			LocalDateTime fechaCreacion, String usuarioCreacion, LocalDateTime fechaActualizacion,
			String usuarioActualizacion) {
		super(idArchivo, estadoRegistro, numeroLinea, linea, fechaCreacion, usuarioCreacion, fechaActualizacion, usuarioActualizacion);
		this.id = id;
		this.clienteCodigo = clienteCodigo;
		this.servicioCodigo = servicioCodigo;
		this.numeroOrden = numeroOrden;
		this.femi = femi;
		this.fema = fema;
		this.productoCodigo = productoCodigo;
		this.productoNombre = productoNombre;
		this.cantidad = cantidad;
		this.bodegaCodigo = bodegaCodigo;
		this.estadoInventarioCodigo = estadoInventarioCodigo;
		this.documentoCliente = documentoCliente;
		this.idCliente = idCliente;
		this.idServicio = idServicio;
		this.idProducto = idProducto;
		this.idBodega = idBodega;
		this.idEstadoInventario = idEstadoInventario;
	}
}