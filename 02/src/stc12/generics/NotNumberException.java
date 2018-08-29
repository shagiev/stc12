package stc12.generics;

public class NotNumberException extends Exception {
    @Override
    public String getMessage() {
        return "Incorrect object type. Must extends Number";
    }
}
