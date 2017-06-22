package com.tacticlogistics.integrador.files.clientes.tactic.oms.manufacturas;

import org.springframework.stereotype.Component;

@Component
public class PlantillaEstampilladosHandler extends AbstractPlantillaManufacturaHandler {

	private static final String CODIGO_TIPO_ARCHIVO = "PLANTILLA_ESTAMPILLADOS";

	private static final String SUBDIRECTORIO_RELATIVO = "OMS\\MANUFACTURAS\\ESTAMPILLADOS";

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