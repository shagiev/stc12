package stc12.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

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
        return method.invoke(mathBox, args);
    }
}
