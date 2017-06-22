package com.tacticlogistics.integrador.files.clientes.tactic.oms.recibos;

import org.springframework.stereotype.Component;

@Component
public class PlantillaComprasHandler extends AbstractPlantillaRecibosHandler {
	
	private static final String CODIGO_TIPO_ARCHIVO = "PLANTILLA_COMPRAS";

	private static final String SUBDIRECTORIO_RELATIVO = "OMS\\RECIBOS\\COMPRAS";

	
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