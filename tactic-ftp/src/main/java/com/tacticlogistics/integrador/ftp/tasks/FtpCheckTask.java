package com.tacticlogistics.integrador.ftp.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.tacticlogistics.integrador.ftp.services.CheckDirectoriosService;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Component
@Getter
@Slf4j
public class FtpCheckTask {
	@Autowired
	@Qualifier("remote")
	private CheckDirectoriosService checkDirectoriosRemotosService;

	@Autowired
	@Qualifier("local")
	private CheckDirectoriosService checkDirectoriosLocalesService;

	@Scheduled(cron = "0 */1 * * * ?")
	public void cronCheckDirectorios() {
		try {
			log.info("Inicio cronCheckDirectorios");
			try {
				// checkDirectoriosRemotosService.check();
			} catch (RuntimeException e) {
				log.error("cronCheckDirectorios: Ocurrio la siguiente excepción: ", e);
			}

			try {
				// checkDirectoriosLocalesService.check();
			} catch (RuntimeException e) {
				log.error("cronCheckDirectorios: Ocurrio la siguiente excepción: ", e);
			}
		} finally {
			log.info("Fin cronCheckDirectorios");
		}
	}
}
