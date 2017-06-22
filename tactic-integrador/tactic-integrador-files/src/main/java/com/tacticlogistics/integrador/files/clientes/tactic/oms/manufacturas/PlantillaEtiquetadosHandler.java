package com.tacticlogistics.integrador.files.clientes.tactic.oms.manufacturas;

import org.springframework.stereotype.Component;

@Component
public class PlantillaEtiquetadosHandler extends AbstractPlantillaManufacturaHandler {

	private static final String CODIGO_TIPO_ARCHIVO = "PLANTILLA_ETIQUETADOS";

	private static final String SUBDIRECTORIO_RELATIVO = "OMS\\MANUFACTURAS\\ETIQUETADOS";

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