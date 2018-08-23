package com.vasudev.cardservice.ccprocms.exception;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Global ExceptionResponse DTO class
 * 
 * @author vasudev007
 *
 */
public class ExceptionResponse {

	private String message;

	private String reqURI;

	public ExceptionResponse() {
	}

	public ExceptionResponse(String message, String reqURI) {
		this.message = message;
		this.reqURI = reqURI;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getReqURI() {
		return reqURI;
	}

	public void setReqURI(String reqURI) {
		this.reqURI = reqURI;
	}

	@Override
	public String toString() {
		// @formatter:off
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).append("message", this.message)
				.append("Requested URI", this.reqURI).toString();
		// @formatter:on
	}

}
