package my.j.tester;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import my.j.annotations.After;
import my.j.annotations.Before;
import my.j.annotations.Test;

public class TestStarter {
    public static void Start(String nameClassTest)
            throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<?> test = Class.forName(nameClassTest);
        Method[] methods = test.getDeclaredMethods();
        ArrayList<Method> list_before_methods = new ArrayList<Method>();
        ArrayList<Method> list_test_methods = new ArrayList<Method>();
        ArrayList<Method> list_after_methods = new ArrayList<Method>();

        for (Method m : methods) {
            if (m.isAnnotationPresent(Test.class)) {
                list_test_methods.add(m);
            } else if (m.isAnnotationPresent(Before.class)) {
                list_before_methods.add(m);
            } else if (m.isAnnotationPresent(After.class)) {
                list_after_methods.add(m);
            }
        }
        int num_test = 0;
        int counter = 0;
        for (var test_method : list_test_methods) {
            Constructor<?> constructor = test.getConstructor();
            Object object_for_test = null;
            try {
                object_for_test = constructor.newInstance();
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            }
            num_test++;
            for (Method m : list_before_methods) {
                m.invoke(object_for_test, new Object[] {num_test});
            }

            boolean res = (boolean) test_method.invoke(object_for_test);
            if (res) counter++;
            for (Method m : list_after_methods) {
                m.invoke(object_for_test);
            }
        }
        System.out.println(String.format("Tests completed. Successful tests: %d from %d.", counter, num_test));
    }
}
