
package task.extension.MultiProducer2;

import task.extension.MultiProducer2State;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class cachedMultiProducer {


    public static Supplier<Function<String,Supplier<String>>> provider(int decisionCount, Supplier<MultiProducer2State> decisionLambda) {
        List<String> cache = new ArrayList<>();
        cache.add("");

        return () -> (appendTxt) -> () -> {
            String value;
            if (cache.size() == 1) {
                value = cache.get(0);
                final String[] current = {value};
                cache.remove(0);
                List<String> newElements = Stream.iterate(0, i -> i < decisionCount, i -> i + 1)
                        .map(i -> decisionLambda.get())
                        .map(decision -> {
                                    if (decision == MultiProducer2State.EXTEND) {
                                        current[0] += appendTxt;
                                    }
                                    return current[0];
                                })
                        .toList();
                cache.addAll(newElements);
            } else {
                value = cache.get(0);
                cache.remove(0);
            }

            //System.out.println("cache" + cache);
            return value;
        };

    }



    public static <T> BiFunction<Supplier<T>, Supplier<T>, Supplier<T>> oneForEach(){
        return (firstLambda,secondLambda) ->{
            final boolean[] first = {true};
            return () -> {
                if (first[0]) {
                    first[0] = false;
                    return firstLambda.get();
                } else {
                    first[0] = true;
                    return secondLambda.get();
                }
            };
        };
    }
}
