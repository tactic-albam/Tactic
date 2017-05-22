package com.tacticlogistics.integrador.mail.fetch.handlers.mime;

import java.io.IOException;
import java.io.InputStream;

import javax.mail.MessagingException;

import org.springframework.stereotype.Component;

import com.tacticlogistics.integrador.mail.fetch.handlers.MessageHandler;
import com.tacticlogistics.integrador.mail.fetch.handlers.MessageRequest;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AttachmentHandler extends MessageHandler {

	@Override
	protected boolean canHandleRequest(MessageRequest request) {
		try {
			Object o = request.getPart().getContent();
			if (o instanceof InputStream) {
				return true;
			}
		} catch (MessagingException | IOException e) {
			log.error("", e);
		}
		return false;
	}

	@Override
	protected void handleRequest(MessageRequest request) {
		log.info("attachment");
		// check if the content is a nested message
		// result.addAll(getContents((Part) p.getContent()));
		
//		System.out.println("This is just an input stream");
//		System.out.println("---------------------------");
//		InputStream is = (InputStream) o;
//		is = (InputStream) o;
//		int c;
		
		// while ((c = is.read()) != -1)
		// System.out.write(c);
	}
}
