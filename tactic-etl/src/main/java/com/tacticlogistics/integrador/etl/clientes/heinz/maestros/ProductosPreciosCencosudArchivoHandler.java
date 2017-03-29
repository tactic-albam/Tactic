
package com.tacticlogistics.integrador.etl.clientes.heinz.maestros;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

@Component
public class ProductosPreciosCencosudArchivoHandler extends ProductoPrecioArchivoHandler {
	protected static final Pattern PATTERN = Pattern.compile("(?i:CENCOSUD\\.(xlsx|xls))");

	@Override
	protected Pattern getFileNamePattern() {
		return PATTERN;
	}
}
