package de.pp.copyhelper;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Philipp Pfeiffer
 * A Helper class for copying values from a sourceObject to a destinationObject using reflection.
 */
public class CopyHelper {

    /**
     * @param sourceClass The class (Class or Interface) of the sourceClass
     * @param destinationClass The class (Class or Interface) of the destinationClass
     * @param sourceObject
     * @param destinationObject
     * @param <T> Generic Type of the sourceClass
     * @param <U> Generic Type of the destinationClass
     * @throws CopyHelperException
     */
    public static <T, U> void copyData(Class<T> sourceClass, Class<U> destinationClass, T sourceObject, U destinationObject) throws CopyHelperException {

        Class<U> destinationParentClass = null;

        U destinationParentObject = null;

        if (!sourceClass.equals(destinationClass) && sourceClass.isAssignableFrom(destinationClass)) {
            if (destinationClass.isInterface()) {
                destinationParentClass = findRightSuperClass(sourceClass, (Class<U>[]) destinationClass.getInterfaces());
            } else {
                destinationParentClass = findRightSuperClass(sourceClass, (Class<U>) destinationClass.getSuperclass(), true);
            }

            destinationParentObject = destinationParentClass.cast(destinationObject);
        }

        if (destinationParentClass == null) {
            destinationParentClass = destinationClass;
            destinationParentObject = destinationObject;
        }

        Set<Pair<Method, Method>> methodsToInvoke;

        try {
            methodsToInvoke = findMethodsToInvoke(sourceClass, destinationParentClass);
        } catch (IntrospectionException e) {
            throw new CopyHelperException(e);
        }
        for (Pair<Method, Method> m : methodsToInvoke) {
            try {
                Object data = m.getFirst().invoke(sourceObject);
                m.getSecond().invoke(destinationParentObject, data);
            } catch (InvocationTargetException | IllegalAccessException e) {
                throw new CopyHelperException(e);
            }
        }
    }

    /**
     * Method for finding {@link Method}s (getter, setter) for a given source- and destination class
     *
     * @param sourceClass
     * @param destinationClass
     * @param <T>
     * @param <U>
     * @return Set of Method{@link Pair}s
     * @throws IntrospectionException
     */
    public static <T, U> Set<Pair<Method, Method>> findMethodsToInvoke(Class<T> sourceClass, Class<U> destinationClass) throws IntrospectionException {

        List<PropertyDescriptor> propertyDescriptorsSource = findPropertyDescriptorsByClass(sourceClass);

        Map<String, PropertyDescriptor> propertyDescriptorMap = convertPropertyDescriptorsToMap(findPropertyDescriptorsByClass(destinationClass));

        Set<Pair<Method, Method>> methodsToInvoke = new HashSet<Pair<Method, Method>>();

        for (PropertyDescriptor propertyDescriptorSource : propertyDescriptorsSource) {
            Method getterMethodSource = propertyDescriptorSource.getReadMethod();
            if (getterMethodSource != null) {
                if (propertyDescriptorMap.containsKey(propertyDescriptorSource.getName())) {
                    PropertyDescriptor propertyDescriptorDestination = propertyDescriptorMap.get(propertyDescriptorSource.getName());
                    Method setterMethodDestination = propertyDescriptorDestination.getWriteMethod();
                    if (setterMethodDestination != null) {
                        if (setterMethodDestination.getAnnotation(NoCopy.class) == null && getterMethodSource.getAnnotation(NoCopy.class) == null) {
                            //First method is Getter-Method from sourceClass, second is Setter-Method from destinationClass
                            Pair<Method, Method> methodPair = new Pair<Method, Method>(getterMethodSource, setterMethodDestination);
                            methodsToInvoke.add(methodPair);
                        }
                    }
                }
            }

        }

        return methodsToInvoke;
    }

    /**
     * Method for finding {@link PropertyDescriptor}s for a given class
     * @param clazz
     * @param <T>
     * @return List of {@link PropertyDescriptor}
     * @throws IntrospectionException
     */
    public static <T> List<PropertyDescriptor> findPropertyDescriptorsByClass(Class<T> clazz) throws IntrospectionException {
        return Arrays.asList(Introspector.getBeanInfo(clazz).getPropertyDescriptors());
    }

    /**
     * @param propertyDescriptors
     * @return Map of Strings and {@link PropertyDescriptor}s
     */
    public static Map<String, PropertyDescriptor> convertPropertyDescriptorsToMap(List<PropertyDescriptor> propertyDescriptors) {
        return propertyDescriptors.stream().collect(Collectors.toMap(p -> p.getName(), p -> p));
    }

    /**
     * Method for finding the Superclass of a class recursively.
     * @param destinationClass
     * @param actualClass
     * @param <T>
     * @param <U>
     * @return {@link Class}
     */
    public static <T, U> Class<U> findRightSuperClass(Class<T> destinationClass, Class<U> actualClass, boolean recursive) {
        Class<U> clazz = null;
        if (destinationClass.equals(actualClass)) {
            clazz = actualClass;
        } else {
            if (recursive) {
                clazz = findRightSuperClass(destinationClass, (Class<U>) actualClass.getSuperclass(), true);
            }
        }
        return clazz;
    }

    /**
     * Method for finding all inherited Interfaces of a Interface
     * @param destinationClass
     * @param interfaces
     * @param <T>
     * @param <U>
     * @return {@link Class} of the Interface
     */
    public static <T, U> Class<U> findRightSuperClass(Class<T> destinationClass, Class<U>[] interfaces) {
        Class<U> foundClass = null;
        for (Class<U> clazz : interfaces) {
            foundClass = findRightSuperClass(destinationClass, clazz, false);
            if (foundClass != null) {
                break;
            }
        }
        return foundClass;
    }


}
