package com.tacticlogistics.integrador.etl.clientes.heinz.maestros.model;

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
@Table(catalog = "heinz", name = "productos_precios")
@Data
@Builder
public class ProductoPrecio {
	public static final String CLIENTE_CODIGO = "CLIENTE_CODIGO";
	public static final String TERCERO_IDENTIFICACION = "TERCERO_IDENTIFICACION";
	public static final String PRODUCTO_CODIGO = "PRODUCTO_CODIGO";
	public static final String PRODUCTO_NOMBRE = "PRODUCTO_NOMBRE";
	public static final String VALOR_UNITARIO = "VALOR_UNITARIO";
	public static final String REDONDEO = "REDONDEO";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_producto_precio")
	private Long id;

	@Column(nullable = false)
	@NotNull
	private Long idArchivo;

	@Column(length = 20, nullable = false)
	@NotNull
	private String clienteCodigo;

	@Column(length = 20, nullable = false)
	@NotNull
	private String terceroIdentificacion;

	@Column(length = 50, nullable = false)
	@NotNull
	private String productoCodigo;

	@Column(length = 200, nullable = false)
	@NotNull
	private String productoNombre;

	private BigDecimal valorUnitario;

	private Integer redondeo;

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
