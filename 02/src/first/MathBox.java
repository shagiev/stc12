package first;

public class MathBox {

    public int summa (Integer a, Integer b) {
       return a + b;
    }

    public int diff (Integer a, Integer b) {
        return a - b;
    }

    public long factorial (Integer a) {
        try {
            return (a > 1) ? (a * this.factorial(a - 1)) : 1;
        } catch (Exception exception) {
            System.out.println("Ошибка: " + exception.getMessage());
            return 0;
        }
    }

    public double dividerExceptionInside(Integer a, Integer b) {
        try {
            return a / b;
        } catch (ArithmeticException exception) {
            System.out.println("Произошла арифрметическая ошибка: " + exception.getMessage());
            return 0;
        }
    }

    public double divider (Integer a, Integer b) throws MyDivizionByZeroException {
        try {
            return a / b;
        } catch (ArithmeticException exception) {
             throw new MyDivizionByZeroException(exception);
        }
    }

}
