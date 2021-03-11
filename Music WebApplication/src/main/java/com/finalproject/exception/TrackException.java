package com.finalproject.exception;

public class TrackException extends Exception {
	
	public TrackException(String message)
	{
		super("TrackException-"+message);
	}
	
	public TrackException(String message, Throwable cause)
	{
		super("TrackException-"+message,cause);
	}
}
