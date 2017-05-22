package com.tacticlogistics.integrador.model.wms.pf;

import java.math.BigDecimal;
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
@Table(catalog = "wms", name = "pf_productos_medidas")
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ProntoFormProductoMedida extends Registro {

	private static final long serialVersionUID = 1L;

	public static final String CLIENTE_CODIGO = "CLIENTE_CODIGO";
	public static final String PRODUCTO_CODIGO = "PRODUCTO_CODIGO";
	public static final String PRODUCTO_NOMBRE = "PRODUCTO_NOMBRE";
	public static final String BODEGA_CODIGO = "BODEGA_CODIGO";
	public static final String HUELLA_CODIGO = "HUELLA_CODIGO";
	public static final String DEFAULT_FLAG = "DEFAULT_FLAG";
	public static final String CASE_LEVEL = "CASE_LEVEL";

	public static final String UNIDAD_CODIGO_1 = "UNIDAD_CODIGO1";
	public static final String FACTOR_CONVERSION_1 = "FACTOR_CONVERSION1";
	public static final String LARGO_1 = "LARGO1";
	public static final String ANCHO_1 = "ANCHO1";
	public static final String ALTO_1 = "ALTO1";
	public static final String PESO_1 = "PESO1";
	public static final String VOLUMEN_1 = "VOLUMEN1";

	public static final String UNIDAD_CODIGO_2 = "UNIDAD_CODIGO2";
	public static final String FACTOR_CONVERSION_2 = "FACTOR_CONVERSION2";
	public static final String LARGO_2 = "LARGO2";
	public static final String ANCHO_2 = "ANCHO2";
	public static final String ALTO_2 = "ALTO2";
	public static final String PESO_2 = "PESO2";
	public static final String VOLUMEN_2 = "VOLUMEN2";

	public static final String UNIDAD_CODIGO_3 = "UNIDAD_CODIGO3";
	public static final String FACTOR_CONVERSION_3 = "FACTOR_CONVERSION3";
	public static final String LARGO_3 = "LARGO3";
	public static final String ANCHO_3 = "ANCHO3";
	public static final String ALTO_3 = "ALTO3";
	public static final String PESO_3 = "PESO3";
	public static final String VOLUMEN_3 = "VOLUMEN3";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pf_producto_medida")
	private Long id;

	@Column(length = 50, nullable = false)
	@NotEmpty
	private String clienteCodigo;

	@Column(length = 50, nullable = false)
	@NotEmpty
	private String productoCodigo;

	@Column(length = 200, nullable = false)
	@NotEmpty
	private String productoNombre;

	@Column(length = 50, nullable = false)
	@NotEmpty
	private String bodegaCodigo;

	@Column(length = 50, nullable = false)
	@NotEmpty
	private String huellaCodigo;

	private int caseLevel;

	@Column(length = 50, nullable = false)
	@NotEmpty
	private String unidadCodigo1;
	private int factorConversion1;

	@Column(nullable = false)
	@NotNull
	private BigDecimal largo1;
	@Column(nullable = false)
	@NotNull
	private BigDecimal ancho1;
	@Column(nullable = false)
	@NotNull
	private BigDecimal alto1;
	@Column(nullable = false)
	@NotNull
	private BigDecimal peso1;
	@Column(nullable = false)
	@NotNull
	private BigDecimal volumen1;

	@Column(length = 50, nullable = false)
	@NotNull
	private String unidadCodigo2;
	private Integer factorConversion2;
	@Column(nullable = false)
	private BigDecimal largo2;
	@Column(nullable = false)
	private BigDecimal ancho2;
	@Column(nullable = false)
	private BigDecimal alto2;
	@Column(nullable = false)
	private BigDecimal peso2;
	@Column(nullable = false)
	private BigDecimal volumen2;

	@Column(length = 50, nullable = false)
	@NotNull
	private String unidadCodigo3;
	private Integer factorConversion3;
	@Column(nullable = false)
	private BigDecimal largo3;
	@Column(nullable = false)
	private BigDecimal ancho3;
	@Column(nullable = false)
	private BigDecimal alto3;
	@Column(nullable = false)
	private BigDecimal peso3;
	@Column(nullable = false)
	private BigDecimal volumen3;

	@Builder
	public ProntoFormProductoMedida(Long id, String clienteCodigo, String productoCodigo, String productoNombre,
			String bodegaCodigo, String huellaCodigo, int caseLevel, 
			String unidadCodigo1, int factorConversion1,
			BigDecimal largo1, BigDecimal ancho1, BigDecimal alto1, BigDecimal peso1, BigDecimal volumen1,
			String unidadCodigo2, Integer factorConversion2, 
			BigDecimal largo2, BigDecimal ancho2, BigDecimal alto2, BigDecimal peso2, BigDecimal volumen2, 
			String unidadCodigo3, Integer factorConversion3, 
			BigDecimal largo3, BigDecimal ancho3, BigDecimal alto3, BigDecimal peso3, BigDecimal volumen3,
			Long idArchivo, EstadoRegistroType estadoRegistro, int numeroLinea, String linea, 
			LocalDateTime fechaCreacion, String usuarioCreacion, 
			LocalDateTime fechaActualizacion, String usuarioActualizacion) {
		super(idArchivo, estadoRegistro, numeroLinea, linea, fechaCreacion, usuarioCreacion, fechaActualizacion, usuarioActualizacion);
		this.id = id;
		this.clienteCodigo = clienteCodigo;
		this.productoCodigo = productoCodigo;
		this.productoNombre = productoNombre;
		this.bodegaCodigo = bodegaCodigo;
		this.huellaCodigo = huellaCodigo;
		this.caseLevel = caseLevel;
		this.unidadCodigo1 = unidadCodigo1;
		this.factorConversion1 = factorConversion1;
		this.largo1 = largo1;
		this.ancho1 = ancho1;
		this.alto1 = alto1;
		this.peso1 = peso1;
		this.volumen1 = volumen1;
		this.unidadCodigo2 = unidadCodigo2;
		this.factorConversion2 = factorConversion2;
		this.largo2 = largo2;
		this.ancho2 = ancho2;
		this.alto2 = alto2;
		this.peso2 = peso2;
		this.volumen2 = volumen2;
		this.unidadCodigo3 = unidadCodigo3;
		this.factorConversion3 = factorConversion3;
		this.largo3 = largo3;
		this.ancho3 = ancho3;
		this.alto3 = alto3;
		this.peso3 = peso3;
		this.volumen3 = volumen3;
	}
	
	
}
