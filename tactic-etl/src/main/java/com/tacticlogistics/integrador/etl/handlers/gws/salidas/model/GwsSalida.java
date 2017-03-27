package com.tacticlogistics.integrador.etl.handlers.gws.salidas.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.tacticlogistics.integrador.etl.handlers.EtlEntidadBase;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(catalog = "gws", name = "salidas")
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class GwsSalida extends EtlEntidadBase {

	private static final long serialVersionUID = 1L;

	public static final String CLIENTE_CODIGO = "CLIENTE_CODIGO";
	public static final String SERVICIO_CODIGO = "SERVICIO_CODIGO";
	public static final String NUMERO_ORDEN = "NUMERO_ORDEN";

	public static final String CANAL_CODIGO = "CANAL_CODIGO";
	public static final String TERCERO_IDENTIFICACION = "TERCERO_IDENTIFICACION";
	public static final String TERCERO_NOMBRE = "TERCERO_NOMBRE";

	public static final String CIUDAD_CODIGO = "CIUDAD_CODIGO";
	public static final String DIRECCION = "DIRECCION";
	public static final String PUNTO_CODIGO = "PUNTO_CODIGO";
	public static final String PUNTO_NOMBRE = "PUNTO_NOMBRE";

	public static final String FEMI = "FEMI";
	public static final String FEMA = "FEMA";
	public static final String HOMI = "HOMI";
	public static final String HOMA = "HOMA";

	public static final String CONTACTO_NOMBRES = "CONTACTO_NOMBRES";
	public static final String CONTACTO_TELEFONOS = "CONTACTO_TELEFONOS";
	public static final String CONTACTO_EMAIL = "CONTACTO_EMAIL";
	public static final String NOTAS = "NOTAS";

	public static final String REQUIERE_TRANSPORTE = "REQUIERE_TRANSPORTE";
	public static final String AUTORIZADO_IDENTIFICACION = "AUTORIZADO_IDENTIFICACION";
	public static final String AUTORIZADO_NOMBRES = "AUTORIZADO_NOMBRES";

	public static final String REQUIERE_RECAUDO = "REQUIERE_RECAUDO";
	public static final String VALOR_RECAUDAR = "VALOR_RECAUDAR";

	public static final String REQUIERE_AGENDAMIENTO = "REQUIERE_AGENDAMIENTO";

	public static final String PRODUCTO_CODIGO = "PRODUCTO_CODIGO";
	public static final String PRODUCTO_NOMBRE = "PRODUCTO_NOMBRE";
	public static final String CANTIDAD = "CANTIDAD";
	public static final String BODEGA_ORIGEN_CODIGO = "BODEGA_ORIGEN_CODIGO";
	public static final String BODEGA_DESTINO_CODIGO = "BODEGA_DESTINO_CODIGO";
	public static final String VALOR_UNITARIO_DECLARADO = "VALOR_UNITARIO_DECLARADO";
	public static final String LOTE = "LOTE";
	public static final String PREDISTRIBUCION_CROSSDOCK = "PREDISTRIBUCION_CROSSDOCK";
	public static final String PREDISTRIBUCION_EVENTO = "PREDISTRIBUCION_EVENTO";

	public static final String PREFIJO_ORDEN = "PREFIJO_ORDEN";
	public static final String NUMERO_ORDEN_SIN_PREFIJO = "NUMERO_ORDEN_SIN_PREFIJO";
	public static final String FECHA_ORDEN = "FECHA_ORDEN";

	public static final String DOCUMENTO_CLIENTE = "DOCUMENTO_CLIENTE";
	public static final String FECHA_DOCUMENTO_CLIENTE = "FECHA_DOCUMENTO_CLIENTE";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_salida")
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
