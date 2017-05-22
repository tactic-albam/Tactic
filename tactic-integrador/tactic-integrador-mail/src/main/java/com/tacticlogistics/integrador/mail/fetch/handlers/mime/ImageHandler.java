package com.tacticlogistics.integrador.mail.fetch.handlers.mime;

import javax.mail.MessagingException;

import org.springframework.stereotype.Component;

import com.tacticlogistics.integrador.mail.fetch.handlers.MessageHandler;
import com.tacticlogistics.integrador.mail.fetch.handlers.MessageRequest;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ImageHandler extends MessageHandler {

	@Override
	protected boolean canHandleRequest(MessageRequest request) {
		try {
			if (request.getPart().isMimeType("image/jpeg")) {
				return true;
			}
			if (request.getPart().getContentType().contains("image/")) {
				return true;
			}
		} catch (MessagingException e) {
			log.error("", e);
		}
		return false;
	}

	@Override
	protected void handleRequest(MessageRequest request) {
		// check if the content is a nested message
		// result.addAll(getContents((Part) p.getContent()));
	}
}
