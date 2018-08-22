package first;

public class MyDivizionByZeroException extends ArithmeticException {
    public String getMessage() {
        return "Нельзя делить на ноль.";
    }
}
