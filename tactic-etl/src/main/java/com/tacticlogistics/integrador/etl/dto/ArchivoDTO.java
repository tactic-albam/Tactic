package com.tacticlogistics.integrador.etl.dto;

import java.nio.file.Path;
import java.util.List;

import com.tacticlogistics.integrador.etl.dto.RegistroDTO.Status;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ArchivoDTO<T> {

	private Path path;
	private List<RegistroDTO<T>> data;
	
	public boolean hasError() {
		return data.stream().anyMatch(a -> a.getEstado().equals(Status.ERROR));
	}
}
