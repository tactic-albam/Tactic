package com.tacticlogistics.integrador.etl.tasks;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.tacticlogistics.integrador.etl.model.GrupoIntegracion;
import com.tacticlogistics.integrador.etl.model.GrupoIntegracionRepository;
import com.tacticlogistics.integrador.etl.services.EtlService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class EtlTask {
	@Value("${directorios.locales}")
	private String[] directoriosLocales;

	@Autowired
	private EtlService service;
	
	@Autowired
	private GrupoIntegracionRepository repository;

	@Scheduled(cron = "${tasks.etl-task.cron}")
	public void cron() {
		List<GrupoIntegracion> lista = repository.findAll();
		
		for (GrupoIntegracion grupoIntegracion : lista) {
			System.out.println(grupoIntegracion.toString());
		}
		
		log.debug("Inicio cron");

		List<Path> paths = getDirectorios();

		for (Path path : paths) {
			service.run(path);
		}

		log.debug("Fin cron");
	}

	private List<Path> getDirectorios() {
		List<Path> paths = new ArrayList<>();
		for (String directorio : directoriosLocales) {
			paths.add(Paths.get(directorio));
		}
		return paths;
	}
}
