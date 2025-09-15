import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class FibonacciTest {



    @org.junit.jupiter.api.Test
    void testIterativeFibo() {
        Fibonacci f = new Fibonacci();
        assertEquals(f.iterativeFibo(10),55);
    }

    @org.junit.jupiter.api.Test
    void testIterativeFiboNull() {
        Fibonacci f = new Fibonacci();
        assertEquals(f.iterativeFibo(0),0);
    }

    @org.junit.jupiter.api.Test
    void testRecursiveFibo() {
        Fibonacci f = new Fibonacci();
        assertEquals(f.recursiveFibo(10),55);
    }

    @org.junit.jupiter.api.Test
    void testRecursiveFiboNull() {
        Fibonacci f = new Fibonacci();
        assertEquals(f.recursiveFibo(0),0);
    }
    /*
    @Test
    @Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
    void failsIfExecutionTimeExceeds500Milliseconds() {
        FunctionsForTesting f = new FunctionsForTesting();
        f.infiniteFun();
    }*/

    @org.junit.jupiter.api.Test
    void exceptionTesting() {
        FunctionsForTesting f = new FunctionsForTesting();
        Exception exception = assertThrows(ArithmeticException.class, f::zeroDivision);
        assertEquals("/ by zero", exception.getMessage());
    }

    @org.junit.jupiter.api.Test
    void idkTest(){

        assertEquals(int.class,2);
    }


}