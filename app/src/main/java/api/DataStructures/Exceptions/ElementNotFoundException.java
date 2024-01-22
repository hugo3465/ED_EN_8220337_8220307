package api.dataStructures.Exceptions;

public class ElementNotFoundException extends RuntimeException{
    public ElementNotFoundException() {
        super();
    }

    public ElementNotFoundException(String text) {
        super(text);
    }
}