package project;

public class InvalidIDException extends Exception {
    public InvalidIDException(String errorMsg){
        super(errorMsg);
    }
}
