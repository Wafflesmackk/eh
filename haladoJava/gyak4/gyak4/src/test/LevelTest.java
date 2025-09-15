package test;

import org.junit.jupiter.api.Test;
import posta.Level;
import posta.Posta;
import posta.WeekDay;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LevelTest {



    @Test
    public void utazasNapTest(){
        Level level  = new Level("TestPosta", WeekDay.FRI,1234);
        Posta posta = new Posta("InterPosta");
        level.utazas(posta);
        assertEquals(level.getFeladasiNap(),WeekDay.SAT);
    }
    @Test
    public void utazasPostaTest(){
        Level level  = new Level("TestPosta", WeekDay.FRI,1234);
        Posta posta = new Posta("InterPosta");
        level.utazas(posta);
        ArrayList<String> postaTrial = new ArrayList<String>();
        postaTrial.add(posta.getCim());
        assertEquals(level.getAllomasok(),postaTrial);
    }
}
