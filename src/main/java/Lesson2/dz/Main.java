package Lesson2.dz;

import java.sql.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        try (MySQliteClass mySQL = new MySQliteClass("Lesson2_DZ.db","tab")){

            mySQL.getStmt().execute("CREATE TABLE IF NOT EXISTS tab (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    " prodid INTEGER UNIQUE NOT NULL, title TEXT, cost INTEGER)");
            mySQL.getStmt().executeUpdate("DELETE FROM tab");
            mySQL.getConnection().setAutoCommit(false);
            for (int i = 1; i <= 10000; i++) {
                mySQL.getStmt().executeUpdate(String.format("INSERT INTO tab (prodid, title, cost) VALUES (%d, 'товар%d', %d)",i,i,i*10));
            }
            mySQL.getConnection().setAutoCommit(true);
            Scanner sc = new Scanner(System.in);
            String command = "";
            System.out.println("Введите команду или /выход для завершения");
            while (true) {
                command = sc.nextLine().trim();
                if (command.equals("/выход")) break;
                mySQL.checker(command);
            }
        } catch ( ClassNotFoundException|SQLException e) {
            e.printStackTrace();
        }
    }
}
// ДОП задание: SELECT name,max(date) FROM date GROUP BY name;