package com.tacticlogistics.integrador.etl.clientes.tactic.tms.rutas.rutacontrol;

import java.util.Optional;

import org.springframework.util.Assert;

import com.tacticlogistics.integrador.etl.clientes.tactic.tms.rutas.rutacontrol.model.VisitaRutaControl;
import com.tacticlogistics.integrador.etl.handlers.decorators.Decorator;
import com.tacticlogistics.integrador.etl.handlers.decorators.Filter;
import com.tacticlogistics.integrador.etl.handlers.dto.ArchivoDTO;
import com.tacticlogistics.integrador.etl.handlers.dto.RegistroDTO;
import com.tacticlogistics.integrador.etl.model.etl.Campo;

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
		Assert.notNull(result.getTipoArchivo());
		Assert.notEmpty(result.getRegistros());

		for (val registro : result.getRegistros()) {
			reemplazarValoresNull(registro);
			reducirNumeroDeCaracteres(result, VisitaRutaControl.FECHA_HORA_INICIO_VISITA, registro);
			reducirNumeroDeCaracteres(result, VisitaRutaControl.FECHA_HORA_FIN_VISITA, registro);
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
		Assert.notNull(archivoDTO);
		Assert.notNull(archivoDTO.getTipoArchivo());
		Optional<Campo> optional = archivoDTO.getTipoArchivo().getCampoPorCodigo(campoCodigo);
		Assert.isTrue(optional.isPresent());
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
