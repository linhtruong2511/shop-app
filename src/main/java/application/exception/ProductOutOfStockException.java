package application.exception;

public class ProductOutOfStockException extends CustomException{
    public ProductOutOfStockException(String message){
        super(message, 1111);
    }
}
