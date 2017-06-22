package com.tacticlogistics.integrador.files.clientes.tactic.oms.manufacturas;

import org.springframework.stereotype.Component;

@Component
public class PlantillaEnsamblesHandler extends AbstractPlantillaManufacturaHandler {

	private static final String CODIGO_TIPO_ARCHIVO = "PLANTILLA_ENSAMBLES";

	private static final String SUBDIRECTORIO_RELATIVO = "OMS\\MANUFACTURAS\\ENSAMBLES";

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