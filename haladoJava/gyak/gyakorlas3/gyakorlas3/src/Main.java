import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ForkJoinPool;

public class Main {

    public static <T> T applyAssoc(T[] values, AssocFunction<T> lambda) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return forkJoinPool.invoke(new AssocTask<>(values, 0, values.length, lambda));
    }

    public static void main(String[] args) {
        Integer[] array = {1, 2, 3, 4};
        AssocFunction<Integer> lambda = (n, m) -> n + m;

        Integer result = applyAssoc(array, lambda);
        System.out.println("Result: " + result);
    }
}

interface AssocFunction<T> {
    T apply(T a, T b);
}

class AssocTask<T> extends RecursiveTask<T> {
    private final T[] values;
    private final int start;
    private final int end;
    private final AssocFunction<T> lambda;

    public AssocTask(T[] values, int start, int end, AssocFunction<T> lambda) {
        this.values = values;
        this.start = start;
        this.end = end;
        this.lambda = lambda;
    }

    @Override
    protected T compute() {
        if (end - start == 1) {
            return values[start];
        } else {
            int mid = (start + end) / 2;

            AssocTask<T> lowerTask = new AssocTask<>(values, start, mid, lambda);
            AssocTask<T> upperTask = new AssocTask<>(values, mid, end, lambda);

            lowerTask.fork();
            T upperResult = upperTask.compute();
            T lowerResult = lowerTask.join();

            return lambda.apply(lowerResult, upperResult);
        }
    }
}
