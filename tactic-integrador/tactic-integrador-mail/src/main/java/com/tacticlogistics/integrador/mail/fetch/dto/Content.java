package com.tacticlogistics.integrador.mail.fetch.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Content {
	private String body;

	private String fileName;

	private Object attachment;
}
