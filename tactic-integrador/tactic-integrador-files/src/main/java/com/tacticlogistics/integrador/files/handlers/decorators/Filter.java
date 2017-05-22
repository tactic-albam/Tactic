package com.tacticlogistics.integrador.files.handlers.decorators;

import com.tacticlogistics.integrador.dto.ArchivoDTO;

public interface Filter<T> {
	ArchivoDTO<T> transformar(ArchivoDTO<T> archivoDTO);
}
