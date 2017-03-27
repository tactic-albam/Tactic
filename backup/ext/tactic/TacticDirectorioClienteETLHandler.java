package com.tacticlogistics.integrador.ext.tactic;

import org.springframework.stereotype.Component;

import com.tacticlogistics.ClienteCodigoType;
import com.tacticlogistics.integrador.etl.file.ArchivoHandler;
import com.tacticlogistics.integrador.etl.file.DirectorioClienteHandler;

//@Component
public class TacticDirectorioClienteETLHandler extends DirectorioClienteHandler {

	@Override
	protected String getWorkingDirectory() {
		return ClienteCodigoType.TACTIC.toString();
	}


	@Override
	protected ArchivoHandler getArchivoETLHandlerChain() {
		// TODO Auto-generated method stub
		return null;
	}
}
