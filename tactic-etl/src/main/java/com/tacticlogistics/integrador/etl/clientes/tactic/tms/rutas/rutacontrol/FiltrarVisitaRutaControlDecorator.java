package com.tacticlogistics.integrador.etl.clientes.tactic.tms.rutas.rutacontrol;

import org.springframework.util.Assert;

import com.tacticlogistics.integrador.etl.clientes.tactic.tms.rutas.rutacontrol.model.VisitaRutaControl;
import com.tacticlogistics.integrador.etl.handlers.decorators.Decorator;
import com.tacticlogistics.integrador.etl.handlers.decorators.Filter;
import com.tacticlogistics.integrador.etl.handlers.dto.ArchivoDTO;

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
		Assert.notNull(result.getTipoArchivo());
		Assert.notNull(result.getDatos());

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
