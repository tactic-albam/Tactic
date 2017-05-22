package com.tacticlogistics.integrador.files.handlers.decorators;

import java.math.BigDecimal;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import com.tacticlogistics.integrador.dto.ArchivoDTO;
import com.tacticlogistics.integrador.dto.RegistroDTO;
import com.tacticlogistics.integrador.model.etl.tipoarchivo.TipoArchivo;

import lombok.val;

public abstract class MapEntidadDecorator<T> extends Decorator<T> {

	public MapEntidadDecorator() {
		super();
	}

	public MapEntidadDecorator(Filter<T> inner) {
		super(inner);
	}

	@Override
	public ArchivoDTO<T> transformar(ArchivoDTO<T> archivoDTO) {
		final val result = super.transformar(archivoDTO);
		val registros = result.getRegistros();
		Assert.notEmpty(registros);

		boolean error = false;
		for (val registro : registros) {
			try {
				registro.setEntidad(map(result, registro));
			} catch (RuntimeException e) {
				error = true;
				registro.getErrores().add(e.getMessage() == null?e.getClass().getName():e.getMessage());
			}
		}

		if (error) {
			String mensaje = "Ocurrieron errores durante la conversión de los datos del archivo";
			ETLRuntimeException.throwException(mensaje, result);
		}

		return result;
	}

	protected abstract T map(ArchivoDTO<T> archivoDTO, RegistroDTO<T> registro);

	protected Integer getInteger(TipoArchivo tipoArchivo, final Map<String, String> datos, String campoCodigo) {
		val campo = tipoArchivo.getCampoPorCodigo(campoCodigo);
		Assert.isTrue(campo.isPresent());
		String valor = StringUtils.defaultString(datos.get(campo.get().getCodigo()));

		Integer result = null;
		if (!valor.isEmpty()) {
			result = Integer.parseInt(valor);
		}
		return result;
	}

	protected Long getLong(TipoArchivo tipoArchivo, final Map<String, String> datos, String campoCodigo) {
		val campo = tipoArchivo.getCampoPorCodigo(campoCodigo);
		Assert.isTrue(campo.isPresent());
		String valor = StringUtils.defaultString(datos.get(campo.get().getCodigo()));

		Long result = null;
		if (!valor.isEmpty()) {
			result = Long.parseLong(valor);
		}
		return result;
	}

	protected BigDecimal getBigDecimal(TipoArchivo tipoArchivo, final Map<String, String> datos, String campoCodigo) {
		val campo = tipoArchivo.getCampoPorCodigo(campoCodigo);
		Assert.isTrue(campo.isPresent());
		String valor = StringUtils.defaultString(datos.get(campo.get().getCodigo()));

		try {
			BigDecimal result = null;
			if (!valor.isEmpty()) {
				result = (BigDecimal) campo.get().getDecimalFormat().parse(valor);
			}
			return result;
		} catch (ParseException e) {
			String mensaje = "Ocurrio un error al intentar hacer la conversión del dato %s";
			mensaje = String.format(mensaje, valor);
			throw new RuntimeException(mensaje, e);
		}
	}

	protected LocalDateTime getLocalDateTime(TipoArchivo tipoArchivo, final Map<String, String> datos,
			String campoCodigo) {
		val campo = tipoArchivo.getCampoPorCodigo(campoCodigo);
		Assert.isTrue(campo.isPresent());
		String valor = StringUtils.defaultString(datos.get(campo.get().getCodigo()));

		LocalDateTime result = null;
		if (!valor.isEmpty()) {
			result = LocalDateTime.parse(valor, campo.get().getDateTimeFormatter());
		}
		return result;
	}

	protected LocalDate getLocalDate(TipoArchivo tipoArchivo, final Map<String, String> datos, String campoCodigo) {
		val campo = tipoArchivo.getCampoPorCodigo(campoCodigo);
		Assert.isTrue(campo.isPresent());
		String valor = StringUtils.defaultString(datos.get(campo.get().getCodigo()));

		LocalDate result = null;
		if (!valor.isEmpty()) {
			result = LocalDate.parse(valor, campo.get().getDateTimeFormatter());
		}
		return result;
	}

	protected LocalTime getLocalTime(TipoArchivo tipoArchivo, final Map<String, String> datos, String campoCodigo) {
		val campo = tipoArchivo.getCampoPorCodigo(campoCodigo);
		Assert.isTrue(campo.isPresent());
		String valor = StringUtils.defaultString(datos.get(campo.get().getCodigo()));

		LocalTime result = null;
		if (!valor.isEmpty()) {
			result = LocalTime.parse(valor, campo.get().getDateTimeFormatter());
		}
		return result;
	}

	protected LocalTime getLocalTimeFromBigDecimal(TipoArchivo tipoArchivo, final Map<String, String> datos,
			String campoCodigo) {
		BigDecimal valor = this.getBigDecimal(tipoArchivo, datos, campoCodigo);

		LocalTime result = null;
		if (valor != null) {
			Float floatValue = valor.floatValue();
			if (floatValue >= 1.0) {
				String mensaje = "La hora suministrada en el campo %s supera las 24 horas";
				mensaje = String.format(mensaje, campoCodigo);
				throw new RuntimeException(mensaje);
			}

			result = LocalTime.ofSecondOfDay((long) ((24L * 60L * 60L * 1L) * floatValue));
		}
		return result;
	}
}