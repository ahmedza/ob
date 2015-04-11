package com.onebridge.exception;

import com.parse.ParseException;

public class DataException extends ParseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9084586952409402395L;

	public DataException(int theCode, String theMessage) {
		super(theCode, theMessage);
		// TODO Auto-generated constructor stub
	}
	
	public DataException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	

}
