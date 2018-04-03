package com.tacticlogistics.integrador.files.clientes.tactic.tms.visitas.rutacontrol;

import java.util.Optional;

import org.apache.commons.lang3.Validate;

import com.tacticlogistics.integrador.dto.ArchivoDTO;
import com.tacticlogistics.integrador.dto.RegistroDTO;
import com.tacticlogistics.integrador.files.handlers.decorators.Decorator;
import com.tacticlogistics.integrador.files.handlers.decorators.Filter;
import com.tacticlogistics.integrador.model.etl.tipoarchivo.Campo;
import com.tacticlogistics.integrador.model.tms.visitas.rutacontrol.VisitaRutaControl;

import lombok.val;

public class ReemplazarValoresVisitaRutaControlDecorator extends Decorator<VisitaRutaControl> {

	public ReemplazarValoresVisitaRutaControlDecorator() {
		super();
	}

	public ReemplazarValoresVisitaRutaControlDecorator(Filter<VisitaRutaControl> inner) {
		super(inner);
	}

	@Override
	public ArchivoDTO<VisitaRutaControl> transformar(ArchivoDTO<VisitaRutaControl> archivoDTO) {
		final val result = super.transformar(archivoDTO);
		Validate.notNull(result.getTipoArchivo());
		Validate.notEmpty(result.getRegistros());

		for (val registro : result.getRegistros()) {
			reemplazarValoresNull(registro);
		}

		return result;
	}

	protected void reemplazarValoresNull(RegistroDTO<VisitaRutaControl> registro) {
		// @formatter:off
		registro
		.getDatos()
		.entrySet()
		.stream()
		.filter(a -> a.getValue().equals("NULL"))
		.forEach(a -> a.setValue(""));
		// @formatter:on
	}

	protected void reducirNumeroDeCaracteres(ArchivoDTO<VisitaRutaControl> archivoDTO, String campoCodigo, RegistroDTO<VisitaRutaControl> registro) {
		Validate.notNull(archivoDTO);
		Validate.notNull(archivoDTO.getTipoArchivo());
		Optional<Campo> optional = archivoDTO.getTipoArchivo().getCampoPorCodigo(campoCodigo);
		Validate.isTrue(optional.isPresent());
		Campo campo = optional.get();

		// @formatter:off
		registro
		.getDatos()
		.entrySet()
		.stream()
		.filter(a -> a.getKey().equals(campo.getCodigo()) && a.getValue().length() > campo.getNumeroCaracteres())
		.forEach(a -> a.setValue(a.getValue().substring(0, campo.getNumeroCaracteres())));
		// @formatter:on
	}
}
