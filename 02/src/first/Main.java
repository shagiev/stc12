package first;

public class Main {
    public static void main(String[] args) {

        MathBox mathBox = new MathBox();

        System.out.println("Привет. Это демонстрация работы класса MathBox.");
        System.out.println("Сумма 1073 и 37 равна " + mathBox.summa(1073, 37));
        System.out.println("Разность 110 и 73 равна " + mathBox.diff(110, 73));
        System.out.println("Тестируем деление 107 на 23: " + mathBox.dividerExceptionInside(107, 23));
        System.out.println( "Тестируем делением на ноль с отловом ошибки.");
        System.out.println( "107/0 = " + mathBox.dividerExceptionInside(107, 0) );
        System.out.println("Тестируем деление на ноль с пробросом исключения.");
        try {
            mathBox.divider(107, 0);
        } catch (MyDivizionByZeroException exception) {
            System.out.println("Отловили исключение: " + exception.getMessage());
        }
        System.out.println("Тестируем факториал. 10! = " + mathBox.factorial(10));

        System.out.println("А теперь пробуем сломать систему. Посчитаем факториал для 1000000");
        System.out.println(mathBox.factorial(1000000));
    }
}