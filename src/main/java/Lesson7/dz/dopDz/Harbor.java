package Lesson7.dz.dopDz;

import java.util.concurrent.CountDownLatch;

public class Harbor {
    public final static int  MAXCLOTH = 2700;
    public final static int  MAXFOOD = 5900;
    public final static int  MAXFUEL = 8500;
    public final static int  LOADER_RATIO = 100;     // сколько грузим за раз
    public final static CountDownLatch clothFinishLine = new CountDownLatch(MAXCLOTH/Vessels.Cargo.getCapacity()+1);
    public final static CountDownLatch foodFinishLine = new CountDownLatch(MAXFOOD/Vessels.Food.getCapacity()+1);
    public final static CountDownLatch fuelFinishLine = new CountDownLatch(MAXFUEL/Vessels.Tanker.getCapacity()+1);
    private Vessels type;
    private volatile int goodsCounter;
    private CountDownLatch finishLine;
    private Boolean emptyDocks;
    public Harbor(Vessels type){
        this(type, false);
    }
    public Harbor(Vessels type,Boolean emptyDocks) {        //emptyDocks=true, если пустой(финишный) причал
        this.type = type;
        this.emptyDocks = emptyDocks;
        switch (type){
            case Cargo:
                this.finishLine = clothFinishLine;
                if(!emptyDocks) goodsCounter = MAXCLOTH;
                break;
            case Food:
                this.finishLine = foodFinishLine;
                if(!emptyDocks) goodsCounter = MAXFOOD;
                break;
            case Tanker:
                this.finishLine = fuelFinishLine;
                if(!emptyDocks) goodsCounter = MAXFUEL;
                break;
        }
    }
    public synchronized void setAnchor(Ship ship){
        if (ship.getType() != type) throw new IllegalArgumentException(" Wrong Harbor type ;)");
        System.out.println(ship+" зашел в порт...");
        if(ship.isloaded()) {
            unload(ship);
        }else {
            if(!emptyDocks)load(ship);
        }
    }
    private void unload(Ship ship){
        goodsCounter += ship.unload();
        System.out.println(ship+" разгрузился! В порту: "+goodsCounter);
        finishLine.countDown();
    }
    private void load(Ship ship){
        System.out.println(ship+" погрузка начата...");
        while (goodsCounter>0 && ship.loadGoods(LOADER_RATIO)) {
            try {
                Thread.sleep(1000);             // скорость загрузки корабля, для более быстрого результата лучше поставить 100
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            goodsCounter -= LOADER_RATIO;
        }
        System.out.println(ship+" погрузка закончена!, осталось товара " + goodsCounter);

    }

    public int getGoods() {
        return goodsCounter;
    }
}
