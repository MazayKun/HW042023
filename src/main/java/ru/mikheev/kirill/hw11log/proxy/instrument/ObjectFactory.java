package ru.mikheev.kirill.hw11log.proxy.instrument;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class ObjectFactory {

    @SuppressWarnings("unchecked")
    public static <T> T createInstance(Class<T> targetInterface, Class<? extends T> realization, Object... constructorArgs) {
        Class<?>[] argsTypes = new Class<?>[constructorArgs.length];
        for (int i = 0; i < constructorArgs.length; i++) {
            argsTypes[i] = constructorArgs[i].getClass();
        }

        Constructor<? extends T> constructor;
        try {
            constructor = realization.getConstructor(argsTypes);
            T realInstance = constructor.newInstance(constructorArgs);
            return (T) Proxy.newProxyInstance(
                    ObjectFactory.class.getClassLoader(),
                    new Class<?>[]{targetInterface},
                    new LogMethodParametersInvocationHandler(realInstance));
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Error during constructor constructor with arguments extraction: " + Arrays.toString(argsTypes), e);
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException("Error during during instance creation" + targetInterface, e);
        }
    }


}
