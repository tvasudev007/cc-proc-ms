package com.vasudev.cardservice.ccprocms.exception;

/**
 * Exception thrown wherever business code determines when appropriate. Can
 * transport a message key and optional arguments to the message. This exception
 * must be catched and processed in the boundary tier.
 */
public class BusinessException extends Exception {

	private static final long serialVersionUID = 5557428774528282654L;
	public static final String INVALID_CC_NUM = "Invalid credit card number!!";
	public static final String DUPLICATE_CC_NUM = "Duplicate credit card number!!";
	public static final String INVALID_CC_LIMIT = "Invalid credit card limit!!";
	public static final String INVALID_CC_NAME = "Invalid credit card name!!";
	public static final String INVALID_CC_BALANCE = "Balance shoulde be set to zero!!";

	public BusinessException() {
		super();
	}

	/**
	 * @param message
	 */
	public BusinessException(final String message) {
		super(message);
	}
	
	/**
	 * 
	 * @param messageKey Message key
	 * @param cause
	 */
	public BusinessException(String messageKey, Throwable cause) {
		super(messageKey, cause);
	}

}
