import Lesson6.dz.DBManager;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import java.sql.*;

//задание 3
public class DBTestClass {
    static DBManager dbMngr;

    @BeforeClass                        // запускается один раз
    public static void makeBase() throws SQLException, ClassNotFoundException {
        dbMngr = new DBManager();
        dbMngr.genDB();                 // заполняем БД начальными данными
    }
    @Test
    public void readTest() {
        try {
            Assert.assertEquals("Petrov", dbMngr.getNameByPoint(20));
        } catch (SQLException e) {
            e.printStackTrace();
            Assert.fail();
        }
    }
    @Test
    public void insertTest() {
        try {
            dbMngr.insertStudent("Bond",77);
            Assert.assertEquals(77,dbMngr.getPointsByName("Bond"));
        } catch (SQLException e) {
            e.printStackTrace();
            Assert.fail();
        }
    }
    @Test
    public void updateTest(){
        try {
            dbMngr.insertStudent("Rock",33);
            dbMngr.updatePoints("Rock",999);
            Assert.assertEquals(999,dbMngr.getPointsByName("Rock"));
        } catch (SQLException e) {
            e.printStackTrace();
            Assert.fail();
        }
    }
}
