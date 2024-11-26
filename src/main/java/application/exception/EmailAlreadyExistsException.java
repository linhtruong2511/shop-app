package application.exception;

public class EmailAlreadyExistsException extends CustomException{
    public EmailAlreadyExistsException(String message){
        super(message, 1004);
    }
}
