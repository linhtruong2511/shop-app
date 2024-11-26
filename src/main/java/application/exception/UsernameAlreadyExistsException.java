package application.exception;

public class UsernameAlreadyExistsException extends CustomException{
    public UsernameAlreadyExistsException(String message){
        super(message, 1007);
    }
}
