package com.tacticlogistics.integrador.ext.dicermex;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tacticlogistics.ClienteCodigoType;
import com.tacticlogistics.integrador.etl.file.ArchivoHandler;
import com.tacticlogistics.integrador.etl.file.DirectorioClienteHandler;
import com.tacticlogistics.integrador.ext.dicermex.despachos.DespachosArchivoETLHandler;
import com.tacticlogistics.integrador.ext.dicermex.despachos.DespachosLineasArchivoETLHandler;

//@Component
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
