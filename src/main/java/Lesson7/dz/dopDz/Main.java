package Lesson7.dz.dopDz;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {
        // создадим финишные причалы, которые принимаею товары
        Harbor finishCloth = new Harbor(Vessels.Cargo,true);
        Harbor finishFood = new Harbor(Vessels.Food,true);
        Harbor finishFuel = new Harbor(Vessels.Tanker,true);
        // создадим  стартовые причалы и поместим их в массив вмесле с финишными
        Harbor[] cargoHarbs = {new Harbor(Vessels.Cargo), finishCloth};
        Harbor[] foodHarbs = {new Harbor(Vessels.Food), finishFood};
        Harbor[] tankerHarbs = {new Harbor(Vessels.Tanker), finishFuel};
        // создадим канал с пропускной способностью 5 корабоей
        Channel channel = new Channel(5);
        System.out.printf("В финишных пристанях товаров: одежды-%d еды-%d топлива-%d\n",
                finishCloth.getGoods(),finishFood.getGoods(),finishFuel.getGoods());
        ExecutorService ex = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 8; i++) {
            ex.execute(new Ship(Vessels.Cargo, channel, cargoHarbs));
            ex.execute(new Ship(Vessels.Food, channel, foodHarbs));
            ex.execute(new Ship(Vessels.Tanker, channel, tankerHarbs));
        }
        ex.shutdown();

        try {
            Harbor.clothFinishLine.await();
            System.out.printf("=====================Лимит MAXCLOTH:%d достигнут!\n",Harbor.MAXCLOTH);
            Harbor.foodFinishLine.await();
            System.out.printf("=====================Лимит MAXFOOD:%d достигнут!\n",Harbor.MAXFOOD);
            Harbor.fuelFinishLine.await();
            System.out.printf("=====================Лимит MAXFUEL:%d достигнут!\n",Harbor.MAXFUEL);
            System.out.println("Все грузы доставлены!");
            //Проверим рельное кол-во товаров в пристанях
            Thread.sleep(1500);
            System.out.printf("Проверочный итог. В финишных пристанях товаров: одежды-%d еды-%d топлива-%d\n",
                    finishCloth.getGoods(), finishFood.getGoods(), finishFuel.getGoods());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
