package application.exception;

public class TokenInvalidException extends CustomException{
    public TokenInvalidException(String message){
        super(message, 1007);
    }
}
