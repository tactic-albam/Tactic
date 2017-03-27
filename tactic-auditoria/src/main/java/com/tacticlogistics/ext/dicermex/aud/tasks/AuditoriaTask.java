package com.tacticlogistics.ext.dicermex.aud.tasks;

import java.nio.file.Path;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.tacticlogistics.ext.dicermex.aud.despachos.AuditoriaService;
import com.tacticlogistics.ext.dicermex.aud.despachos.ExportService;
import com.tacticlogistics.ext.dicermex.aud.despachos.NotificacionService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AuditoriaTask {

	@Autowired
	AuditoriaService auditoriaService;

	@Autowired
	ExportService exportService;

	@Autowired
	NotificacionService notificacionService;

	@Scheduled(cron = "0 0 18 * * ?")
	//@Scheduled(cron = "0 */1 * * * ?")
	public void cron() {
		try {
			log.info("cron {}",LocalDateTime.now());
			LocalDateTime fechaInicio = auditoriaService.getFechaUltimaAuditoria();
			LocalDateTime fechaFin = auditoriaService.getFechaNuevaAuditoria();
			log.info("fechaInicio {}",fechaInicio);
			log.info("fechaFin {}",fechaFin);
			int id = auditoriaService.auditar(fechaInicio, fechaFin);
			log.info("auditar:{}",id);
			Path path = exportService.exportar(id,fechaInicio, fechaFin);
			log.info("exportar:{}",path);
			notificacionService.notificar(path.toFile(),fechaInicio, fechaFin);
			log.info("notificar");
		} catch (RuntimeException e) {
			log.error("Ocurrio la siguiente excepcion: ", e);
		}
	}
}
