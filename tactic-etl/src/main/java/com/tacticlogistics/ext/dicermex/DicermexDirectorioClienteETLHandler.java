package com.tacticlogistics.ext.dicermex;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tacticlogistics.ClienteCodigoType;
import com.tacticlogistics.etl.file.ArchivoHandler;
import com.tacticlogistics.etl.file.DirectorioClienteHandler;
import com.tacticlogistics.ext.dicermex.despachos.DespachosArchivoETLHandler;
import com.tacticlogistics.ext.dicermex.despachos.DespachosLineasArchivoETLHandler;

@Component
public class DicermexDirectorioClienteETLHandler extends DirectorioClienteHandler {
	
	private static final String WORKING_DIRECTORY = ClienteCodigoType.DICERMEX.toString();

	@Autowired
	private DespachosArchivoETLHandler despachos;

	@Autowired
	private DespachosLineasArchivoETLHandler despachosLineas;

	@Override
	protected String getWorkingDirectory() {
		return WORKING_DIRECTORY;
	}

	@Override
	protected ArchivoHandler getArchivoETLHandlerChain() {
		ArchivoHandler rootChain = despachos;
		rootChain.setNextHandler(despachosLineas);
		return rootChain;
	}
}
