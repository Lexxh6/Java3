package Lesson3.dz;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //доп задание
        try(Server server = new Server(7777);
            Client client = new Client(7777)){
            Student std = new Student();
            std.setS("Студент1");
            client.sendObj(std);
            Student std2 = (Student) server.reciveObj();
            System.out.println("Объект десериализован: "+std2.getS());
            firstTask();    //1 задание
            secondTask();   //2 задание
            thirdTask();    //3 задание
        } catch (IOException|ClassNotFoundException e){e.getStackTrace();}
    }
    public static  void firstTask () throws IOException {
        File file = new File("1.txt");
        try (FileOutputStream out = new FileOutputStream(file);
             FileInputStream in = new FileInputStream(file);) {
            for (int i = 0; i < 50; i++) out.write('0' + i); // запишем в файл 50 байт
            out.close();
            byte[] bytes = new byte[50];
            in.read(bytes);
            System.out.println("Содержимое файла " + file.toString() + ":\n" + new String(bytes));
        }
    }
    public static  void secondTask ()throws IOException{
        File file;
        ArrayList<InputStream> flist = new ArrayList<>();
        FileOutputStream fout;
        for (int i = 1; i <= 5 ; i++) {             // создаем 5 файлов по 80 байт
            file = new File(i+"ar.txt");
            fout = new FileOutputStream(file);
            for (int j=0; j<80 ;j++) fout.write('!'+j);
            fout.write('\n');
            fout.close();
            flist.add(new FileInputStream(file)); // добавляем файл в список
        }
        SequenceInputStream sqin = new SequenceInputStream(Collections.enumeration(flist));
        BufferedReader br = new BufferedReader(new InputStreamReader(sqin));
        String s;
        System.out.println("Содержимое 5 файлов:");
        while ((s=br.readLine())!= null) System.out.println(s);
        br.close();
    }
    public static  void thirdTask ()throws IOException{
        long l = System.currentTimeMillis();
        File file = new File("big.txt");
        try (BufferedWriter bwr = new BufferedWriter(new FileWriter(file));){    // Запишем файл > 20 МБ
            for (int i = 0; i <2000000 ; i++) bwr.write("I love Java!");
        }
        BufferedReader br = new BufferedReader(new FileReader(file));
        char[] chars = new char[1800];

        System.out.println("Загрузка заняла(мс):"+ (System.currentTimeMillis()-l));
        Scanner sc = new Scanner(System.in);
        System.out.println("Type /exit to exit or press Enter to read 1800 chars...");
        String s;
        while((s = sc.nextLine()).equals("") && !s.equals("/exit")){
            l = System.currentTimeMillis();
            br.read(chars);
            System.out.println(new String(chars));
            System.out.println("Выполнение(мс):"+ (System.currentTimeMillis()-l));
        }
        br.close();
    }
}
