package application.exception;

public class RoleNotExistsException extends CustomException{
    public RoleNotExistsException(String message){
        super(message, 1005);
    }
}
