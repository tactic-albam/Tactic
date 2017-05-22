package com.tacticlogistics.integrador.db.dto;

import java.io.Serializable;

import com.tacticlogistics.integrador.model.etl.archivo.Archivo;
import com.tacticlogistics.integrador.model.etl.tipoarchivo.TipoArchivo;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class DbRequestDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private TipoArchivo tipoArchivo;
	
	private Archivo archivo;

}