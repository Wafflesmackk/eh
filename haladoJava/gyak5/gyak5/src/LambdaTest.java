import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class LambdaTest {
    @Test
    public void testPosNumLambdaFirst(){
        Supplier<Integer> f = Lambdas.posNumLambda();
        assertEquals(f.get(),1);
    }
    @Test
    public void testPosNumLambdaState(){
        Supplier<Integer> f = Lambdas.posNumLambda();
        for (int i = 0; i < 5; i++ ){
            f.get();
        }
        assertEquals(f.get(),6);
    }/*
    @Test
    public void testPosNumOutState(){
        final int numStart = 0;
        assertEquals(1,Lambdas.posNumFun.apply(numStart));
    }*/

    @Test
    public void nNumOnConsole(){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        Lambdas.writeOut.accept(5);

        System.setOut(originalOut);

        String capturedOutput = outputStream.toString().trim();

        // Define the expected output
        String expectedOutput = "5\r\n5\r\n5\r\n5\r\n5";

        // Assert the output
        assertEquals(expectedOutput, capturedOutput);
    }

    @Test
    public void randomNumOnConsole(){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        Random random = new Random();
        int randNum = random.nextInt(100);

        Consumer<Integer> g = (n) -> {
            for(int i = 0; i < randNum; i++){
                System.out.println(n);
            }
        };

        g.accept(5);

        System.setOut(originalOut);

        String capturedOutput = outputStream.toString().trim();

        // Define the expected output  //("")

        String expectedOutput = "5\r\n".repeat(Math.max(0, randNum - 1)) + "5";

        // Assert the output
        assertEquals(expectedOutput, capturedOutput);
    }
    @Test
    public void isPrimeFalseTest(){
        Predicate<Integer> test = Lambdas.isPrime;
        assertFalse(test.test(9));
    }
    @Test
    public void isPrimeTrueTest(){
        Predicate<Integer> test = Lambdas.isPrime;
        assertTrue(test.test(11));
    }
    @Test
    public void primeNumTest(){
        Supplier<Integer> primeNumSupplier = Lambdas.primeNums;
        primeNumSupplier.get();
        primeNumSupplier.get();
        primeNumSupplier.get();
        assertEquals(primeNumSupplier.get(),7);
    }
    @Test
    public void pointsOnPlaneTest(){
        PointDummy test = new PointDummy(4,0);
        Supplier<PointDummy> pointDummySupplier = Lambdas.pointsOnPlane;
        pointDummySupplier.get();
        pointDummySupplier.get();
        pointDummySupplier.get();
        assertEquals(test,pointDummySupplier.get());
    }




}
