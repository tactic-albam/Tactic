package com.tacticlogistics.integrador.model.oms;

import java.math.BigDecimal;
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
@Table(catalog = "oms", name = "recibos")
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Recibo extends Registro {

	private static final long serialVersionUID = 1L;

	public static final String CLIENTE_CODIGO = "CLIENTE_CODIGO";
	public static final String SERVICIO_CODIGO = "SERVICIO_CODIGO";
	public static final String NUMERO_ORDEN = "NUMERO_ORDEN";

	public static final String CANAL_CODIGO = "CANAL_CODIGO";
	public static final String TERCERO_IDENTIFICACION = "TERCERO_IDENTIFICACION";
	public static final String TERCERO_SUCURSAL = "TERCERO_SUCURSAL";
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

	public static final String REQUIERE_AGENDAMIENTO = "REQUIERE_AGENDAMIENTO";

	public static final String PRODUCTO_CODIGO = "PRODUCTO_CODIGO";
	public static final String PRODUCTO_CODIGO_ALTERNO = "PRODUCTO_CODIGO_ALTERNO";
	public static final String PRODUCTO_NOMBRE = "PRODUCTO_NOMBRE";
	public static final String CANTIDAD = "CANTIDAD";
	public static final String BODEGA_DESTINO_CODIGO = "BODEGA_DESTINO_CODIGO";
	public static final String ESTADO_INVENTARIO_DESTINO_CODIGO = "ESTADO_INVENTARIO_DESTINO_CODIGO";
	public static final String VALOR_UNITARIO_DECLARADO = "VALOR_UNITARIO_DECLARADO";
	public static final String LOTE = "LOTE";
	public static final String BL = "BL";
	public static final String CONTENEDOR = "CONTENEDOR";
	public static final String PREDISTRIBUCION_CROSSDOCK = "PREDISTRIBUCION_CROSSDOCK";
	public static final String PREDISTRIBUCION_EVENTO = "PREDISTRIBUCION_EVENTO";

	public static final String PREFIJO_ORDEN = "PREFIJO_ORDEN";
	public static final String NUMERO_ORDEN_SIN_PREFIJO = "NUMERO_ORDEN_SIN_PREFIJO";
	public static final String FECHA_ORDEN = "FECHA_ORDEN";

	public static final String DOCUMENTO_CLIENTE = "DOCUMENTO_CLIENTE";
	public static final String FECHA_DOCUMENTO_CLIENTE = "FECHA_DOCUMENTO_CLIENTE";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_recibo")
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

	@Column(length = 50, nullable = false)
	@NotNull
	private String canalCodigo;

	@Column(length = 20, nullable = false)
	@NotNull
	private String terceroIdentificacion;

	@Column(length = 50, nullable = false)
	@NotNull
	private String terceroSucursal;

	@Column(length = 100, nullable = false)
	@NotNull
	private String terceroNombre;

	@Column(length = 50, nullable = false)
	@NotNull
	private String ciudadCodigo;

	@Column(length = 150, nullable = false)
	@NotNull
	private String direccion;

	@Column(length = 50, nullable = false)
	@NotNull
	private String puntoCodigo;

	@Column(length = 100, nullable = false)
	@NotNull
	private String puntoNombre;

	private LocalDate femi;
	private LocalDate fema;
	private LocalTime homi;
	private LocalTime homa;

	@Column(length = 100, nullable = false)
	@NotNull
	private String contactoNombres;

	@Column(length = 50, nullable = false)
	@NotNull
	private String contactoTelefonos;

	@Column(length = 100, nullable = false)
	@NotNull
	private String contactoEmail;

	@Column(length = 200, nullable = false)
	@NotNull
	private String notas;

	@Column(length = 1, nullable = false)
	@NotEmpty
	private String requiereTransporte;

	@Column(length = 1, nullable = false)
	@NotNull
	private String requiereAgendamiento;

	@Column(length = 50, nullable = false)
	@NotNull
	private String productoCodigo;

	@Column(length = 50, nullable = false)
	@NotNull
	private String productoCodigoAlterno;

	@Column(length = 200, nullable = false)
	@NotNull
	private String productoNombre;

	private int cantidad;

	@Column(length = 50, nullable = false)
	@NotEmpty
	private String bodegaDestinoCodigo;

	@Column(length = 50, nullable = false)
	@NotNull
	private String estadoInventarioDestinoCodigo;

	private BigDecimal valorUnitarioDeclarado;

	@Column(length = 30, nullable = false)
	@NotNull
	private String lote;

	@Column(length = 50, nullable = false)
	@NotNull
	private String bl;

	@Column(length = 50, nullable = false)
	@NotNull
	private String contenedor;

	@Column(length = 200, nullable = false)
	@NotNull
	private String predistribucionCrossdock;

	@Column(length = 200, nullable = false)
	@NotNull
	private String predistribucionEvento;

	@Column(length = 20, nullable = false)
	@NotNull
	private String prefijoOrden;

	@Column(length = 20, nullable = false)
	@NotNull
	private String numeroOrdenSinPrefijo;

	private LocalDate fechaOrden;

	@Column(length = 20, nullable = false)
	@NotNull
	private String documentoCliente;
	private LocalDate fechaDocumentoCliente;

	private Long idCliente;
	private Long idServicio;
	private Long idCanal;
	private Long idCiudad;
	private Long idBodegaDestino;
	@Column(name = "id_estado_inventario_destino", length = 50)
	private String idEstadoInventarioDestino;
	
	@Builder
	public Recibo(Long id, String clienteCodigo, String servicioCodigo, String numeroOrden, String canalCodigo,
			String terceroIdentificacion, String terceroSucursal, String terceroNombre, String ciudadCodigo,
			String direccion, String puntoCodigo, String puntoNombre, LocalDate femi, LocalDate fema, LocalTime homi,
			LocalTime homa, String contactoNombres, String contactoTelefonos, String contactoEmail, String notas,
			String requiereTransporte, String requiereAgendamiento, String productoCodigo, String productoCodigoAlterno,
			String productoNombre, int cantidad, String bodegaDestinoCodigo, String estadoInventarioDestinoCodigo,
			BigDecimal valorUnitarioDeclarado, String lote, String bl, String contenedor,
			String predistribucionCrossdock, String predistribucionEvento, String prefijoOrden,
			String numeroOrdenSinPrefijo, LocalDate fechaOrden, String documentoCliente,
			LocalDate fechaDocumentoCliente, Long idCliente, Long idServicio, Long idCanal, Long idCiudad,
			Long idBodegaDestino, String idEstadoInventarioDestino,
			Long idArchivo, EstadoRegistroType estadoRegistro, int numeroLinea, String linea, 
			LocalDateTime fechaCreacion, String usuarioCreacion, LocalDateTime fechaActualizacion,
			String usuarioActualizacion) {
		super(idArchivo, estadoRegistro, numeroLinea, linea, fechaCreacion, usuarioCreacion, fechaActualizacion, usuarioActualizacion);
		this.id = id;
		this.clienteCodigo = clienteCodigo;
		this.servicioCodigo = servicioCodigo;
		this.numeroOrden = numeroOrden;
		this.canalCodigo = canalCodigo;
		this.terceroIdentificacion = terceroIdentificacion;
		this.terceroSucursal = terceroSucursal;
		this.terceroNombre = terceroNombre;
		this.ciudadCodigo = ciudadCodigo;
		this.direccion = direccion;
		this.puntoCodigo = puntoCodigo;
		this.puntoNombre = puntoNombre;
		this.femi = femi;
		this.fema = fema;
		this.homi = homi;
		this.homa = homa;
		this.contactoNombres = contactoNombres;
		this.contactoTelefonos = contactoTelefonos;
		this.contactoEmail = contactoEmail;
		this.notas = notas;
		this.requiereTransporte = requiereTransporte;
		this.requiereAgendamiento = requiereAgendamiento;
		this.productoCodigo = productoCodigo;
		this.productoCodigoAlterno = productoCodigoAlterno;
		this.productoNombre = productoNombre;
		this.cantidad = cantidad;
		this.bodegaDestinoCodigo = bodegaDestinoCodigo;
		this.estadoInventarioDestinoCodigo = estadoInventarioDestinoCodigo;
		this.valorUnitarioDeclarado = valorUnitarioDeclarado;
		this.lote = lote;
		this.bl = bl;
		this.contenedor = contenedor;
		this.predistribucionCrossdock = predistribucionCrossdock;
		this.predistribucionEvento = predistribucionEvento;
		this.prefijoOrden = prefijoOrden;
		this.numeroOrdenSinPrefijo = numeroOrdenSinPrefijo;
		this.fechaOrden = fechaOrden;
		this.documentoCliente = documentoCliente;
		this.fechaDocumentoCliente = fechaDocumentoCliente;
		this.idCliente = idCliente;
		this.idServicio = idServicio;
		this.idCanal = idCanal;
		this.idCiudad = idCiudad;
		this.idBodegaDestino = idBodegaDestino;
		this.idEstadoInventarioDestino = idEstadoInventarioDestino;
	}
}