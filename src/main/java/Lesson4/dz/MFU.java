package Lesson4.dz;

public class MFU {
    private Object mon1;
    private Object mon2;

    public MFU() {
        mon1 = new Object();
        mon2 = new Object();
    }
    public  void runScan(int... pages){
        synchronized (mon1){
            StringBuilder sb = new StringBuilder("отсканировано ");
            System.out.println("сканирование начато...");
            for (int i = 0; i < pages.length ; i++) {
                try {
                    Thread.sleep(50);
                    System.out.println("--сканирую страницу:"+pages[i]);
                    sb.append(pages[i]+", ");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            sb.append("... страницы");
            System.out.println(sb.toString());
        }
    }
    public synchronized void runPrint(int... pages){
        synchronized (mon2){
            StringBuilder sb = new StringBuilder("отпечатано ");
            System.out.println("печать начата...");
            for (int i = 0; i < pages.length ; i++) {
                try {
                    Thread.sleep(50);
                    System.out.println("--печатаю страницу:"+pages[i]);
                    sb.append(pages[i]+", ");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            sb.append("... страницы");
            System.out.println(sb.toString());
        }
    }
}
