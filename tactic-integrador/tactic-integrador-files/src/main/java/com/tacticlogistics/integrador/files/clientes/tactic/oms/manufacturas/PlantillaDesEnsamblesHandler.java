package com.tacticlogistics.integrador.files.clientes.tactic.oms.manufacturas;

import org.springframework.stereotype.Component;

@Component
public class PlantillaDesEnsamblesHandler extends AbstractPlantillaManufacturaHandler {

	private static final String CODIGO_TIPO_ARCHIVO = "PLANTILLA_DESENSAMBLES";

	private static final String SUBDIRECTORIO_RELATIVO = "OMS\\MANUFACTURAS\\DESENSAMBLES";

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