package stc12.generics;

import org.jetbrains.annotations.Contract;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Домашнее задание урок 4
 * Создать класс ObjectBox, который будет хранить коллекцию Object. В нем должны быть все методы из MathBox.
 * Методы должны работать корректно когда все элементы коллекции-Number. Если в коллекции есть не Number,
 * должен бросаться Exception, разработанный самостоятельно.
 * У класса должен быть метод addObject, добавляющий объект в коллекцию.
 * У класса должен быть метод deleteObject, проверяющий наличие объекта в коллекции.
 * Должен быть метод dump, выводящий содержимое коллекции в строку.
 *
 * @param <T>
 */
public class ObjectBox <T> {
    private Collection<T> collection = new ArrayList<>();

    /**
     * Add object to collection. Check to be Number.
     *
     * @param object Объект для добавления в коллекцию.
     */
    public void addObject(T object) throws NotNumberException {
        this.validate(object);
        collection.add(object);
    }

    /**
     * Delete object from collection.
     *
     * @param object
     */
    public void delete(T object) {
        collection.remove(object);
    }

    /**
     * Generate
     *
     * @return String, containing collection objects.
     */
    public String dump() {
        return collection.toString();
    }

    private void validate(T object) throws NotNumberException {
        if (!(object instanceof Number)) {
            throw new NotNumberException();
        }
    }
}
