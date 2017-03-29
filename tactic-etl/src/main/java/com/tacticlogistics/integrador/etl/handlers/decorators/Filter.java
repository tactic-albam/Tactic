package com.tacticlogistics.integrador.etl.handlers.decorators;

import com.tacticlogistics.integrador.etl.handlers.dto.ArchivoDTO;

public interface Filter<T> {
	ArchivoDTO<T> transformar(ArchivoDTO<T> archivoDTO);
}
