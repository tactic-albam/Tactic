package com.tacticlogistics.ext.dicermex.aud.despachos;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class NotificacionService {
	private static final DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

	@Value("${dicermex.mail.from}")
	private String mailFrom;

	@Value("${dicermex.mail.from.personal}")
	private String mailFromPersonal;

	@Value("#{'${dicermex.mail.to}'.split(',')}")
	private String[] mailTo;
	
	@Value("#{'${dicermex.mail.cc}'.split(',')}")
	private String[] mailCc;

	@Autowired
	private JavaMailSender mailSender;

	public void notificar(File file, LocalDateTime fechaInicio, LocalDateTime fechaFin) {
		try {
			MimeMessage message = crearMimeMessage(file, fechaInicio, fechaFin);

			this.mailSender.send(message);
		} catch (RuntimeException e) {
			throw e;
		} finally {
			file.delete();
		}
	}

	private MimeMessage crearMimeMessage(File file, LocalDateTime fechaInicio, LocalDateTime fechaFin) {
		MimeMessage message = this.mailSender.createMimeMessage();
		String subject = String.format("INFORME DE AUDITORIA DE DESPACHOS: Desde %s Hasta %s",
				fechaInicio.format(format), fechaFin.format(format));
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(mailFrom, mailFromPersonal);
			helper.setTo(mailTo);
			helper.setCc(mailCc);
			helper.setSubject(subject);
			helper.setText("Inconsistencias detectadas en las solicitudes de despacho");
			helper.addAttachment(file.getName(), file);
			return message;
		} catch (MessagingException | UnsupportedEncodingException e) {
			throw new RuntimeException("Ocurrio una excepción al crear el mensaje de notificación", e);
		}

	}
}
