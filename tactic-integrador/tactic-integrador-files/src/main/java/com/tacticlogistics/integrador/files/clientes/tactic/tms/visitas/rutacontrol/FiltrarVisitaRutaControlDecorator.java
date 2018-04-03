package com.tacticlogistics.integrador.files.clientes.tactic.tms.visitas.rutacontrol;

import org.apache.commons.lang3.Validate;

import com.tacticlogistics.integrador.dto.ArchivoDTO;
import com.tacticlogistics.integrador.files.handlers.decorators.Decorator;
import com.tacticlogistics.integrador.files.handlers.decorators.Filter;
import com.tacticlogistics.integrador.model.tms.visitas.rutacontrol.VisitaRutaControl;

import lombok.val;

public class FiltrarVisitaRutaControlDecorator extends Decorator<VisitaRutaControl> {

	public FiltrarVisitaRutaControlDecorator() {
		super();
	}

	public FiltrarVisitaRutaControlDecorator(Filter<VisitaRutaControl> inner) {
		super(inner);
	}

	@Override
	public ArchivoDTO<VisitaRutaControl> transformar(ArchivoDTO<VisitaRutaControl> archivoDTO) {
		final val result = super.transformar(archivoDTO);
		Validate.notNull(result.getTipoArchivo());
		Validate.notNull(result.getDatos());

		String separador = result.getTipoArchivo().getRegExpSeparadorRegistros();
		String[] lineas = result.getDatos().split(separador, -1);

		StringBuilder sb = new StringBuilder();
		separador = result.getTipoArchivo().getUnescapedSeparadorRegistros();

		for (String linea : lineas) {
			if(linea.isEmpty()){
				continue;
			}

			sb.append(linea).append(separador);
		}

		result.setDatos(sb.toString());

		return result;
	}
}
