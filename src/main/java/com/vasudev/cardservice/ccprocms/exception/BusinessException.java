/**
 * 
 */
package com.vasudev.cardservice.ccprocms.exception;

/**
 * @author vasudev007
 *
 */
public class BusinessException extends Exception {

	private static final long serialVersionUID = -470180507998010368L;

	public static final String INVALID_CC_NUM = "Invalid credit card number!!";
	public static final String DUPLICATE_CC_NUM = "Duplicate credit card number!!";

	public BusinessException() {
		super();
	}

	public BusinessException(final String message) {
		super(message);
	}
}
