package Lesson7.dz;

import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) {
        try {
//            TesterClass.start(MyClass.class);                            // два варианта запуска: по классу и по его имени
            TesterClass.start("Lesson7.dz.MyClass");
        } catch (IllegalAccessException|InstantiationException|InvocationTargetException|ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
