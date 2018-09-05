package stc12.proxy;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class MathBoxInvocationHandler implements InvocationHandler {
    private MathBox mathBox;

    public MathBoxInvocationHandler(MathBox mathBox) {
        this.mathBox = mathBox;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (mathBox.getClass().getAnnotation(Logged.class) != null) {
            System.out.println("Вызван метод " + method.getName());
        }

        Method realMethod = null;
        try {
            realMethod = mathBox.getClass().getMethod(method.getName());
        } catch (NoSuchMethodException exception) {
            return method.invoke(mathBox, args);
        }
        if (realMethod.isAnnotationPresent(ClearData.class)) {
            Field collectionField = mathBox.getClass().getDeclaredField("collection");
            collectionField.setAccessible(true);
            collectionField.set(mathBox, new ArrayList<Integer>());
        }
        return method.invoke(mathBox, args);
    }
}
