import java.util.concurrent.RecursiveTask;
import java.util.function.BiFunction;
import java.util.function.Function;

public class AssocTask<T> extends RecursiveTask<T> {
    private final int  start;

    private final int end;

    private T[] inputArray;

    private BiFunction<T,T,T> lambda;

    public AssocTask(int start, int end, T[] inputArray, BiFunction<T, T, T> lambda) {
        this.start = start;
        this.end = end;
        this.inputArray = inputArray;
        this.lambda = lambda;
    }

    @Override
    protected T compute() {
        if (end - start == 1) {
            return inputArray[start];
        } else {
            int mid = (start + end) / 2;
            AssocTask<T> lowerTask = new AssocTask<>(start, mid, inputArray, lambda);
            AssocTask<T> upperTask = new AssocTask<>(mid, end, inputArray, lambda);

            lowerTask.fork();
            T upperResult = upperTask.compute();
            T lowerResult = lowerTask.join();

            return lambda.apply(lowerResult, upperResult);
        }
    }
}
