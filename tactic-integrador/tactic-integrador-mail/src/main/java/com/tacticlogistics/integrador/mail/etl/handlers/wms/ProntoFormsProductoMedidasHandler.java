package com.tacticlogistics.integrador.mail.etl.handlers.wms;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.tacticlogistics.ClienteCodigoType;
import com.tacticlogistics.integrador.mail.etl.handlers.MailMessageHandler;
import com.tacticlogistics.integrador.mail.fetch.dto.MailMessage;

import lombok.Getter;
import lombok.val;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@Getter
public class ProntoFormsProductoMedidasHandler extends MailMessageHandler {
	@Value("${etl.directorios.locales}")
	private String[] directoriosLocales;

	@Value("${etl.directorio.entradas}")
	private String subDirectorioDeEntradas;

	private final Path directorio = Paths.get("WMS/HUELLAS");

	private static final String TAB = "\t";

	private static final String SUBJECT = "PRONTOFORMS HUELLAS";

	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HHmmss");

	@Override
	protected boolean canHandleRequest(MailMessage request) {
		if (StringUtils.contains(request.getSubject().toUpperCase(), getSubject())) {
			if (request.getContents().size() == 1) {
				return true;
			}
		}
		return false;
	}

	private String getSubject() {
		return SUBJECT;
	}

	@Override
	protected void handleRequest(MailMessage request) {
		Validate.notEmpty(request.getContents());
		String body = request.getContents().get(0).getBody();
		Validate.notEmpty(body);
		body = clean(body);
		body = unpivot(body);
		write(request, body);
	}

	private String clean(String body) {
		// @formatter:off
		return body
				.replaceAll("<p>", "")
				.replaceAll("</p>", "")
				.replaceAll("\\r\\n", "\n");
		// @formatter:on
	}

	private String unpivot(String body) {
		String lineas[] = body.split("\\n");
		StringBuilder headers = new StringBuilder();
		StringBuilder data = new StringBuilder();

		for (int i = 0; i < lineas.length; i++) {
			val values = lineas[i].split("=");

			headers.append(values[0].trim()).append(TAB);
			data.append(((values.length < 2) ? "" : values[1]).trim()).append(TAB);
		}

		headers.deleteCharAt(headers.length() - 1);
		data.deleteCharAt(data.length() - 1);

		headers.append(StringUtils.LF).append(data.toString()).append(StringUtils.LF);

		return headers.toString();
	}

	public void write(MailMessage request, String datos) {
		int i = Math.abs((new Random()).nextInt());
		String filename = request.getSentDate().format(formatter) + "-" + String.valueOf(i) + "-HUELLAS.TXT";

		List<Path> paths = getDirectorios();

		for (Path root : paths) {
			if (!Files.exists(root)) {
				continue;
			}

			System.out.println("this.getDirectorio():" + this.getDirectorio().toString());
			// @formatter:off
			Path path = root
					.resolve(ClienteCodigoType.TACTIC.toString())
					.resolve(this.getSubDirectorioDeEntradas())
					.resolve(this.getDirectorio())
					.resolve(filename);
			// @formatter:on
			System.out.println("path:" + path.toString());

			
			try (BufferedWriter bw = new BufferedWriter(new FileWriter(path.toString()))) {
				bw.write(datos);
			} catch (IOException e) {
				log.error("", e);
			}
		}
	}

	private List<Path> getDirectorios() {
		List<Path> paths = new ArrayList<>();
		for (String directorio : directoriosLocales) {
			paths.add(Paths.get(directorio));
		}
		return paths;
	}
}
