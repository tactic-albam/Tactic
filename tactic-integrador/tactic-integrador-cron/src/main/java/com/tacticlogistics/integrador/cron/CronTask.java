package com.tacticlogistics.integrador.cron;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.tacticlogistics.integrador.db.services.DbIntegrationService;
import com.tacticlogistics.integrador.files.services.FilesIntegrationService;
import com.tacticlogistics.integrador.mail.fetch.services.FetchMailService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CronTask {

	@Autowired
	private FetchMailService fetchMailService;
	
	@Autowired
	private FilesIntegrationService filesService;

	@Autowired
	private DbIntegrationService dbService;

	@Scheduled(cron = "${tasks.cron}")
	public void cron() {
		log.debug("Inicio cron");

		fetchMailService.run();
		
		filesService.run();
		
		dbService.run();

		log.debug("Fin cron");
	}
}
