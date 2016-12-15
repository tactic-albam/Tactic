package com.tacticlogistics.integrador.ftp.services;

import java.util.List;

import org.apache.commons.net.ftp.FTPClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import com.tacticlogistics.integrador.ftp.FTPConnectionPool;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Order(value = 1)
@NoArgsConstructor
@Data
@ConfigurationProperties(prefix = "tactic.ftp")
@Slf4j
public class TacticFtpService implements FtpService {
	private String nombre;
	@Autowired
	private FTPConnectionPool pool;

	@Override
	public void pull(List<Destino> destinos) {
		try {
			log.info("Inicio pull");
			FTPClient ftpClient = pool.getConnection();
			
			pool.freeConnection(ftpClient);
		} catch (RuntimeException e) {
			log.error("pull: Ocurrio la siguiente excepción: ", e);
		} finally {
			log.info("Fin pull");
		}
	}
}
