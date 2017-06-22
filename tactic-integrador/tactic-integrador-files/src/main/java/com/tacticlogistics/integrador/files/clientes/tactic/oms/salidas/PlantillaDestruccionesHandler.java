package com.tacticlogistics.integrador.files.clientes.tactic.oms.salidas;

import org.springframework.stereotype.Component;

import com.tacticlogistics.integrador.files.clientes.tactic.oms.manufacturas.AbstractPlantillaManufacturaHandler;

@Component
public class PlantillaDestruccionesHandler extends AbstractPlantillaManufacturaHandler {
	private static final String CODIGO_TIPO_ARCHIVO = "PLANTILLA_DESTRUCCIONES";

	private static final String SUBDIRECTORIO_RELATIVO = "OMS\\SALIDAS\\DESTRUCCIONES";

	// ----------------------------------------------------------------------------------------------------------------
	//
	// ----------------------------------------------------------------------------------------------------------------
	@Override
	protected String getCodigoTipoArchivo() {
		return CODIGO_TIPO_ARCHIVO;
	}

	@Override
	protected String getDirectorioRelativo() {
		return SUBDIRECTORIO_RELATIVO;
	}
}
