package com.tacticlogistics.integrador.files.clientes.tactic.oms.salidas;

import org.springframework.stereotype.Component;

@Component
public class PlantillaCrossDockingHandler extends AbstractPlantillaSalidasHandler {
	private static final String CODIGO_TIPO_ARCHIVO = "PLANTILLA_XD";

	private static final String SUBDIRECTORIO_RELATIVO = "OMS\\SALIDAS\\XD";

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