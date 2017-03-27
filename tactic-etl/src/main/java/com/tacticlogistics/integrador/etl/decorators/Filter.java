package com.tacticlogistics.integrador.etl.decorators;

import com.tacticlogistics.integrador.etl.dto.ArchivoDTO;

public interface Filter<T> {
	ArchivoDTO<T> transformar(ArchivoDTO<T> archivoDTO);
}
