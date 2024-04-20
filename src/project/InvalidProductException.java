package project;

public class InvalidProductException extends Exception {
    public InvalidProductException(String errorMsg){
        super(errorMsg);
    }
}
