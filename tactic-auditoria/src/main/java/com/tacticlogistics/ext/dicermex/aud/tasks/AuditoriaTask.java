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

	@Scheduled(cron = "* 30 17 * * ?")
	public void cron() {
		try {
			LocalDateTime fechaInicio = auditoriaService.getFechaUltimaAuditoria();
			LocalDateTime fechaFin = auditoriaService.getFechaNuevaAuditoria();

			int id = auditoriaService.auditar(fechaInicio, fechaFin);
			Path path = exportService.exportar(id,fechaInicio, fechaFin);
			notificacionService.notificar(path.toFile(),fechaInicio, fechaFin);
		} catch (RuntimeException e) {
			log.error("Ocurrio la siguiente excepcion: ", e);
		}
	}
}
