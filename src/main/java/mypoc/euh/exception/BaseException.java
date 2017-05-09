package mypoc.euh.exception;

public class BaseException extends Exception {

	private static final long serialVersionUID = -6506792676172876523L;
	
	private String message;
	
	public BaseException() {
		
	}
	
	public BaseException(String message) {
		this.message = message;
	}
	
	@Override
	public String toString() {
		return message;
	}
	
	@Override
	public String getMessage() {
		return toString();
	}
	
}
