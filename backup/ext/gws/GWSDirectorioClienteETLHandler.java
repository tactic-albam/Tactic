package com.tacticlogistics.integrador.ext.gws;

import org.springframework.stereotype.Component;

import com.tacticlogistics.ClienteCodigoType;
import com.tacticlogistics.integrador.etl.file.ArchivoHandler;
import com.tacticlogistics.integrador.etl.file.DirectorioClienteHandler;

//@Component
public class GWSDirectorioClienteETLHandler extends DirectorioClienteHandler {

	private static final String WORKING_DIRECTORY = ClienteCodigoType.GWS.toString();

	@Override
	protected String getWorkingDirectory() {
		return WORKING_DIRECTORY;
	}

	@Override
	protected ArchivoHandler getArchivoETLHandlerChain() {
		// TODO Auto-generated method stub
		return null;
	}


}
