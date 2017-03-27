
package com.tacticlogistics.integrador.etl.handlers.heinz.maestros;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

@Component
public class ProductosPreciosExitoArchivoHandler extends ProductoPrecioArchivoHandler {
	protected static final Pattern PATTERN = Pattern.compile("(?i:EXITO\\.(xlsx|xls))");

	@Override
	protected Pattern getFileNamePattern() {
		return PATTERN;
	}
}
