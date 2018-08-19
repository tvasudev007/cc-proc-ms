package com.vasudev.cardservice.ccprocms.exception;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author vasudev007
 *
 */
@ControllerAdvice
public class ApplicationExceptionHandler {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@ExceptionHandler(HttpMessageNotReadableException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public @ResponseBody ExceptionResponse handlejsonParseException(final HttpMessageNotReadableException exception,
			final HttpServletRequest request) {
		log.error("HttpMessageNotReadableException: {}", exception.getMessage());
		return new ExceptionResponse(exception.getMessage(), request.getRequestURI());
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody ExceptionResponse handleAllOtherException(final Exception exception,
			final HttpServletRequest request) {
		log.error("Exception: {}", exception);
		return new ExceptionResponse(exception.getMessage(), request.getRequestURI());
	}

}
