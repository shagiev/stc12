package stc12.collections;

public class Main {
    public static void main(String[] args) {
        Integer[] numbers = {3, 5, 10, 2, 1, 7};
        MathBox mathBox = new MathBox(numbers);
        System.out.println(mathBox);
        System.out.print("Отсортируем. ");
        mathBox.sort();
        System.out.println("Получилось: " + mathBox);
        System.out.println("Сумма всех элементов: " + mathBox.summator());

        System.out.print("Теперь поделим все элементы на 3 нацело: ");
        System.out.println(mathBox.splitter(3));


        System.out.println("Удалим число 5: ");
        mathBox.remove(5);
        System.out.println(mathBox);
    }
}
