package com.tacticlogistics.integrador.ftp.tasks;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.tacticlogistics.integrador.ftp.services.CheckDirectoriosService;
import com.tacticlogistics.integrador.ftp.services.Destino;
import com.tacticlogistics.integrador.ftp.services.FtpService;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Component
@Getter
@Slf4j
public class FtpTask {
	@Value("${directorios.locales}")
	private String[] directoriosLocales;

	@Autowired
	private List<FtpService> ftpServices;

	@Scheduled(cron = "0 */1 * * * ?")
	public void cronFtpPull() {
		log.info("Inicio cronFtpPull");

		List<Destino> destinos = getDestinos();
		List<FtpService> services = getFtpServices();
		for (FtpService service : services) {
			try {
				log.info("Realizando operación GET desde {}", service.getNombre());
				service.pull(destinos);
			} catch (RuntimeException e) {
				String error = "cronFtpPull: Ocurrio una excepción al procesar el servicio FTP " + service.getNombre();
				log.error(error, e);
			}
		}
		log.info("Fin cronFtpPull");
	}

	private List<Destino> getDestinos() {
		List<Destino> destinos = new ArrayList<>();
		for (String directorio : directoriosLocales) {
			destinos.add(new Destino(Paths.get(directorio)));

		}
		return destinos;
	}

	@Autowired
	@Qualifier("remote")
	private CheckDirectoriosService checkDirectoriosRemotosService;

	@Autowired
	@Qualifier("local")
	private CheckDirectoriosService checkDirectoriosLocalesService;

	@Scheduled(cron = "0 */10 * * * ?")
	public void cronCheckDirectorios() {
		try {
			log.info("Inicio cronCheckDirectorios");
			try {
				checkDirectoriosRemotosService.check();
			} catch (RuntimeException e) {
				log.error("cronCheckDirectorios: Ocurrio la siguiente excepción: ", e);
			}

			try {
				checkDirectoriosLocalesService.check();
			} catch (RuntimeException e) {
				log.error("cronCheckDirectorios: Ocurrio la siguiente excepción: ", e);
			}
		} finally {
			log.info("Fin cronCheckDirectorios");
		}
	}
}
