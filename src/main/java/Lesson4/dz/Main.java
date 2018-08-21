package Lesson4.dz;

import java.io.*;

public class Main {
    public static FileWriter out;
    public static MFU mfu;
    public static void main(String[] args) {

        //-------------------------задание 1 ()
        task1();
        //-------------------------задание 2
//        task2();
        //-------------------------задание 3
//        task3();
        //-------------------------доп. задание
//        exrtaTask();
    }
    public static void exrtaTask (){
        generateFile("1.txt","I love Java!", 15);
        MultiReader mr = null;
        try {
            mr = new MultiReader("1.txt",10,4); // задаем максимальное чило потоков и число одновременно запущенных
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        mr.runRead();
    }

    public static void task3 (){
        mfu = new MFU();
        Thread t1 = new Thread(()->mfu.runScan(1,2,3));
        Thread t2 = new Thread(()->mfu.runScan(44,55));
        Thread t3 = new Thread(()->mfu.runPrint(6,7,8));
        Thread t4 = new Thread(()->mfu.runPrint(978,234));
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
    public static void task2 () {
        Object obj = new Object();
        try {
            out = new FileWriter("22.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                while(true) {
                    synchronized (obj) {
                        for (int j = 0; j <10 ; j++) {
                            try {
                                out.write("Поток: "+Thread.currentThread().getName()+" \n");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        System.out.println(Thread.currentThread().getName()+ " закончил!");
                    }
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
    public static void generateFile (String file, String s, int count){
        try(FileWriter fileWriter = new FileWriter(file)){
            for (int i = 0; i < count; i++) {
                fileWriter.write(i+s+i+"\n");
            }
        } catch (IOException e){e.printStackTrace();}
    }
    public static void task1 (){
        ABCprinter mon = new ABCprinter(3,5); // задаем кол-во потоков(букв), и кол-во повторов
        mon.go();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println();
    }

}
