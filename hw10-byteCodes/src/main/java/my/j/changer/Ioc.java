package my.j.changer;

import my.j.SourceClass.TestLogging;
import my.j.SourceClass.TestLoggingInterface;
import my.j.annotation.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashSet;

public class Ioc {
    private static final Logger logger = LoggerFactory.getLogger(Ioc.class);
    private Ioc() {}
    public static String getKey_from_Method(Method method)
    {
        String key = method.getName() + Arrays.toString(method.getParameterTypes());
        return key;
    }
    public static HashSet<String> getLogMethodsHash(String className) {

        Class<?> test = null;
        try {
            test = Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        Method[] methods;
        methods = test.getDeclaredMethods();
        HashSet<String> hashSetLogsMethods = new HashSet<>();
        for (Method m : methods) {
            if (m.isAnnotationPresent(Log.class)) {
                String key=getKey_from_Method(m);
                hashSetLogsMethods.add(key);
            }
        }
        return  hashSetLogsMethods;

    }

    public static TestLoggingInterface createTestClass(HashSet<String> hashSetMethods) {
        InvocationHandler handler = new DemoInvocationHandler(new TestLogging(), hashSetMethods);
        return (TestLoggingInterface)
                Proxy.newProxyInstance(Ioc.class.getClassLoader(), new Class<?>[] {TestLoggingInterface.class}, handler);
    }

    static class DemoInvocationHandler implements InvocationHandler {
        private final TestLoggingInterface myClass;
        private HashSet<String> hashSetMethods=null;
        DemoInvocationHandler(TestLoggingInterface myClass, HashSet<String> hashSetMethods) {

            this.myClass = myClass;
            this.hashSetMethods=hashSetMethods;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

            if (!hashSetMethods.isEmpty()) {
                String key= Ioc.getKey_from_Method(method);

                if(hashSetMethods.contains(key)){
                    String params=Arrays.toString(args).
                            replace("[","").
                            replace("]", "");
                    logger.info("executed method: {}, param {}", method.getName(), params);
                }

            }

            return method.invoke(myClass, args);
        }

        @Override
        public String toString() {
            return "DemoInvocationHandler{" + "myClass=" + myClass + '}';
        }
    }

}
