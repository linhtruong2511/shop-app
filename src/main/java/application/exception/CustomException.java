package application.exception;

public class CustomException extends RuntimeException {
	private String message;
	private int code;

	public CustomException(String message, int code) {
		super(message);
		this.message = message;
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

}
