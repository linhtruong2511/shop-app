package application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlerProductNotFoundException(ProductNotFoundException e) {
        ErrorResponse error = new ErrorResponse(e.getMessage(), e.getCode());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SupplierNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlerSupplierNotFoundException(SupplierNotFoundException e) {
        ErrorResponse error = new ErrorResponse(e.getMessage(), e.getCode());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlerCustomerNotFoundException(CustomerNotFoundException e) {
        ErrorResponse error = new ErrorResponse(e.getMessage(), e.getCode());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlerOrderNotFoundException(OrderNotFoundException e) {
        ErrorResponse error = new ErrorResponse(e.getMessage(), e.getCode());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlerUserNotFoundException(UserNotFoundException e) {
        ErrorResponse error = new ErrorResponse(e.getMessage(), e.getCode());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RoleNotExistsException.class)
    public ResponseEntity<ErrorResponse> handlerRoleNotExistsException(RoleNotExistsException e) {
        ErrorResponse error = new ErrorResponse(e.getMessage(), e.getCode());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UsernameAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handlerUsernameAlreadyExists(UsernameAlreadyExistsException e) {
        ErrorResponse error = new ErrorResponse(e.getMessage(), e.getCode());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handlerEmailAlreadyExists(EmailAlreadyExistsException e) {
        ErrorResponse error = new ErrorResponse(e.getMessage(), e.getCode());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProductOutOfStockException.class)
    public ResponseEntity<ErrorResponse> handlerProductOutOfStock(ProductOutOfStockException e){
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), e.getCode());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserDeletedException.class)
    public ResponseEntity<ErrorResponse> handlerUserDeletedException(UserDeletedException e){
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), e.getCode());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_GATEWAY);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handlerMethodArgumentNotValidException(MethodArgumentNotValidException e){
        ErrorResponse error = new ErrorResponse(e.getBindingResult().getFieldError().getDefaultMessage(), 1003);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }



    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception e) {
        ErrorResponse error = new ErrorResponse("Internal server error", 500);
        e.printStackTrace();
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
