package com.tacticlogistics.ext.tactic;

import org.springframework.stereotype.Component;

import com.tacticlogistics.ClienteCodigoType;
import com.tacticlogistics.etl.file.ArchivoHandler;
import com.tacticlogistics.etl.file.DirectorioClienteHandler;

@Component
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
