package org.joy.util;

import lombok.extern.slf4j.Slf4j;
import org.joy.generator.exception.GeneratorException;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ObjectFactory {
    private static List<ClassLoader> externalClassLoaders;

    static {
        externalClassLoaders = new ArrayList<ClassLoader>();
    }

    private ObjectFactory(){
        super();
    }

    public static synchronized void addExternalClassLoader(ClassLoader classLoader) {
        ObjectFactory.externalClassLoaders.add(classLoader);
    }

    @SuppressWarnings("rawtypes")
    public static Class externalClassForName(String type) throws ClassNotFoundException {

        Class clazz;

        for (ClassLoader classLoader : externalClassLoaders) {
            try {
                classLoader.loadClass(type);
                clazz = Class.forName(type, true, classLoader);
                return clazz;
            } catch (ClassNotFoundException e) {
                log.info(e.getMessage(), e);
            }
        }

        return internalClassForName(type);
    }

    public static Class<?> internalClassForName(String type) throws ClassNotFoundException {
        Class<?> clazz = null;

        try {
            ClassLoader cl = Thread.currentThread().getContextClassLoader();
            clazz = Class.forName(type, true, cl);
        } catch (ClassNotFoundException e) {
            log.info(e.getMessage(), e);
        }

        if (clazz == null) {
            clazz = Class.forName(type, true, ObjectFactory.class.getClassLoader());
        }

        return clazz;
    }

    public static Object createExternalObject(String type) {
        Object answer;

        try {
            Class<?> clazz = externalClassForName(type);
            answer = clazz.newInstance();
        } catch (Exception e) {
            throw new GeneratorException(String.format("Cannot instantiate object of type {0}",type), e);
        }

        return answer;
    }

}
