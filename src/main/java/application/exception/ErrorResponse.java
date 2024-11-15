package application.exception;

public class ErrorResponse {
	private String message;
	private int code;
	
	public ErrorResponse(String message, int code) {
		this.code = code;
		this.message = message;
	}
	
	public int getCode() {
		return code;
	} 

	public String getMessage() {
		return message;
	}
}
