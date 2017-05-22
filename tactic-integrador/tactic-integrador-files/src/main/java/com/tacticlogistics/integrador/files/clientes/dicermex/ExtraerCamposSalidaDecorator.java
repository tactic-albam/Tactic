package com.tacticlogistics.integrador.files.clientes.dicermex;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import com.tacticlogistics.ClienteCodigoType;
import com.tacticlogistics.integrador.dto.ArchivoDTO;
import com.tacticlogistics.integrador.dto.RegistroDTO;
import com.tacticlogistics.integrador.files.handlers.decorators.Decorator;
import com.tacticlogistics.integrador.files.handlers.decorators.Filter;
import com.tacticlogistics.integrador.model.clientes.dicermex.DicermexSalida;

import lombok.val;

public class ExtraerCamposSalidaDecorator extends Decorator<DicermexSalida> {

	public ExtraerCamposSalidaDecorator() {
		super();
	}

	public ExtraerCamposSalidaDecorator(Filter<DicermexSalida> inner) {
		super(inner);
	}

	@Override
	public ArchivoDTO<DicermexSalida> transformar(ArchivoDTO<DicermexSalida> archivoDTO) {
		final val result = super.transformar(archivoDTO);
		Assert.notEmpty(result.getRegistros());

		val registros = result.getRegistros();
		val clienteCodigo = ClienteCodigoType.DICERMEX.toString();
		for (val registro : registros) {
			registro.getDatos().put(DicermexSalida.CLIENTE_CODIGO, clienteCodigo);
			cambiarDatosServicioCodigo(registro);
			cambiarDatosFemiFemaHomiHoma(registro);
		}

		return result;
	}

	private void cambiarDatosServicioCodigo(final RegistroDTO<DicermexSalida> registro) {
		String numeroOrden = registro.getDatos().get(DicermexSalida.NUMERO_ORDEN);
		String servicioCodigo = StringUtils.remove(StringUtils.left(numeroOrden, 3), "-");

		registro.getDatos().put(DicermexSalida.SERVICIO_CODIGO, servicioCodigo);
	}

	private void cambiarDatosFemiFemaHomiHoma(final RegistroDTO<DicermexSalida> registro) {
		String notas = registro.getDatos().get(DicermexSalida.NOTAS);
		notas = StringUtils.deleteWhitespace(notas);
		notas = StringUtils.left(notas, 200);
		registro.getDatos().put(DicermexSalida.NOTAS,notas);
		if(!cambiarDatosFechaHoraMinimaMaximaEntrega(registro, notas)){
			cambiarDatosFechaMinimaMaximaEntrega(registro, notas);
		}
	}

	private boolean cambiarDatosFechaHoraMinimaMaximaEntrega(final RegistroDTO<DicermexSalida> registro, String value) {
		if (!value.matches("@99\\d{1,2}:\\d{1,2}\\-\\d{1,2}:\\d{1,2}@98\\d{8}.*")) {
			return false;
		}

		value = value.replaceAll("@99", "");
		value = value.replaceAll("@98", "-");

		val datos = value.split("-");
		val homi = StringUtils.leftPad(datos[0], 5, '0');
		val homa = StringUtils.leftPad(datos[1], 5, '0');
		val fecha = StringUtils.left(datos[2], 8);

		registro.getDatos().put(DicermexSalida.FEMI, fecha);
		registro.getDatos().put(DicermexSalida.FEMA, fecha);
		registro.getDatos().put(DicermexSalida.HOMI, homi);
		registro.getDatos().put(DicermexSalida.HOMA, homa);
		
		return true;
	}

	private boolean cambiarDatosFechaMinimaMaximaEntrega(final RegistroDTO<DicermexSalida> registro, String value) {
		if (!value.matches("FECHAMINIMA\\d{8}FECHAMAXIMA\\d{8}.*")) {
			return false;
		}

		value = value.replaceAll("FECHAMINIMA", "");
		value = value.replaceAll("FECHAMAXIMA", "-");

		val datos = value.split("-");
		val femi = StringUtils.left(datos[0], 8);
		val fema = StringUtils.left(datos[1], 8);
		
		registro.getDatos().put(DicermexSalida.FEMI, femi);
		registro.getDatos().put(DicermexSalida.FEMA, fema);
		registro.getDatos().put(DicermexSalida.HOMI, "");
		registro.getDatos().put(DicermexSalida.HOMA, "");
		
		return true;
	}
}
