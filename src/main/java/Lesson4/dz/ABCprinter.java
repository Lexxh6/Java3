package Lesson4.dz;

/**
 * Класс создает заданное (maxThreads) кол-во потоков, каждый поток выводит букву
 * начиная с 'A', буквы выводятся в консоль в порядке возрастания. Вывод повторятся (repeats) раз.
 */
public class ABCprinter {
    private int maxThreads;
    private int repeats;
    private volatile int threadToGo;
    public ABCprinter(int maxThreads, int repeats){
        this.maxThreads = maxThreads;
        this.repeats = repeats;
    }
    public void go () {
        for(int i =0; i< maxThreads ; i++) {
            Thread thread =new Thread(()->{
                int number = Integer.parseInt(Thread.currentThread().getName());
                char c = (char)('A'+number);
                for (int j = 0; j < repeats ; j++) {
                    synchronized (ABCprinter.this) {
                        while (true) {
                            if (number == threadToGo) {
                                if (number == maxThreads - 1) threadToGo = 0;
                                else threadToGo++;
                                System.out.print(c);
                                ABCprinter.this.notifyAll();
                                break;
                            } else {
                                try {
                                    wait();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                }
            });
            thread.setName(Integer.toString(i));
            thread.start();
        }
    }
}