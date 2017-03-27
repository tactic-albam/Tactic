package com.tacticlogistics.integrador.etl.handlers.heinz.salidas.model;

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
@Table(catalog = "heinz", name = "salidas_cadenas")
@Data
@Builder
public class SalidaCadena {

	public static final String NUMERO_ORDEN = "NUMERO_ORDEN";
	public static final String FEMI = "FEMI";
	public static final String FEMA = "FEMA";
	public static final String TERCERO_IDENTIFICACION = "TERCERO_IDENTIFICACION";
	public static final String TERCERO_NOMBRE = "TERCERO_NOMBRE";
	public static final String DIRECCION = "DIRECCION";
	public static final String PUNTO_NOMBRE = "PUNTO_NOMBRE";
	public static final String PRODUCTO_CODIGO = "PRODUCTO_CODIGO";
	public static final String PRODUCTO_NOMBRE = "PRODUCTO_NOMBRE";
	public static final String PRODUCTO_EAN_TERCERO = "PRODUCTO_EAN_TERCERO";
	public static final String PRODUCTO_PLU_TERCERO = "PRODUCTO_PLU_TERCERO";
	public static final String CANTIDAD = "CANTIDAD";
	public static final String VALOR_DECLARADO = "VALOR_DECLARADO";
	public static final String CANTIDAD_PUNTO_VENTA = "CANTIDAD_PUNTO_VENTA";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_salida_cadena")
	private Long id;

	@Column(nullable = false)
	@NotNull
	private Long idArchivo;

	@Column(length = 20, nullable = false)
	@NotNull
	private String numeroOrden;

	@Column(nullable = false)
	private LocalDateTime femi;

	@Column(nullable = false)
	private LocalDateTime fema;

	@Column(length = 20, nullable = false)
	@NotNull
	private String terceroIdentificacion;

	@Column(length = 100, nullable = false)
	@NotNull
	private String terceroNombre;

	@Column(length = 150, nullable = false)
	@NotNull
	private String direccion;

	@Column(length = 100, nullable = false)
	@NotNull
	private String puntoNombre;

	@Column(length = 50, nullable = false)
	@NotNull
	private String productoCodigo;

	@Column(length = 50, nullable = false)
	@NotNull
	private String productoNombre;

	@Column(length = 50, nullable = false)
	@NotNull
	private String productoEanTercero;

	@Column(length = 50, nullable = false)
	@NotNull
	private String productoPluTercero;

	private Integer cantidad;

	@NotNull
	private BigDecimal valorDeclarado;
	
	private Integer cantidadPuntoVenta;
	
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
