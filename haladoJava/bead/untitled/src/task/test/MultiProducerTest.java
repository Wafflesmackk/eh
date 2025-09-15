package task.test;

import org.junit.jupiter.api.Test;
import task.compulsory.MultiProducer.MultiProducerFactory;

import java.io.ObjectStreamException;
import java.lang.reflect.Executable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.IntSupplier;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class MultiProducerTest {

    public static int[] value123A = {0};
    public static int[] value123B = {0};

    public static String[] txtA  = {""};
    public static String[] txtB  = {""};


    public static IntSupplier amountLambda123A = () -> ++value123A[0];
    public static IntSupplier amountLambda123B = () -> ++value123B[0];

    public static Supplier<String> contentLambdaA = () -> {
        txtA[0] = txtA[0].concat("a");
        return txtA[0];
    };
    public static Supplier<String> contentLambdaB  = () -> {
        txtB[0] = txtB[0].concat("a");
        return txtB[0];
    };

    public String getContentA(){
        return txtA[0];
    }

    public String getContentB(){
        return txtB[0];
    }

    @Test
    public void test(){
        BiFunction<IntSupplier,Supplier<String>,String> multiFactA = MultiProducerFactory.provider().get();
        BiFunction<IntSupplier,Supplier<String>,String> multiFactB = MultiProducerFactory.provider().get();

        ArrayList<String> multiFactAOut = new ArrayList<>();
        ArrayList<String> multiFactOutB = new ArrayList<>();

        ArrayList<String> expectedValues = new ArrayList<String>(Arrays.asList(new String[] {"a", "aa", "aaa", "aaaa", "aaaaa", "aaaaaa", "aaaaaaa"}));

        assertAll( () -> {
            for (int i = 0; i < 7; i++) {
                multiFactA.apply(amountLambda123A, contentLambdaA);
                multiFactAOut.add(getContentA());


                assertNotEquals(multiFactAOut,multiFactOutB);
                assertNotEquals(getContentA(),getContentB());


                multiFactB.apply(amountLambda123B, contentLambdaB);
                multiFactOutB.add(getContentB());


                assertEquals(multiFactAOut,multiFactOutB);
                assertEquals(getContentA(),getContentB());

            }
        },

                ()-> assertEquals(multiFactAOut,multiFactOutB),
                ()-> assertEquals(multiFactAOut,expectedValues),
                () -> assertEquals(multiFactOutB, expectedValues)
        );


    }


}
