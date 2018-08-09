package Lesson1.dz.BelomestsevOleg;

import java.util.ArrayList;
import java.util.List;

public class Box<T extends Fruit> {
    private ArrayList<T> storage;
    public Box(){
        storage = new ArrayList<T>();
    }
    public Box(T fruit){
        this();
        storage.add(fruit);
    }

    public void addFruit (T fruit){
        storage.add(fruit);
    }
    public void addFruit (List<T> list){
        storage.addAll(list);
    }
    public float getWegiht(){
        if (storage.isEmpty()) return 0;
        return storage.get(0).weight*storage.size();
    }
    public <E extends Fruit> boolean compare (Box<E> box){
        return  this.getWegiht() == box.getWegiht() ? true : false;
    }
    public void emptyToBox (Box<T> box){
        box.addFruit(storage);
        storage.clear();
    }
}
