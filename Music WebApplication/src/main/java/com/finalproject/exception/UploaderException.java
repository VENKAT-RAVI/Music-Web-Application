package com.finalproject.exception;

public class UploaderException extends Exception {

	public UploaderException(String message)
	{
		super("UploaderException-"+message);
	}
	
	public UploaderException(String message, Throwable cause)
	{
		super("UploaderException-"+message,cause);
	}
	
}
