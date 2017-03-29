package com.tacticlogistics.integrador.etl.clientes.dicermex.model;

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

import com.tacticlogistics.integrador.etl.model.oms.EtlEntidadBase;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(catalog = "dicermex", name = "salidas")
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DicermexSalida extends EtlEntidadBase {

	private static final long serialVersionUID = 1L;

	public static final String CLIENTE_CODIGO = "CLIENTE_CODIGO";
	public static final String SERVICIO_CODIGO = "SERVICIO_CODIGO";
	public static final String PREFIJO_ORDEN = "PREFIJO_ORDEN";
	public static final String NUMERO_ORDEN = "NUMERO_ORDEN";

	public static final String FEMI = "FEMI";
	public static final String FEMA = "FEMA";
	public static final String HOMI = "HOMI";
	public static final String HOMA = "HOMA";

	public static final String CANAL_CODIGO = "CANAL_CODIGO";
	public static final String TERCERO_IDENTIFICACION = "TERCERO_IDENTIFICACION";
	public static final String TERCERO_NOMBRE = "TERCERO_NOMBRE";

	public static final String CIUDAD_CODIGO = "CIUDAD_CODIGO";
	public static final String DIRECCION = "DIRECCION";
	public static final String PUNTO_NOMBRE = "PUNTO_NOMBRE";

	public static final String TELEFONO_CONTACTO = "TELEFONO_CONTACTO";
	public static final String VALOR_RECAUDAR = "VALOR_RECAUDAR";
	public static final String NOTAS = "NOTAS";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_salida")
	private Long id;

	@Column(length = 50, nullable = false)
	@NotNull
	private String servicioCodigo;

	@Column(length = 3, nullable = false)
	@NotNull
	private String prefijoOrden;

	@Column(length = 20, nullable = false)
	@NotNull
	private String numeroOrden;

	@Column(nullable = true)
	private LocalDate femi;

	@Column(nullable = true)
	private LocalDate fema;

	@Column(nullable = true)
	private LocalTime homi;

	@Column(nullable = true)
	private LocalTime homa;

	@Column(length = 50, nullable = false)
	@NotNull
	private String canalCodigo;

	@Column(length = 20, nullable = false)
	@NotNull
	private String terceroIdentificacion;

	@Column(length = 100, nullable = false)
	@NotNull
	private String terceroNombre;

	@Column(length = 50, nullable = false)
	@NotNull
	private String ciudadCodigo;

	@Column(length = 150, nullable = false)
	@NotNull
	private String direccion;

	@Column(length = 100, nullable = false)
	@NotNull
	private String puntoNombre;

	@Column(length = 50, nullable = false)
	@NotNull
	private String telefonoContacto;

	private int valorRecaudar;

	@Column(length = 200, nullable = false)
	@NotNull
	private String notas;

	@Builder
	public DicermexSalida(Long id, String servicioCodigo, String prefijoOrden, String numeroOrden, LocalDate femi,
			LocalDate fema, LocalTime homi, LocalTime homa, String canalCodigo, String terceroIdentificacion,
			String terceroNombre, String ciudadCodigo, String direccion, String puntoNombre, String telefonoContacto,
			int valorRecaudar, String notas, Long idArchivo, String linea, LocalDateTime fechaCreacion,
			String usuarioCreacion) {
		super(idArchivo, linea, fechaCreacion, usuarioCreacion);
		this.id = id;
		this.servicioCodigo = servicioCodigo;
		this.prefijoOrden = prefijoOrden;
		this.numeroOrden = numeroOrden;
		this.femi = femi;
		this.fema = fema;
		this.homi = homi;
		this.homa = homa;
		this.canalCodigo = canalCodigo;
		this.terceroIdentificacion = terceroIdentificacion;
		this.terceroNombre = terceroNombre;
		this.ciudadCodigo = ciudadCodigo;
		this.direccion = direccion;
		this.puntoNombre = puntoNombre;
		this.telefonoContacto = telefonoContacto;
		this.valorRecaudar = valorRecaudar;
		this.notas = notas;
	}
}
