package first;

public class MyDivizionByZeroException extends ArithmeticException {

    public MyDivizionByZeroException(Throwable exception) {
        super("Zero divizion");
    }
}
