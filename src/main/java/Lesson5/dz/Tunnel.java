package Lesson5.dz;

import java.util.concurrent.Semaphore;

public class Tunnel extends Stage {
    private Semaphore way;

    public Tunnel(int cars_count) {
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
        way = new Semaphore(cars_count/2);               // Пропускная способность тоннеля
    }
    @Override
    public void go(Car c) {
        try {
            try {
                System.out.println(c.getName() + " %%готовится к этапу(ждет): " + description);
                way.acquire();
                System.out.println(c.getName() + " ++начал этап: " + description);
                Thread.sleep(length / c.getSpeed() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(c.getName() + " --закончил этап: " + description);
                way.release();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}