package com.tacticlogistics.integrador.ftp.services;

import java.nio.file.Path;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Destino {
	private Path path;

	public Destino(Path path) {
		super();
		this.path = path;
	}
}
