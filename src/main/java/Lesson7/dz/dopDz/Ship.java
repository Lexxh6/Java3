package Lesson7.dz.dopDz;

public class Ship implements Runnable{
    private static int counter;
    private Vessels type;
    private int cargoCounter;
    private Channel channel;
    private Harbor[] harbors;
    private int number;

    public Ship(Vessels type, Channel channel, Harbor[] harbors){
        this.type = type;
        this.channel = channel;
        this.harbors = harbors;
        number = ++counter;
    }
    public Vessels getType(){
        return type;
    }
    public boolean loadGoods(int i) {
        if (cargoCounter < type.getCapacity()) {
            cargoCounter += i;
            return true;
        }
        return false;
    }
    public int unload(){
        int tempCargoCounter = cargoCounter;
        cargoCounter = 0;
        return tempCargoCounter;
    }
    public boolean isloaded(){
        return cargoCounter>0 ? true : false;
    }
    @Override
    public void run() {
        try {
            for (int i = 0; i < harbors.length ; i++) {
                channel.goInside(this);
                harbors[i].setAnchor(this);
                Thread.sleep((int)(Math.random()*2000) + 500);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Override
    public String toString(){
        return "Ship №" + number + " " + type.name() +" ("+cargoCounter+")";
    }
}
