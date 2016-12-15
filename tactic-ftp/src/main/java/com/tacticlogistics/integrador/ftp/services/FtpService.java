package com.tacticlogistics.integrador.ftp.services;

import java.util.List;

public interface FtpService {
	String getNombre();

	void pull(List<Destino> destinos);
}
