package com.tacticlogistics.integrador.ftp;

import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
@Component
@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
@Data
@Slf4j
public class FTPConnectionPool {
	private Queue<FTPClient> freeConnections = new ConcurrentLinkedQueue<>();
	private String host;
	private int port = 21;
	private String username;
	private String password;
	private int maxConn;
	private int timeout = 5 * 60 * 1000;
	private int checkedOut;

	
	public FTPConnectionPool() {
		super();
	}
	
	public FTPConnectionPool(String host, int port, String username, String password, int maxConn, int timeout) {
		this.host = host;
		this.port = port;
		this.username = username;
		this.password = password;
		this.maxConn = maxConn;
		this.timeout = timeout;
		initPool();
	}

	public FTPClient getConnection() {
		FTPClient ftp = null;
		synchronized (freeConnections) {
			if (freeConnections.size() > 0) {
				ftp = (FTPClient) freeConnections.poll();
				if (!ftp.isConnected()) {
					log.info("FTPConnectionPool");
					ftp = getConnection();
				}
				/* Compruebe que el servicio FTP este disponible, 
				 * si lo está disponible, devuelve una conexión , 
				 * de lo contrario, crea una nueva conexión */
				boolean isValid = true;
				try {
					if (!ftp.sendNoOp()) {
						int replyCode = ftp.getReplyCode();
						if (FTPReply.CANNOT_OPEN_DATA_CONNECTION == replyCode) {
							isValid = false;
							System.out.println("Reply -| " + replyCode);
							// 断开连接
							ftp.disconnect();
						}
					}
				} catch (IOException ex) {
					isValid = false;
				}
				if (!isValid) {
					ftp = newConnection();
				}
			} else if (maxConn == 0 || checkedOut < maxConn) {
				ftp = newConnection();
			}
		}
		if (null != ftp) {
			checkedOut++;
		}
		return ftp;
	}

	public void freeConnection(FTPClient ftp) {
		log.info("freeConnection");
		synchronized (freeConnections) {
			freeConnections.add(ftp);
			checkedOut--;
		}
	}
	
	public void release() {
		for (int i = freeConnections.size() - 1; i >= 0; i--) {
			FTPClient ftp = (FTPClient) freeConnections.poll();
			try {
				ftp.disconnect();
			} catch (IOException ex) {
				log.error("FTPConnectionPool",ex);
			}
			freeConnections.clear();
			checkedOut = 0;
		}
	}

	private void initPool() {
		int n = this.maxConn % 1;
		if (n == 0) {
			n = 1;
		}
		for (int i = 0; i < n; i++) {
			FTPClient f = newConnection();
			if (null != f) {
				freeConnections.add(f);
			}
		}
	}

	private FTPClient newConnection() {
		FTPClient ftpClient = new FTPClient();
		ftpClient.setDataTimeout(this.getTimeout());
		ftpClient.setConnectTimeout(this.getTimeout());
		try {
			ftpClient.setControlEncoding("UTF-8");
			ftpClient.connect(host, port);
			log.info(ftpClient.getReplyString());
		} catch (IOException e) {
			String error = "FTPConnectionPool {host=" + host + ",Port=" + port + "}, al establecer una conexión!"
					+ e.getMessage();
			log.error(error);
			throw new RuntimeException(error, e);
		}
		try {
			if (ftpClient.login(username, password)) {
				if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
					try {
						ftpClient.disconnect();
					} catch (IOException e) {
						String error = "Exception: {Username=" + username + "}, al realizar la desconexión";
						log.error(error);
					}
				} else {
					ftpClient.enterLocalPassiveMode();
				}
				ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
				log.info(ftpClient.getReplyString());
			}
		} catch (IOException e) {
			String error = "Exception:La autenticación de usuario falló, compruebe el usuario y la contraseña {Username="
					+ username + "}";
			log.error(error);
			throw new RuntimeException(error + e.getMessage());
		}
		return ftpClient;
	}
}
