package application.exception;

import application.entity.Product;

public class ProductOutOfStock extends CustomException{
    public ProductOutOfStock(String message){
        super(message, 1111);
    }
}
