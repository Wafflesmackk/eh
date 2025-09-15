import org.junit.Assert;
import org.junit.Test;

public class MathUtilsTest {
    public static final double EPSILON = 0.1;

    
    @Test
    public void raisePosToPos(){
        Assert.assertEquals(8.0, MathUtils.power(2, 3), EPSILON);
    }
    @Test
    public void raiseOneToPos(){
        Assert.assertEquals(1.0, MathUtils.power(1, 3), EPSILON);
    }
    @Test
    public void raiseZeroToPos(){
        Assert.assertEquals(0.0, MathUtils.power(0, 3), EPSILON);
    }
    @Test
    public void raiseZeroToNeg(){
        Assert.assertEquals(0.25, MathUtils.power(2, -2), EPSILON);
    }
    @Test
    public void raisePosToZero(){
        Assert.assertEquals(1, MathUtils.power(3, 0), EPSILON);
    }
}
