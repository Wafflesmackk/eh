package task.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import task.extension.MultiProducer2.cachedMultiProducer;
import task.extension.MultiProducer2State;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class multiProducerTest2 {

    Supplier<MultiProducer2State> bunchDecisionLambda;
    Supplier<MultiProducer2State> flipDecisionLambda;

    private final boolean[] flip = {true};
    private final int[] count = {0,0};

    private void resetFlip(){
        flip[0] = true;
    }

    private void resetCount(){
        count[0] = 0 ;
        count[1] = 0;
    }


    @BeforeEach
    public void setup(){
        resetCount();
        resetFlip();

         flipDecisionLambda = () -> {
            if (flip[0]){
                flip[0] = false;
                return MultiProducer2State.EXTEND;
            }
            else{
                flip[0] = true;
                return  MultiProducer2State.KEEP;
            }
        };

        bunchDecisionLambda = () ->{
            if(count[1] == 0){
                count[0]++;
                count[1] = count[0];
                return MultiProducer2State.EXTEND;
            }
            else{
                count[1]--;
                return MultiProducer2State.KEEP;
            }
        };
    }

    @ParameterizedTest
    @CsvSource(textBlock = """
            "a", 5, 5
            "", 9, 11
            "aaa", 2, 22
    """)
    public void testConstant(String appendTxt, int decisionCount, int checkNum){
            Supplier<MultiProducer2State> constantDecisionLambda = () -> {
                return MultiProducer2State.KEEP;
            };


            String  result = "";

        for (int i = 0; i < checkNum; i++) {
            result = cachedMultiProducer.provider(decisionCount,constantDecisionLambda).get().apply(appendTxt).get();
        }

       assertEquals("",result);
    }

    @Test
    public void flip20(){
        StringBuilder expResult = new StringBuilder();
        String appendTxt = "a";

        Supplier<String> innerLambda = cachedMultiProducer.provider(10,flipDecisionLambda).get().apply(appendTxt);

        ArrayList<String> results = new ArrayList<>();


        for (int i = 0; i < 20; i++) {
            results.add(innerLambda.get());
        }

        ArrayList<String> expResults = new ArrayList<>(Arrays.asList(new String[] {
                "",
                "a",
                "a",
                "aa",
                "aa",
                "aaa",
                "aaa",
                "aaaa",
                "aaaa",
                "aaaaa",
                "aaaaa",
                "aaaaaa",
                "aaaaaa",
                "aaaaaaa",
                "aaaaaaa",
                "aaaaaaaa",
                "aaaaaaaa",
                "aaaaaaaaa",
                "aaaaaaaaa",
                "aaaaaaaaaa"}));

        assertEquals(expResults,results);
    }

    @Test
    public void bunch20(){
        String appendTxt = "a";
        Supplier<String> innerLambda = cachedMultiProducer.provider(10,bunchDecisionLambda).get().apply(appendTxt);
        ArrayList<String> results = new ArrayList<>();


        for (int i = 0; i < 20; i++) {
            results.add(innerLambda.get());
        }

        ArrayList<String> expResults = new ArrayList<>(Arrays.asList(new String[] {
                "",
                "a",
                "a",
                "aa",
                "aa",
                "aa",
                "aaa",
                "aaa",
                "aaa",
                "aaa",
                "aaaa",
                "aaaa",
                "aaaa",
                "aaaa",
                "aaaa",
                "aaaaa",
                "aaaaa",
                "aaaaa",
                "aaaaa",
                "aaaaa"}));
        assertEquals(expResults,results);
    }

    @Test
    public void oneOfAB(){
        Supplier<String> onlyA = () -> "a";
        Supplier<String> onlyB = () -> "b";

        BiFunction<Supplier<String>,Supplier<String>,Supplier<String>> oneOfABLambda = cachedMultiProducer.oneForEach();
        Stream<String> resultStream = Stream.generate(oneOfABLambda.apply(onlyA,onlyB)).limit(6);
        List<String> resultList = resultStream.toList();

        assertAll(
                ()-> assertEquals("a", resultList.get(0)),
                ()->assertEquals("b", resultList.get(1)),
                ()->assertEquals("a", resultList.get(2)),
                ()->assertEquals("b", resultList.get(3)),
                ()->assertEquals("a", resultList.get(4)),
                ()->assertEquals("b", resultList.get(5))
        );
    }

    @ParameterizedTest
    @CsvSource(textBlock = """
            1
            3
            10
    """)
    public void cachedMultiProducerTest(int decisionCount){
       Supplier<String> flipLambda = cachedMultiProducer.provider(decisionCount,flipDecisionLambda).get().apply("a");
       Supplier<String> bunchLambda = cachedMultiProducer.provider(decisionCount,bunchDecisionLambda).get().apply("b");

        BiFunction<Supplier<String>,Supplier<String>,Supplier<String>> oneOfABLambda = cachedMultiProducer.oneForEach();

       List<String> result  = Stream.generate(oneOfABLambda.apply(flipLambda,bunchLambda)).limit(40).toList();

        List<String> expectedOutput = List.of("", "",
                "a", "b",
                "a", "b",
                "aa", "bb",
                "aa", "bb",
                "aaa", "bb",
                "aaa", "bbb",
                "aaaa", "bbb",
                "aaaa", "bbb",
                "aaaaa", "bbb",
                "aaaaa", "bbbb",
                "aaaaaa", "bbbb",
                "aaaaaa", "bbbb",
                "aaaaaaa", "bbbb",
                "aaaaaaa", "bbbb",
                "aaaaaaaa", "bbbbb",
                "aaaaaaaa", "bbbbb",
                "aaaaaaaaa", "bbbbb",
                "aaaaaaaaa", "bbbbb",
                "aaaaaaaaaa", "bbbbb");

        assertEquals(result,expectedOutput);

    }

}