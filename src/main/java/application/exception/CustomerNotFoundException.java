package application.exception;

public class CustomerNotFoundException extends CustomException{
    public CustomerNotFoundException(String message){
        super(message, 1001);
    }
}
