package com.tacticlogistics.integrador.files.clientes.tactic.oms.recibos;

import org.springframework.stereotype.Component;

@Component
public class PlantillaDevolucionesHandler extends AbstractPlantillaRecibosHandler {
	
	private static final String CODIGO_TIPO_ARCHIVO = "PLANTILLA_DEVOLUCIONES";

	private static final String SUBDIRECTORIO_RELATIVO = "OMS\\RECIBOS\\DEVOLUCIONES";

	
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