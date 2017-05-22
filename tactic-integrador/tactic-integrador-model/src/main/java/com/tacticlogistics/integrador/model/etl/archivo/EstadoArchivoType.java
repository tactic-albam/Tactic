package com.tacticlogistics.integrador.model.etl.archivo;

public enum EstadoArchivoType {
	NO_PROCESADO,

	ERROR_ESTRUCTURA,
	ESTRUCTURA_VALIDADA,
	
	HOMOLOGADO,
	ENRIQUECIDO,
	VALIDADO,
	PROCESADO,
	
	ERROR_FATAL
}