package application.exception;

public class HttpRequestMethodNotSupportedException extends CustomException{
    public HttpRequestMethodNotSupportedException(String message){
        super(message, 1008);
    }
}
