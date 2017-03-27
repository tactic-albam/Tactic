package com.tacticlogistics.integrador.etl.handlers;

import java.io.Serializable;
import java.nio.file.Path;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@ToString
@Builder
public class ArchivoRequest implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Getter
	@NonNull
	private Path root;

	@Getter
	@NonNull
	private Path archivo;

	private Path pathRelativa = null;

	public Path getDirectorioRelativo() {
		if (pathRelativa == null) {
			pathRelativa = this.getArchivo().getParent();
			pathRelativa = this.getRoot().relativize(pathRelativa);
		}
		return pathRelativa;
	}

	public Path getCliente() {
		Path result = this.getRoot().getParent().getFileName();
		return result;
	}
}
