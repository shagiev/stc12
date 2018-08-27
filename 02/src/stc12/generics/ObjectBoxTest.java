package stc12.generics;

import static org.junit.jupiter.api.Assertions.*;

class ObjectBoxTest {

    @org.junit.jupiter.api.Test
    void testAddDeleteDump() {
        ObjectBox<Number> objectBox = new ObjectBox<>();
        assertEquals("[]", objectBox.dump());

        objectBox.addObject(5);
        objectBox.addObject(10.5);
        assertEquals("[5, 10.5]", objectBox.dump());

        objectBox.delete(5);
        assertEquals("[10.5]", objectBox.dump());
    }
}