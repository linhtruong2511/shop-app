package application.exception;

public class SupplierNotFoundException extends CustomException{
    public SupplierNotFoundException(String message){
        super(message, 1001);
    }
}
