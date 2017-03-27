package com.tacticlogistics.integrador;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 * Aplicación encargada del procesamiento de archivos de diferentes tipo
 * La aplicación es responsable de:
 * <ul>
 * <li>Recorrer una estructura de archivos en busca de archivos</li>
 * <li>Indentificar el tipo de archivo de cada archivo encontrado</li>
 * <li>Leer el contenido de cada archivo</li>
 * <li>Validar la estructura del contenido de cada archivo</li>
 * <li>Convertir el contenido de cada archivo a estructuras de datos estandares</li>
 * <li>Persistir las estructuras de datos</li>
 * </ul> 
 * 
 * @author albam@tacticlogistics.com
 *
 */
@SpringBootApplication
@EnableScheduling
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Bean
	public javax.validation.Validator localValidatorFactoryBean() {
		return new LocalValidatorFactoryBean();
	}
}
