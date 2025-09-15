package test;

import org.junit.jupiter.api.Test;
import posta.Level;
import posta.Posta;
import posta.Postafiok;
import posta.WeekDay;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PostaFiokTest {

    @Test
    public void fogadTest(){
        Postafiok postafiok = new Postafiok();
        Level level = new Level("TestPosta", WeekDay.FRI,1234);
        postafiok.fogad(level);
        ArrayList<Level> testLevel = new ArrayList<Level>();
        testLevel.add(level);
        assertEquals(testLevel,postafiok.getLevelek());
    }
}
