package stc12.generics;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ObjectBoxTest {

    @Test
    void testAddDeleteDump() {
        ObjectBox<Number> objectBox = new ObjectBox<>();
        assertEquals("[]", objectBox.dump());

        try {
            objectBox.addObject(5);
            objectBox.addObject(10.5);
        } catch (NotNumberException e) {
            fail("Exception thrown on number values");
        }

        assertEquals("[5, 10.5]", objectBox.dump());

        objectBox.delete(5);
        assertEquals("[10.5]", objectBox.dump());
    }

    @Test
    void testIncorrectTypeException() {
        ObjectBox<String> objectBox = new ObjectBox<>();
        Throwable exception = assertThrows(NotNumberException.class, () -> {
            objectBox.addObject("hello");
        });

        assertEquals("Incorrect object type. Must extends Number", exception.getMessage());
    }
}