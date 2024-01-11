package Exceptions;

public class EmptyCollectionException extends RuntimeException{
    public EmptyCollectionException() {
        super();
    }

    public EmptyCollectionException(String text) {
        super(text);
    }
}
