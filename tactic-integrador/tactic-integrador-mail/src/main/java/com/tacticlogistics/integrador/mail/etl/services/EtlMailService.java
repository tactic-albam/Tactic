package com.tacticlogistics.integrador.mail.etl.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tacticlogistics.integrador.mail.etl.handlers.MailMessageHandler;
import com.tacticlogistics.integrador.mail.fetch.dto.MailMessage;

import lombok.Getter;

@Service
@Getter
public class EtlMailService {
	@Autowired
	private List<MailMessageHandler> handlers;

	private MailMessageHandler rootChain = null;

	public void transformar(MailMessage mailMessage) {
		MailMessageHandler handler = getRootResponsabilityChain();

		handler.receiveRequest(mailMessage);
	}

	private MailMessageHandler getRootResponsabilityChain() {
		if (rootChain == null) {
			for (int i = 0; i < handlers.size(); i++) {
				if (rootChain == null) {
					rootChain = this.handlers.get(i);
				} else {
					rootChain.setNextHandler(this.handlers.get(i));
				}
			}
		}
		return rootChain;
	}
}
