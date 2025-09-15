import org.junit.Assert;
import org.junit.Test;

public class MathUtilsTest {
    public static final double EPSILON = 0.1;
    @Test
    public void randomNumINc(){
        Assert.assertEquals(7, MathUtils.Increment(6), EPSILON);
    }
    @Test
    public void intmaxInc(){
        int max = Integer.MAX_VALUE;
        Assert.assertEquals(max, MathUtils.Increment(max), EPSILON);
    }
}
