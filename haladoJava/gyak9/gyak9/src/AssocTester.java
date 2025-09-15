import org.junit.jupiter.api.Test;

import java.util.function.BiFunction;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AssocTester {

    @Test
    public void taskTester(){
        Integer[] array = {1, 2, 3, 4};
        BiFunction<Integer,Integer,Integer> lambda = (n, m) -> n + m;

        Assoc<Integer> assoc = new Assoc<>();

        Integer result = assoc.applyAssoc(lambda, array);
        assertEquals(result,10);

    }
}
