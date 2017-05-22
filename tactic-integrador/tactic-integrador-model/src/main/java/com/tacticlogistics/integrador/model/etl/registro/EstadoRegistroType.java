package com.tacticlogistics.integrador.model.etl.registro;

public enum EstadoRegistroType {
	NO_PROCESADO,
	
	ESTRUCTURA_VALIDADA,
	ERROR_ESTRUCTURA,
	
	HOMOLOGADO,
	ERROR_HOMOLOGACION,
	
	ENRIQUECIDO,
	ERROR_ENRIQUECIMIENTO,
	
	VALIDADO,
	ERROR_VALIDACION,
	
	PROCESADO,
	
	ERROR_FATAL
}