package test;

import org.junit.jupiter.api.Test;
import posta.Posta;
import posta.Postafiok;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PostaTest {

    @Test
    public void  PostaInitTest(){
        Posta posta = new Posta(new ArrayList<Postafiok>(),"Test", new HashMap<String,Posta>());
        assertEquals(posta.getCim(),"Test");
    }
}
