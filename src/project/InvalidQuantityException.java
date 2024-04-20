package project;

public class InvalidQuantityException extends Exception {
    public InvalidQuantityException(String errorMsg){
        super(errorMsg);
    }
}
