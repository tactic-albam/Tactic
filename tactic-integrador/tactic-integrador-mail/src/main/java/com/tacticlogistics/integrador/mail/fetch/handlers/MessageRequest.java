package com.tacticlogistics.integrador.mail.fetch.handlers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.mail.Part;

import com.tacticlogistics.integrador.mail.fetch.dto.Content;
import com.tacticlogistics.integrador.mail.fetch.services.FetchMailService;

import lombok.Data;
import lombok.NonNull;
import lombok.ToString;

@ToString
@Data
public class MessageRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	@NonNull
	private FetchMailService service;

	@NonNull
	private Part part;

	@NonNull
	private List<Content> contents;

	public MessageRequest() {
		super();
		this.contents = new ArrayList<>();
	}
}