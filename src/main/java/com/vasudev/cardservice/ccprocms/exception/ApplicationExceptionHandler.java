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
 * To handle all the exceptions and sent response to REST clients
 * 
 * @author vasudev007
 *
 */
@ControllerAdvice
public class ApplicationExceptionHandler {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	/**
	 * To handle json parse exceptions
	 * 
	 * @param httpme
	 * @param request
	 * @return
	 */
	@ExceptionHandler(HttpMessageNotReadableException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public @ResponseBody ExceptionResponse handlejsonParseException(final HttpMessageNotReadableException httpme, final HttpServletRequest request) {
		log.error("HttpMessageNotReadableException: {}", httpme.getMessage());
		return new ExceptionResponse(httpme.getMessage(), request.getRequestURI());
	}

	/**
	 * To handle business exceptions
	 * 
	 * @param be
	 * @param request
	 * @return
	 */
	@ExceptionHandler(BusinessException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public @ResponseBody ExceptionResponse handleBusinessException(final BusinessException be, final HttpServletRequest request) {
		log.error("HttpMessageNotReadableException: {}", be.getMessage());
		return new ExceptionResponse(be.getMessage(), request.getRequestURI());
	}

	/**
	 * To handle all other exceptions
	 * 
	 * @param exception
	 * @param request
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody ExceptionResponse handleAllException(final Exception exception, final HttpServletRequest request) {
		log.error("Exception: {}", exception);
		return new ExceptionResponse(exception.getMessage(), request.getRequestURI());
	}

	// TODO : Handle other specific exceptionss
}
