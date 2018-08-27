package stc12.generics;

import java.util.ArrayList;
import java.util.Collection;

public class ObjectBox <T extends Number> {
    private Collection<T> collection = new ArrayList<>();

    public void addObject(T object) {
        collection.add(object);
    }

    public void delete(T object) {
        collection.remove(object);
    }

    public String dump() {
        return collection.toString();
    }
}
