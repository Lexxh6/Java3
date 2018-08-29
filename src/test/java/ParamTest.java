import Lesson6.dz.MassCheker;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Arrays;
import java.util.Collection;

//задание 2
@RunWith(Parameterized.class)
public class ParamTest {
    private boolean result;
    private int[] m;
    private MassCheker massCheker;

    @Parameterized.Parameters
    public static Collection<Object[]> data(){
        return Arrays.asList(new Object[][]{
                {false, new int[]{1,4,0,1,1}},
                {false, new int[]{4,4,4,4}},
                {false, new int[]{1,1,1}},
                {true, new int[]{4,4,1,4,4}},
                {true, new int[]{1,4,1,4,1}},
                {false, new int[]{1,4,1,7,4,1}}
        });
    }

    public ParamTest(boolean result, int[] m) {
        this.result = result;
        this.m = m;
    }
    @Before
    public void prep(){
        massCheker = new MassCheker();
    }
    @Test
    public void mmassTest(){
        Assert.assertEquals(result,massCheker.masChkAB(m));
    }
}
