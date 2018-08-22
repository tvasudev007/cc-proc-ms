/**
 * 
 */
package com.vasudev.cardservice.ccprocms.exception;

/**
 * @author vasudev007
 *
 */
public class BusinessException extends Exception {

	public static final String INVALID_CC_NUM = "Invalid credit card number!!";
	public static final String DUPLICATE_CC_NUM = "Duplicate credit card number!!";
	public static final String INVALID_CC_LIMIT = "Invalid credit card limit!!";
	public static final String INVALID_CC_NAME = "Invalid credit card name!!";
	public static final String INVALID_CC_BALANCE = "Balance shoulde be set to zero!!";

	public BusinessException() {
		super();
	}

	public BusinessException(final String message) {
		super(message);
	}
}
