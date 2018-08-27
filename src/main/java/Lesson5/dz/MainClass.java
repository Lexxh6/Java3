package Lesson5.dz;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainClass {
    public static final int CARS_COUNT = 4;
    public static CyclicBarrier startBarrier;
    public static CountDownLatch finishLine = new CountDownLatch(CARS_COUNT);
    public static void main(String[] args) {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        startBarrier = new CyclicBarrier(CARS_COUNT, ()-> System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!"));
        Race race = new Race(new Road(60), new Tunnel(CARS_COUNT), new Road(40));
        ExecutorService ex = Executors.newFixedThreadPool(CARS_COUNT);
        for (int i = 0; i < CARS_COUNT; i++) {
            ex.execute(new Car(race, 20 + (int) (Math.random() * 10), startBarrier, finishLine));
        }
        try {
            finishLine.await();
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ex.shutdown();
    }
}