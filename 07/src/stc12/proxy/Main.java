package stc12.proxy;

import java.lang.reflect.Proxy;

public class Main {
    public static void main(String[] args) {
        Integer[] numbers = {3, 5, 10, 2, 1, 7};
        MathBox realMathBox = new MathBox(numbers);
        MathBoxInterface mathBoxProxy = (MathBoxInterface) Proxy.newProxyInstance(
                MathBoxInvocationHandler.class.getClassLoader(),
                new Class[] {MathBoxInterface.class},
                new MathBoxInvocationHandler(realMathBox)
        );


        System.out.println(mathBoxProxy);
        System.out.print("Отсортируем. ");
        mathBoxProxy.sort();
        System.out.println("Получилось: " + mathBoxProxy);
        System.out.println("Сумма всех элементов: " + mathBoxProxy.summator());

        System.out.print("Теперь поделим все элементы на 3 нацело: ");
        System.out.println(mathBoxProxy.splitter(3));


        System.out.println("Удалим число 5: ");
        mathBoxProxy.remove(5);
        System.out.println(mathBoxProxy);

        System.out.println("Попробуем очистку коллекции по аннотации.");
        mathBoxProxy.clearContainer();
        System.out.println("Теперь содержимое коллекции: " + mathBoxProxy);
    }
}
