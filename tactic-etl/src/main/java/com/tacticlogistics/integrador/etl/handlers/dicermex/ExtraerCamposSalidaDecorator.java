package com.tacticlogistics.integrador.etl.handlers.dicermex;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;

import com.tacticlogistics.ClienteCodigoType;
import com.tacticlogistics.integrador.etl.decorators.Decorator;
import com.tacticlogistics.integrador.etl.decorators.Filter;
import com.tacticlogistics.integrador.etl.dto.ArchivoDTO;
import com.tacticlogistics.integrador.etl.dto.RegistroDTO;
import com.tacticlogistics.integrador.etl.handlers.dicermex.model.Salida;

import lombok.val;

public class ExtraerCamposSalidaDecorator extends Decorator<Salida> {

	public ExtraerCamposSalidaDecorator() {
		super();
	}

	public ExtraerCamposSalidaDecorator(Filter<Salida> inner) {
		super(inner);
	}

	@Override
	public ArchivoDTO<Salida> transformar(ArchivoDTO<Salida> archivoDTO) {
		final val result = super.transformar(archivoDTO);
		Assert.notEmpty(result.getRegistros());

		val registros = result.getRegistros();
		val clienteCodigo = ClienteCodigoType.DICERMEX.toString();
		for (val registro : registros) {
			registro.getDatos().put(Salida.CLIENTE_CODIGO, clienteCodigo);
			cambiarDatosServicioCodigo(registro);
			cambiarDatosFemiFemaHomiHoma(registro);
		}

		return result;
	}

	private void cambiarDatosServicioCodigo(final RegistroDTO<Salida> registro) {
		String numeroOrden = registro.getDatos().get(Salida.NUMERO_ORDEN);
		String servicioCodigo = StringUtils.remove(StringUtils.left(numeroOrden, 3), "-");

		registro.getDatos().put(Salida.SERVICIO_CODIGO, servicioCodigo);
	}

	private void cambiarDatosFemiFemaHomiHoma(final RegistroDTO<Salida> registro) {
		String notas = registro.getDatos().get(Salida.NOTAS);
		notas = StringUtils.deleteWhitespace(notas);
		notas = StringUtils.left(notas, 200);
		registro.getDatos().put(Salida.NOTAS,notas);
		if(!cambiarDatosFechaHoraMinimaMaximaEntrega(registro, notas)){
			cambiarDatosFechaMinimaMaximaEntrega(registro, notas);
		}
	}

	private boolean cambiarDatosFechaHoraMinimaMaximaEntrega(final RegistroDTO<Salida> registro, String value) {
		if (!value.matches("@99\\d{1,2}:\\d{1,2}\\-\\d{1,2}:\\d{1,2}@98\\d{8}.*")) {
			return false;
		}

		value = value.replaceAll("@99", "");
		value = value.replaceAll("@98", "-");

		val datos = value.split("-");
		val homi = StringUtils.leftPad(datos[0], 5, '0');
		val homa = StringUtils.leftPad(datos[1], 5, '0');
		val fecha = StringUtils.left(datos[2], 8);

		registro.getDatos().put(Salida.FEMI, fecha);
		registro.getDatos().put(Salida.FEMA, fecha);
		registro.getDatos().put(Salida.HOMI, homi);
		registro.getDatos().put(Salida.HOMA, homa);
		
		return true;
	}

	private boolean cambiarDatosFechaMinimaMaximaEntrega(final RegistroDTO<Salida> registro, String value) {
		if (!value.matches("FECHAMINIMA\\d{8}FECHAMAXIMA\\d{8}.*")) {
			return false;
		}

		value = value.replaceAll("FECHAMINIMA", "");
		value = value.replaceAll("FECHAMAXIMA", "-");

		val datos = value.split("-");
		val femi = StringUtils.left(datos[0], 8);
		val fema = StringUtils.left(datos[1], 8);
		
		registro.getDatos().put(Salida.FEMI, femi);
		registro.getDatos().put(Salida.FEMA, fema);
		registro.getDatos().put(Salida.HOMI, "");
		registro.getDatos().put(Salida.HOMA, "");
		
		return true;
	}
}
