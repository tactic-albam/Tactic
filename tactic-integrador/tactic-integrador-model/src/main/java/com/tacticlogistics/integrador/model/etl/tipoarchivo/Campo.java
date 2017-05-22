package com.tacticlogistics.integrador.model.etl.tipoarchivo;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.regex.Pattern;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(catalog = "etl", name = "campos")
@Getter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
public class Campo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_campo")
	private Long id;

	private int ordinal;

	@Column(length = 50, nullable = false)
	@NotNull
	@NotEmpty
	private String codigo;

	@Column(length = 100, nullable = false)
	@NotNull
	private String nombre;

	@Column(length = 200, nullable = false)
	@NotNull
	private String descripcion;

	@Enumerated(EnumType.STRING)
	@Column(name = "id_tipo_dato", nullable = false, length = 50)
	@NotNull
	private TipoDatoType tipoDato;

	private int numeroCaracteres;

	private boolean truncarCaracteres;
	private boolean ignorar;
	private boolean incluir;
	private boolean obligatorio;

	@Column(length = 200, nullable = false)
	@NotNull
	private String valorPredeterminado;

	@Column(length = 50)
	private String formato;

	@Column(length = 1)
	private String formatoSeparadorDecimal;

	@Column(length = 1)
	private String formatoSeparadorGrupo;

	@Column(length = 50, nullable = false)
	@NotNull
	private String expresionRegular;

	@Column(length = 500, nullable = false)
	@NotNull
	private String valoresPermitidos;

	private Long valorEnteroMin;
	private Long valorEnteroMax;
	private BigDecimal valorDecimalMin;
	private BigDecimal valorDecimalMax;
	private LocalDate valorFechaMin;
	private LocalDate valorFechaMax;
	private LocalTime valorHoraMin;
	private LocalTime valorHoraMax;

	private boolean activo;

	@Column(nullable = false)
	@NotNull
	private LocalDateTime fechaActualizacion;

	@Column(length = 50, nullable = false)
	@NotNull
	private String usuarioActualizacion;

	transient private DateTimeFormatter dateTimeFormatter;

	public DateTimeFormatter getDateTimeFormatter() {
		if (this.dateTimeFormatter == null && !this.isIgnorar()) {
			switch (this.getTipoDato()) {
			case DATETIME:
			case DATE:
			case TIME:
				String mensaje = String.format("El campo %s, es de tipo %s y no tiene un formato definido.",
						this.getCodigo(), this.getTipoDato());
				Assert.hasLength(this.getFormato(), mensaje);

				this.dateTimeFormatter = DateTimeFormatter.ofPattern(this.getFormato());
				break;
			default:
				break;
			}
		}
		return dateTimeFormatter;
	}

	transient private DecimalFormat decimalFormat;

	/**
	 * https://docs.oracle.com/javase/tutorial/i18n/format/decimalFormat.html#numberpattern
	 * @return
	 */
	public DecimalFormat getDecimalFormat() {
		if (this.decimalFormat == null && !this.isIgnorar()) {
			switch (this.getTipoDato()) {
			case DECIMAL:
				String mensaje;
				mensaje = "El campo %s, es de tipo %s y no tiene un formato definido.";
				mensaje = String.format(mensaje, this.getCodigo(), this.getTipoDato());
				Assert.hasLength(this.getFormato(), mensaje);
				
				mensaje = "El campo %s, es de tipo %s y no tiene un separador decimal definido.";
				mensaje = String.format(mensaje, this.getCodigo(), this.getTipoDato());
				Assert.hasLength(this.getFormatoSeparadorDecimal(), mensaje);

				mensaje = "El campo %s, es de tipo %s y no tiene un separador de grupo definido.";
				mensaje = String.format(mensaje, this.getCodigo(), this.getTipoDato());
				Assert.hasLength(this.getFormatoSeparadorGrupo(), mensaje);

				DecimalFormatSymbols symbols = new DecimalFormatSymbols();
				symbols.setDecimalSeparator(this.getFormatoSeparadorDecimal().charAt(0));
				symbols.setGroupingSeparator(this.getFormatoSeparadorGrupo().charAt(0));
				
				this.decimalFormat = new DecimalFormat(this.getFormato(),symbols);
				this.decimalFormat.setParseBigDecimal(true);
				break;
			default:
				break;
			}
		}
		return decimalFormat;
	}

	transient private Pattern pattern;

	public Pattern getPattern() {
		if (this.pattern == null && !this.isIgnorar()) {
			if (StringUtils.hasLength(this.getExpresionRegular())) {
				this.pattern = Pattern.compile(this.getExpresionRegular());
			}
		}
		return pattern;
	}

	@Setter(AccessLevel.NONE)
	transient private Set<String> _valoresPermitidos;

	public Set<String> valoresPermitidos() {
		if (this._valoresPermitidos == null && !this.isIgnorar()) {
			if (StringUtils.hasLength(this.getValoresPermitidos())) {
				this._valoresPermitidos = StringUtils.commaDelimitedListToSet(this.getValoresPermitidos());
			}
		}
		return this._valoresPermitidos;
	}
}
