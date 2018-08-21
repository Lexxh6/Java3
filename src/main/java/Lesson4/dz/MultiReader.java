package Lesson4.dz;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class MultiReader {
    private int totalThreads;
    private int threadsRuning;
    private int maxRunning;
    private Map<Thread,Integer> map;
    private FileInputStream fileReader;
    private Object lock;

    public MultiReader (String file,int totatlThreads, int maxThreadRunning) throws FileNotFoundException {
        this.totalThreads = totatlThreads;
        this.maxRunning = maxThreadRunning;
        map = new HashMap<>();
        fileReader = new FileInputStream(file);
        lock = new Object();
    }
    public void runRead (){
        for (int i = 0; i < totalThreads ; i++) {
            Thread thread = new Thread(() -> {
                while (true) {
                    synchronized (MultiReader.this) {
                        while (stopThred(Thread.currentThread())) {
                            try {
                                if (fileReader == null) return;
                                wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    int counter = (int) (Math.random()*5)+1;;            // Сколкьо байт читать за раз
                    int c;
                    synchronized (lock) {       // можно убрать синхронизацию, но сломается порядок
                        try {
                            for (int j = 0; j < counter; j++) {
                                if (fileReader == null) return;
                                c = fileReader.read();
                                if (c == -1){
                                    setTerminateState();
                                    break;
                                }
                                System.out.print((char) c);
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    synchronized (MultiReader.this) {
                        --threadsRuning;
                        notifyAll();
                        if (fileReader == null) return;
                    }
                }
            });
            map.put(thread,0);
            thread.start();
        }
    }
    private synchronized boolean stopThred (Thread t){
        if (threadsRuning<maxRunning){
            if(map.values().contains(0)) {
                if (map.get(t)>0) return true;
            }
            map.replace(t,map.get(t)+1);
            threadsRuning++;
            return false;
        }
        return true;
    }
    private void setTerminateState(){
        fileReader = null;
        for (Thread t: map.keySet()) System.out.println("Поток "+t.getName()+" прочитал файл "+ map.get(t) + " раз(а)"); // проверяем что все потоки чатали файл
    }
}

