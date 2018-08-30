package Lesson7.dz.dopDz;

import java.util.concurrent.Semaphore;

public class Channel {
    private int capacity;
    private Semaphore pass;
    public Channel(int capacity) {
        this.capacity= capacity;
        pass = new Semaphore(capacity);
    }
    public void goInside(Ship ship){
        try {
            System.out.println(ship+" ожидает входа в канал...");
            pass.acquire();
            System.out.println(ship+" начал движение по каналу");
            Thread.sleep(500);
            pass.release();
            System.out.println(ship+" покинул канал!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
