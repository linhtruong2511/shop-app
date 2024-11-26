package application.exception;

public class UserNotFoundException extends CustomException{
    public UserNotFoundException(String message){
        super(message, 1001);
    }
}
