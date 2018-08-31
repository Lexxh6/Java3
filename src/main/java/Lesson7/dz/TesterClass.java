package Lesson7.dz;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TesterClass {
    public static Class c;

    public static void start(String classPath) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException {
        TesterClass.start(Class.forName(classPath));
    }
    public static void start(Class<?> c) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        Object obj = c.newInstance();
        TesterClass.c = c;
        List<Method> listBefore = getAnnoMeths(c, BeforeSuite.class);
        isIlligal(listBefore);

        List<Method> listTest = getAnnoMeths(c, Test.class);
        sortByPriority(listTest,Test.class);

        List<Method> listAfter = getAnnoMeths(c ,AfterSuite.class);
        isIlligal(listAfter);

        List<Method> myList = Stream.of(listBefore, listTest, listAfter).flatMap(Collection::stream).collect(Collectors.toList());         // Java 8 style
        for (Method method:myList) method.invoke(obj);

//        listBefore.addAll(listTest);
//        listBefore.addAll(listAfter);
//        for (Method method:listBefore) method.invoke(obj);

    }
    private static void sortByPriority(List<Method> list, Class<? extends Test> c){
        Collections.sort(list, Comparator.comparingInt(o -> (o.getDeclaredAnnotation(c).priority())));
    }

    private static List<Method> getAnnoMeths(Class<?> c, Class<? extends Annotation> a){
        Method[] methods = c.getDeclaredMethods();
        ArrayList<Method> list = new ArrayList<>();
        for (Method m:methods) {
            if (m.getAnnotation(a)!=null){
                list.add(m);
            }
        }
        return list;
    }
    private static void isIlligal(List list){
        if (list.size()>1) throw new IllegalArgumentException("More then one Annotation Exeption");
    }

}
