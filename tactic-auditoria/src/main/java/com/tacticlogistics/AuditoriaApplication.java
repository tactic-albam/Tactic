package com.tacticlogistics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AuditoriaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuditoriaApplication.class, args);
	}
}
