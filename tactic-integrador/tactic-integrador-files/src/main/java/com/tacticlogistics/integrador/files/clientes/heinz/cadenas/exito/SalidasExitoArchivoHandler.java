package com.tacticlogistics.integrador.files.clientes.heinz.cadenas.exito;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import com.tacticlogistics.integrador.files.clientes.heinz.cadenas.SalidasCadenasArchivoHandler;
import com.tacticlogistics.integrador.files.clientes.tactic.oms.salidas.MapEntidadSalidaDecorator;
import com.tacticlogistics.integrador.files.handlers.decorators.CheckRegistrosDuplicadosDecorator;
import com.tacticlogistics.integrador.files.handlers.decorators.CheckRestriccionesDeCamposDecorator;
import com.tacticlogistics.integrador.files.handlers.decorators.Decorator;
import com.tacticlogistics.integrador.model.oms.Salida;

@Component
public class SalidasExitoArchivoHandler extends SalidasCadenasArchivoHandler {

	private static final String CODIGO_TIPO_ARCHIVO = "HEINZ_SALIDAS_CADENAS";

	private static final Pattern PATTERN = Pattern.compile("(?i:.*EXITO-.*\\.(xlsx))");

	@Override
	protected String getCodigoTipoArchivo() {
		return CODIGO_TIPO_ARCHIVO;
	}

	@Override
	protected Pattern getFileNamePattern() {
		return PATTERN;
	}

	@Override
	protected Decorator<Salida> getTransformador() {
		// @formatter:off
		return new MapEntidadSalidaDecorator(
				new CheckRegistrosDuplicadosDecorator<Salida>(
					new CheckRestriccionesDeCamposDecorator<Salida>(
						super.getTransformador()
		)));
		// @formatter:on
	}
}
