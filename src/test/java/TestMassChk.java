import Lesson6.dz.MassCheker;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

//задание 1
public class TestMassChk {
    MassCheker massCheker;
    @Before
    public void prep(){
        massCheker = new MassCheker();
    }
    @Test(expected = RuntimeException.class)
    public void test1() {
        int[] m = {1,5,6,7,9,1};
        massCheker.masChk(m);
    }
    @Test
    public void test2() {
        int[] m = {1,4,6,7,9,1};
        int[] exp = {6,7,9,1};
        Assert.assertArrayEquals(exp,massCheker.masChk(m));
    }
    @Test
    public void test3() {
        int[] m = {4,5,6,4,9,3};
        int[] exp = {9,3};
        Assert.assertArrayEquals(exp,massCheker.masChk(m));
    }

}
