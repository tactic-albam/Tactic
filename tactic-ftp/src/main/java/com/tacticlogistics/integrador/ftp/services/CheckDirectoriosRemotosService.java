package com.tacticlogistics.integrador.ftp.services;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service("remote")
@Slf4j
public class CheckDirectoriosRemotosService implements CheckDirectoriosService {

	@Override
	public void check() {
		try {
			log.info("Inicio check");
			
		} catch (RuntimeException e) {
			log.error("check: Ocurrio la siguiente excepción: ", e);
		} finally {
			log.info("Fin check");
		}
	}

}
