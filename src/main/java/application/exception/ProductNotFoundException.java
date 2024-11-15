package application.exception;

public class ProductNotFoundException extends CustomException{
	public ProductNotFoundException(String message) {
		super(message, 1001);
	}
}
