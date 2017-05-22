package com.tacticlogistics.integrador.mail.fetch.services;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.InternetAddress;
import javax.mail.search.FlagTerm;
import javax.mail.search.SearchTerm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.tacticlogistics.integrador.mail.etl.services.EtlMailService;
import com.tacticlogistics.integrador.mail.fetch.dto.Content;
import com.tacticlogistics.integrador.mail.fetch.dto.MailMessage;
import com.tacticlogistics.integrador.mail.fetch.handlers.MessageHandler;
import com.tacticlogistics.integrador.mail.fetch.handlers.MessageRequest;

import lombok.Getter;
import lombok.val;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Getter
public class FetchMailService {

	private static final String INBOX = "INBOX";

	@Value("${mail.inbox.host}")
	private String host;

	@Value("${mail.inbox.port}")
	private String port;

	@Value("${mail.inbox.storeType}")
	private String storeType;

	@Value("${mail.inbox.user}")
	private String user;

	@Value("${mail.inbox.password}")
	private String password;

	@Autowired
	private List<MessageHandler> handlers;

	private MessageHandler rootChain = null;

	@Autowired
	private EtlMailService etlService;

	public void run() {
		Store store = null;
		Folder folder = null;

		try {
			Properties properties = getProperties();
			Session emailSession = Session.getDefaultInstance(properties);

			// create the store object and connect with the server
			store = emailSession.getStore(getStoreType());
			store.connect(getHost(), getUser(), getPassword());

			// create the folder object and open it
			folder = store.getFolder(INBOX);
			folder.open(Folder.READ_WRITE);

			// retrieve the messages from the folder in an array and print it
			SearchTerm searchTerm = getSearchTerm();
			Message[] messages = folder.search(searchTerm);
			log.info("messages.length = {}", messages.length);

			List<MailMessage> list = new ArrayList<>();
			for (int i = 0; i < messages.length; i++) {
				log.info("{}", i);
				list.add(getMailMessage(messages[i]));
			}

			for (MailMessage message : list) {
				etlService.transformar(message);
			}

		} catch (MessagingException | IOException e) {
			log.error("", e);
		} finally {
			// close the store and folder objects
			closeFolder(folder);
			closeStore(store);
		}
	}

	private Properties getProperties() {
		Properties properties = new Properties();
		properties.put("mail.imap.host", getHost());
		properties.put("mail.imap.port", getPort());
		properties.put("mail.store.protocol", getStoreType());
		properties.put("mail.imap.ssl.enable", "true");
		return properties;
	}

	private MailMessage getMailMessage(Message message) throws MessagingException, IOException {
		MailMessage result;
		val senders = getSenders(message);
		val subject = getSubject(message);
		val sentDate = getSentLocalDateTime(message);
		val contents = getContents(message);

		// @formatter:off
		result = MailMessage
				.builder()
				.senders(senders)
				.subject(subject)
				.sentDate(sentDate)
				.contents(contents)
				.build();
		// @formatter:on

		return result;
	}

	private List<InternetAddress> getSenders(Message message) {
		List<InternetAddress> result = new ArrayList<>();

		Address[] addresses;
		try {
			addresses = message.getFrom();
			if (addresses != null) {
				for (int i = 0; i < addresses.length; i++) {
					if (addresses[i] instanceof InternetAddress) {
						result.add(((InternetAddress) addresses[i]));
					}
				}
			}
		} catch (MessagingException e) {
			log.error("", e);
		}

		return result;
	}

	private String getSubject(Message message) {
		String result = "";
		try {
			if (message.getSubject() != null) {
				result = message.getSubject();
			}
		} catch (MessagingException e) {
			log.error("", e);
		}
		return result;
	}

	private LocalDateTime getSentLocalDateTime(Message message) {
		LocalDateTime result = null;
		Date sentDate;
		try {
			sentDate = message.getSentDate();
			result = LocalDateTime.ofInstant(sentDate.toInstant(), ZoneId.systemDefault());
		} catch (MessagingException e) {
			log.error("", e);
		}
		return result;
	}

	public List<Content> getContents(Part p) {
		List<Content> result = new ArrayList<>();

		MessageRequest request;
		request = new MessageRequest();
		request.setService(this);
		request.setPart(p);

		MessageHandler handler = getRootResponsabilityChain();

		handler.receiveRequest(request);
		result.addAll(request.getContents());
		return result;
	}

	private void closeFolder(Folder folder) {
		if (folder != null) {
			if (folder.isOpen()) {
				try {
					folder.close(false);
				} catch (MessagingException e) {
					log.error("", e);
				}
			}
		}
	}

	private void closeStore(Store store) {
		if (store != null) {
			if (store.isConnected()) {
				try {
					store.close();
				} catch (MessagingException e) {
					log.error("", e);
				}
			}
		}
	}

	private MessageHandler getRootResponsabilityChain() {
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

	private SearchTerm getSearchTerm() {
		Flags seen = new Flags(Flags.Flag.SEEN);
		FlagTerm unseenFlagTerm = new FlagTerm(seen, false);
		return unseenFlagTerm;
	}
}
