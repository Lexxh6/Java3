import Lesson6.dz.chat.client.Controller;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DopDzTest {
    private Controller contr;
    @Before
    public void prep (){
        contr = new Controller();
    }
    @Test
    public void tectConnection(){

        Assert.assertEquals("OK!",contr.checkConnection("/testConnect"));

    }
}
