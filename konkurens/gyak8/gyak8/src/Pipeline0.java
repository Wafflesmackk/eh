import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Pipeline0 {
    public static void main(String[] args) throws Exception {
        var NO_FURTHER_INPUT = "";

        var bq = new LinkedBlockingQueue<String>();// TODO create the queue

        var pool = Executors.newCachedThreadPool();

        pool.submit(() -> {
            bq.addAll(List.of("a", "bb", "ccccccc", "ddd", "eeee", NO_FURTHER_INPUT));
        });

        pool.submit(() -> {
            while (true) {
                bq.stream().toList().forEach(System.out::println);
                // TODO queue #1 ====> txt ===> print it
                // TODO also handle NO_FURTHER_INPUTs
            }
        });

        pool.shutdown();
        pool.awaitTermination(10, TimeUnit.SECONDS);
    }
}
