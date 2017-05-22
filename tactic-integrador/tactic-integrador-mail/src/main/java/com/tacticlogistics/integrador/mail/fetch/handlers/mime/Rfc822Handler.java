package com.tacticlogistics.integrador.mail.fetch.handlers.mime;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.Part;

import org.springframework.stereotype.Component;

import com.tacticlogistics.integrador.mail.fetch.handlers.MessageHandler;
import com.tacticlogistics.integrador.mail.fetch.handlers.MessageRequest;

import lombok.val;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class Rfc822Handler extends MessageHandler {

	@Override
	protected boolean canHandleRequest(MessageRequest request) {
		try {
			if (request.getPart().isMimeType("message/rfc822")) {
				return true;
			}
		} catch (MessagingException e) {
			log.error("", e);
		}
		return false;
	}

	@Override
	protected void handleRequest(MessageRequest request) {
		try {
			Part part = (Part) request.getPart().getContent();
			val result = request.getService().getContents(part);
			request.getContents().addAll(result);
		} catch (IOException | MessagingException e) {
			log.error("", e);
		}
	}
}
