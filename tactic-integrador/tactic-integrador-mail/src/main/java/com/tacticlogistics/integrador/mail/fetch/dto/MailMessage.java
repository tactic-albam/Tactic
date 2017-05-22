package com.tacticlogistics.integrador.mail.fetch.dto;

import java.time.LocalDateTime;
import java.util.List;

import javax.mail.internet.InternetAddress;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@Getter
@Builder
@ToString
public class MailMessage {
	@NonNull
	private List<InternetAddress> senders;

	private String subject;

	private LocalDateTime sentDate;

	@NonNull
	private List<Content> contents;
}
