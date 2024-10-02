package my.j.ex;

import my.j.SourceClass.TestLogging;
import my.j.SourceClass.TestLoggingInterface;
import my.j.changer.Ioc;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;

public class Demo {
    public static void main(String[] args) {
        String className="my.j.SourceClass.TestLogging";
        HashSet<String> hashSetMethodsKey=Ioc.getLogMethodsHash(className);
        TestLoggingInterface test=Ioc.createTestClass(hashSetMethodsKey);
        test.calculation(7);
        test.calculation(7, 8);
    }
}