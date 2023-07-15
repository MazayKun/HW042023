package ru.mikheev.kirill.hw11log.proxy.instrument;

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
        if(method.isAnnotationPresent(Log.class)) {
            for(Object arg : args) {
                System.out.println(String.format("executed method: %s, param type: %s, param value: %s", method.getName(), arg.getClass(), arg));
            }
        }
        return result;
    }
}
