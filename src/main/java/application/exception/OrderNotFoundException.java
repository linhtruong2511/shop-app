package application.exception;

public class OrderNotFoundException extends CustomException{
    public OrderNotFoundException(String message){
        super(message, 1001);
    }
}
