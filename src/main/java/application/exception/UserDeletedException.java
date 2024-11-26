package application.exception;

public class UserDeletedException extends CustomException{
    public UserDeletedException(String message){
        super(message, 1006);
    }
}
