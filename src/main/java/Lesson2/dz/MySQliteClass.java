package Lesson2.dz;

import java.sql.*;

public class MySQliteClass implements AutoCloseable{
    private Connection connection;
    private Statement stmt;
    private String dbName;
    private String table;

    public MySQliteClass(String dbName, String table) throws SQLException, ClassNotFoundException {
        this.dbName = dbName;
        this.table = table;
        connect();
    }
    public void connect() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection(String.format("jdbc:sqlite:%s",dbName));
        stmt = connection.createStatement();
    }

    public void priceInfo(String s) throws SQLException {
        ResultSet rs = stmt.executeQuery(String.format("SELECT cost FROM %s WHERE title = '%s'",table,s));
        if (rs.next()) System.out.println("Цена "+s+": "+ rs.getInt(1));
        else System.out.println("Товар "+s+" не обнаружен");
    }

    public void changePrice(String[] ss) throws SQLException {
        int price;
        try{
            price = Integer.parseInt(ss[2]);
        } catch (NumberFormatException e) {
            System.out.println("Неверный формат цены!");
            return;
        }
        int ret = stmt.executeUpdate(String.format("UPDATE %s SET cost = %d WHERE title = '%s'",table,price,ss[1]));
        if (ret == 0)System.out.println("Товар "+ss[1]+" не обнаружен");
        else System.out.println("Цена изменена!");
    }

    public void showPriceRange(String[] ss) throws SQLException {
        int lim1;
        int lim2;
        try{
            lim1 = Integer.parseInt(ss[1]);
            lim2 = Integer.parseInt(ss[2]);
        } catch (NumberFormatException e) {
            System.out.println("Неверный формат цен!");
            return;
        }
        ResultSet rs = stmt.executeQuery(String.format("SELECT * FROM %s WHERE cost BETWEEN %d AND %d",table,lim1,lim2));
        if (!rs.next()) {
            System.out.println("Товары не обнаружены");
            return;
        }
        do {
            System.out.println(rs.getInt(2)+" "+rs.getString(3)+" "+rs.getInt(4));
        } while (rs.next());

    }

    public void checker(String s) throws SQLException {
        String[] ss = s.split(" ");
        if (ss[0].equals("/цена") && ss.length == 2 ) priceInfo(ss[1]);
        else if (ss[0].equals("/сменитьцену") && ss.length == 3) changePrice(ss);
        else if (ss[0].equals("/товарыпоцене")&& ss.length == 3) showPriceRange(ss);
        else System.out.println("Неверная команда");
    }

    public Connection getConnection() {
        return connection;
    }

    public Statement getStmt() {
        return stmt;
    }

    @Override
    public void close() throws SQLException {
        connection.close();
    }
}
