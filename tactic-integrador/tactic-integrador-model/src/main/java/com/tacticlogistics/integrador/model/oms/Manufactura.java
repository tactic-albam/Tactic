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
@Table(catalog = "oms", name = "manufacturas")
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Manufactura extends Registro {

	private static final long serialVersionUID = 1L;

	public static final String CLIENTE_CODIGO = "CLIENTE_CODIGO";
	public static final String SERVICIO_CODIGO = "SERVICIO_CODIGO";
	public static final String NUMERO_ORDEN = "NUMERO_ORDEN";

	public static final String FEMI = "FEMI";
	public static final String FEMA = "FEMA";
	public static final String PRODUCTO_CODIGO = "PRODUCTO_CODIGO";
	public static final String PRODUCTO_NOMBRE = "PRODUCTO_NOMBRE";
	public static final String UNIDAD_CODIGO = "UNIDAD_CODIGO";
	public static final String CANTIDAD = "CANTIDAD";

	public static final String BODEGA_ORIGEN_CODIGO = "BODEGA_ORIGEN_CODIGO";
	public static final String ESTADO_INVENTARIO_ORIGEN_CODIGO = "ESTADO_INVENTARIO_ORIGEN_CODIGO";
	public static final String BODEGA_DESTINO_CODIGO = "BODEGA_DESTINO_CODIGO";
	public static final String ESTADO_INVENTARIO_DESTINO_CODIGO = "ESTADO_INVENTARIO_DESTINO_CODIGO";

	public static final String LOTE = "LOTE";
	public static final String NOTAS = "NOTAS";

	public static final String DOCUMENTO_CLIENTE = "DOCUMENTO_CLIENTE";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	@Column(length = 50, nullable = false)
	@NotNull
	private String unidadCodigo;

	private int cantidad;

	@Column(length = 50, nullable = false)
	@NotEmpty
	private String bodegaOrigenCodigo;

	@Column(length = 50, nullable = false)
	@NotNull
	private String estadoInventarioOrigenCodigo;

	@Column(length = 50, nullable = false)
	@NotNull
	private String bodegaDestinoCodigo;
	
	@Column(length = 50, nullable = false)
	@NotNull
	private String estadoInventarioDestinoCodigo;

	@Column(length = 30, nullable = false)
	@NotNull
	private String lote;

	@Column(length = 200, nullable = false)
	@NotNull
	private String notas;

	@Column(length = 20, nullable = false)
	@NotNull
	private String documentoCliente;

	private Long idCliente;
	private Long idServicio;
	private Long idProducto;
	private Long idUnidad;
	private Long idBodegaOrigen;
	@Column(name = "id_estado_inventario_origen", length = 50)
	private String idEstadoInventarioOrigen;
	private Long idBodegaDestino;
	@Column(name = "id_estado_inventario_destino", length = 50)
	private String idEstadoInventarioDestino;

	// @formatter:off
	@Builder
	public Manufactura(Long id, 
			String clienteCodigo, String servicioCodigo, String numeroOrden, 
			LocalDate femi, LocalDate fema, 
			String productoCodigo, String productoNombre, String unidadCodigo, int cantidad,  
			String bodegaOrigenCodigo, String estadoInventarioOrigenCodigo, String bodegaDestinoCodigo,String estadoInventarioDestinoCodigo,  
			String lote, String notas, String documentoCliente, 
			Long idCliente, Long idServicio, 
			Long idProducto, Long idUnidad, 
			Long idBodegaOrigen, String idEstadoInventarioOrigen, Long idBodegaDestino, String idEstadoInventarioDestino, 
			Long idArchivo, EstadoRegistroType estadoRegistro, int numeroLinea, String linea, 
			LocalDateTime fechaCreacion, String usuarioCreacion,
			LocalDateTime fechaActualizacion, String usuarioActualizacion) {
		super(idArchivo, estadoRegistro, numeroLinea, linea, fechaCreacion, usuarioCreacion, fechaActualizacion, usuarioActualizacion);
		// @formatter:on
		this.id = id;
		this.clienteCodigo = clienteCodigo;
		this.servicioCodigo = servicioCodigo;
		this.numeroOrden = numeroOrden;
		this.femi = femi;
		this.fema = fema;
		this.productoCodigo = productoCodigo;
		this.productoNombre = productoNombre;
		this.unidadCodigo = unidadCodigo;
		this.cantidad = cantidad;
		this.bodegaOrigenCodigo = bodegaOrigenCodigo;
		this.estadoInventarioOrigenCodigo = estadoInventarioOrigenCodigo;
		this.bodegaDestinoCodigo = bodegaDestinoCodigo;
		this.estadoInventarioDestinoCodigo = estadoInventarioDestinoCodigo;
		this.lote = lote;
		this.notas = notas;
		this.documentoCliente = documentoCliente;
		this.idCliente = idCliente;
		this.idServicio = idServicio;
		this.idProducto = idProducto;
		this.idUnidad = idUnidad; 
		this.idBodegaOrigen = idBodegaOrigen;
		this.idEstadoInventarioOrigen = idEstadoInventarioOrigen;
		this.idBodegaDestino = idBodegaDestino;
		this.idEstadoInventarioDestino = idEstadoInventarioDestino;
	}
}