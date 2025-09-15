package task.compulsory.MultiProducer;

import java.util.Random;
import java.util.function.BiFunction;
import java.util.function.IntSupplier;
import java.util.function.Supplier;

public class MultiProducerFactory {

    public static Supplier<BiFunction<IntSupplier,Supplier<String>,String>> provider() {
        return () -> (amountLambda, contentLambda) ->{
            String value = "";
            String content = contentLambda.get();
            int num = amountLambda.getAsInt();
            while (num <= 0){
                num = amountLambda.getAsInt();
                content = contentLambda.get();
            }
            for (int i = 0; i < num ; i++) {
                value += content + " ,";
            }
            return value;
        };

    };
}
