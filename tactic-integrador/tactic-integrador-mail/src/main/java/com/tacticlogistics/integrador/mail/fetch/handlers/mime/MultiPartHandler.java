package com.tacticlogistics.integrador.mail.fetch.handlers.mime;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.Multipart;

import org.springframework.stereotype.Component;

import com.tacticlogistics.integrador.mail.fetch.handlers.MessageHandler;
import com.tacticlogistics.integrador.mail.fetch.handlers.MessageRequest;

import lombok.val;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class MultiPartHandler extends MessageHandler {

	@Override
	protected boolean canHandleRequest(MessageRequest request) {
		try {
			if (request.getPart().isMimeType("multipart/*")) {
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
			Multipart mp = (Multipart) request.getPart().getContent();
			int count = mp.getCount();
			for (int i = 0; i < count; i++) {
				val result = request.getService().getContents(mp.getBodyPart(i));
				request.getContents().addAll(result);
			}
		} catch (MessagingException | IOException e) {
			log.error("", e);
		}
	}
}