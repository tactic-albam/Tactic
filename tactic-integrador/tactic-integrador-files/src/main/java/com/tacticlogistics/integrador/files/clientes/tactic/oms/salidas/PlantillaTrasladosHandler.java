package com.tacticlogistics.integrador.files.clientes.tactic.oms.salidas;

import org.springframework.stereotype.Component;

@Component
public class PlantillaTrasladosHandler extends AbstractPlantillaSalidasHandler {
	
	private static final String CODIGO_TIPO_ARCHIVO = "PLANTILLA_TRASLADOS";

	private static final String SUBDIRECTORIO_RELATIVO = "OMS\\SALIDAS\\TRASLADOS";

	
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