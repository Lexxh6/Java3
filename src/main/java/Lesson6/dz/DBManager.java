package Lesson6.dz;

import org.junit.Assert;

import java.sql.*;

public class DBManager {
    private Connection conn;
    private Statement stmn;

    public DBManager() throws ClassNotFoundException,SQLException {
        Class.forName("org.sqlite.JDBC");
        conn = DriverManager.getConnection("jdbc:sqlite:Stud.db");
        stmn = conn.createStatement();
    }
    public void genDB() throws SQLException{
        stmn.execute("CREATE TABLE IF NOT EXISTS students (id INTEGER UNIQUE PRIMARY KEY AUTOINCREMENT NOT NULL," +
                " name TEXT NOT NULL, point INTEGER NOT NULL)");
        stmn.executeUpdate("INSERT INTO students (name, point) VALUES ('Ivanov',10)");
        stmn.executeUpdate("INSERT INTO students (name, point) VALUES ('Petrov',20)");
        stmn.executeUpdate("INSERT INTO students (name, point) VALUES ('Sidorov',5)");
    }
    public String getNameByPoint (int points) throws SQLException{
        ResultSet resultSet = stmn.executeQuery(String.format("SELECT name FROM students WHERE point = %d",points));
        return  resultSet.next()? resultSet.getString(1): "";
    }
    public int getPointsByName (String name) throws SQLException{
        ResultSet resultSet = stmn.executeQuery(String.format("SELECT point FROM students WHERE name = '%s'",name));
        return  resultSet.next()? resultSet.getInt(1): -1;

    }
    public void insertStudent (String name, int points) throws SQLException{
        stmn.execute(String.format("INSERT INTO students (name, point) VALUES ('%s',%d)",name,points));
    }
    public void updatePoints (String name, int points) throws SQLException{
        stmn.execute(String.format("UPDATE students SET point = %d WHERE name = '%s'",points,name));
    }

}
