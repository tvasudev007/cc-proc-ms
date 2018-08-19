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

	public BusinessException() {
		super();
	}

	public BusinessException(final String message) {
		super(message);
	}
}
