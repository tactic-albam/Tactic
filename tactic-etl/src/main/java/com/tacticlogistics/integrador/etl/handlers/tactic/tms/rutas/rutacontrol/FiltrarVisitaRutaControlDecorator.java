package com.tacticlogistics.integrador.etl.handlers.tactic.tms.rutas.rutacontrol;

import java.util.Optional;

import org.springframework.util.Assert;

import com.tacticlogistics.integrador.etl.decorators.Decorator;
import com.tacticlogistics.integrador.etl.decorators.Filter;
import com.tacticlogistics.integrador.etl.dto.ArchivoDTO;
import com.tacticlogistics.integrador.etl.handlers.tactic.tms.rutas.rutacontrol.model.VisitaRutaControl;
import com.tacticlogistics.integrador.etl.model.Campo;

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
		boolean descartar = true;
		Optional<Campo> campo = result.getTipoArchivo().getCampoPorCodigo(VisitaRutaControl.OBSERVACION);
		String campoNombre = campo.get().getNombre();

		for (String linea : lineas) {
			if(linea.isEmpty()){
				continue;
			}
			
			if (campoNombre.equalsIgnoreCase(linea.trim())) {
				descartar = false;
				continue;
			}

			if (!descartar) {
				sb.append(linea).append(separador);
			}
		}

		result.setDatos(sb.toString());

		return result;
	}
}
