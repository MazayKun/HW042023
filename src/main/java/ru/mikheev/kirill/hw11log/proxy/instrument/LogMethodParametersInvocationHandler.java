package ru.mikheev.kirill.hw11log.proxy.instrument;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

class LogMethodParametersInvocationHandler implements InvocationHandler {

    private final Object realObject;

    public LogMethodParametersInvocationHandler(Object realObject) {
        this.realObject = realObject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        Object result = method.invoke(realObject, args);
        if (method.isAnnotationPresent(Log.class)) {
            if(args == null) {
                System.out.println(String.format("executed method: %s with 0 parameters", method.getName()));
                return result;
            }
            Class<?> argType;
            for (Object arg : args) {
                argType = arg.getClass();
                if(argType.toString().charAt(6) == '[') {
                    System.out.print(String.format("executed method: %s, param type: array of %s, param value: [", method.getName(), argType.getComponentType()));
                    int arrayLength = Array.getLength(arg);
                    for(int i = 0; i < arrayLength; i++) {
                        System.out.print(Array.get(arg, i));
                        if(i != arrayLength - 1) System.out.print(", ");
                    }
                    System.out.println(']');
                }else{
                    System.out.println(String.format("executed method: %s, param type: %s, param value: %s", method.getName(), arg.getClass(), arg));
                }
            }
        }
        return result;
    }
}
