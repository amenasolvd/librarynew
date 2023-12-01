package exceptions;

public class BorrowingBookLimitOverException extends Exception {

    public BorrowingBookLimitOverException(String e){
        super(e);
    }
}