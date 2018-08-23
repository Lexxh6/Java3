package Lesson5.dz;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

public class Car implements Runnable {
    private static AtomicInteger CARS_COUNT;       // Сменим тип переменной, чтобы обеспечить корректный CARS_COUNT++ для разных потоков
    static {
        CARS_COUNT= new AtomicInteger(0);
    }
    private Race race;
    private int speed;
    private String name;
    private CyclicBarrier startBarier;
    private CountDownLatch finishLine;
    public String getName() {
        return name;
    }
    public int getSpeed() {
        return speed;
    }
    public Car(Race race, int speed, CyclicBarrier startBarier, CountDownLatch finishLine) {
        this.startBarier = startBarier;
        this.finishLine = finishLine;
        this.race = race;
        this.speed = speed;
        this.name = "Участник #" + CARS_COUNT.incrementAndGet();
    }
    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int)(Math.random() * 800));
            System.out.println(this.name + " готов");
            startBarier.await();

        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }
        if (race.isWinner()) System.out.println(this.name + " - WIN");
        finishLine.countDown();
    }
}
