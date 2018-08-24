package stc12.collections;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

public class MathBox {
    private Collection<Integer> collection = new ArrayList<>();

    public MathBox(Integer[] numbers) {
        collection.addAll(Arrays.asList(numbers));
    }

    @Override
    public String toString() {
        return collection.toString();
    }

    public void sort() {
        Collections.sort((ArrayList<Integer>)collection);
    }

    public int summator() {
        return collection.stream().reduce((v1, v2) -> v1 + v2).orElse(0);
    }

    public Collection<Integer> splitter(int divider) {
        return collection.stream().map(number -> number / divider).collect(Collectors.toList());
    }

    public void remove(Integer number) {
        collection.remove(number);
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        MathBox mathBox = (MathBox) object;
        return java.util.Objects.equals(collection, mathBox.collection);
    }

    public int hashCode() {
        return java.util.Objects.hash(super.hashCode(), collection);
    }
}
