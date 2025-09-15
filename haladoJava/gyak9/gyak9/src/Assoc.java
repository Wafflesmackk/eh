import java.util.concurrent.ForkJoinPool;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Assoc<T> {

    public T applyAssoc(BiFunction<T, T, T> assocLambda, T[] inputArray){
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return forkJoinPool.invoke(new AssocTask<>( 0, inputArray.length, inputArray, assocLambda));
    }
}
