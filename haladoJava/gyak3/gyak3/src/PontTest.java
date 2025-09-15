

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;

import static org.junit.jupiter.api.Assertions.*;

public class PontTest {

    Pont pont;

    @BeforeEach
    public void beforeEach(){
        pont = new Pont(1,2);
    }

    @Test
    public void moveXTest(){
        pont.moveX(2);
        assertEquals(3, pont.getX());
    }

    @Test
    public void moveYTest(){
        pont.moveY(2);
        assertEquals(4,pont.getY());
    }

    @Test
    public void distanceTest(){
        Pont other = new Pont(1,3);
        assertEquals(1, pont.distanceFrom(other));
    }


}
