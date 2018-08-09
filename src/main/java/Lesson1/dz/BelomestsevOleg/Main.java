package Lesson1.dz.BelomestsevOleg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        Integer[] m = {1,2,3,4,5,6};
        moveArrayElements(m,5,1);                               // задание 1
        for (int i: m) System.out.print(i + " ");

        ArrayList<Integer> arrayList = arrayToArrayList(m);             // задание 2
        System.out.println("\n"+"ArrayList: " + arrayList);

        System.out.println("\n\t ЗАДАНИЕ С ФРУКТАМИ");
        Box<Apple> box = new Box<Apple>();                              // задание 3
        box.addFruit(new Apple());
        box.addFruit(new Apple());
        box.addFruit(new Apple());

        Box<Orange> box2 = new Box<Orange>();
        box2.addFruit(new Orange());
        System.out.println("Вес коробок: \t box:" + box.getWegiht() + "\t box3: " + box2.getWegiht());
        System.out.println("Веса равны: " + box.compare(box2));
        box2.addFruit(new Orange());
        System.out.println("Вес коробок: \t box:" + box.getWegiht() + "\t box3: " + box2.getWegiht());
        System.out.println("Веса равны: " + box.compare(box2));

        Box<Apple> box3 = new Box<Apple>();
        box3.addFruit(new Apple());
        System.out.println("Вес до пересыпания: \t box:" + box.getWegiht() + "\t box3: " + box3.getWegiht());
        box.emptyToBox(box3);
//        box.emptyToBox(box2);  // не скомпилируется!!! (разные фрукты)
        System.out.println("Вес после пересыпания:\t box:" + box.getWegiht() + "\t box3: " + box3.getWegiht());

    }
    public  static <T> void moveArrayElements (T[] m, int n1, int n2){ // Поменять местами n1 и n2
        if (n1 >= m.length || n2 >= m.length) throw new IllegalArgumentException("moveArrayElements:Номер элемента больше длины массива!");
        T temp = m[n1];
        m[n1] = m[n2];
        m[n2] = temp;
    }
    public static <T> ArrayList<T> arrayToArrayList (T[] m){
        ArrayList<T> arrayList = new ArrayList<T>(Arrays.asList(m));
//        ArrayList<T> arrayList = new ArrayList<T>();  //еще один вариант массив в Array List
//        Collections.addAll(arrayList, m);
        return arrayList;
    }
}
