package org.atom.genarator.exception;

public class DummyFileNotFoundException extends RuntimeException{
	
	public DummyFileNotFoundException(String message) {
		super(message);
	}
	
	public DummyFileNotFoundException(String message,Throwable cause) {
		super(message,cause);
	}

}
