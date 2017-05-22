package com.tacticlogistics.integrador.mail.fetch.handlers.mime;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.Part;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.tacticlogistics.integrador.mail.fetch.dto.Content;
import com.tacticlogistics.integrador.mail.fetch.handlers.MessageHandler;
import com.tacticlogistics.integrador.mail.fetch.handlers.MessageRequest;

import lombok.val;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class TextPlainHandler extends MessageHandler {

	@Override
	protected boolean canHandleRequest(MessageRequest request) {
		try {
			if (request.getPart().isMimeType("text/plain")) {
				return true;
			}
			Object o = request.getPart().getContent();
			if (o != null) {
				if (o instanceof String) {
					String val = (String) o;
					if (StringUtils.isNotEmpty(val)) {
						return true;
					}
				}
			}
		} catch (MessagingException | IOException e) {
			log.error("", e);
		}
		return false;
	}

	@Override
	protected void handleRequest(MessageRequest request) {
		// @formatter:off
		val result = Content
				.builder()
				.body(getBody(request.getPart()))
				.build();
		// @formatter:on

		request.getContents().add(result);
	}

	private String getBody(Part p) {
		String result = "";
		try {
			result = (String) p.getContent();
		} catch (MessagingException | IOException e) {
			log.error("", e);
		}
		return result;
	}
}
