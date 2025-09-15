import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class BankClients2 {
    static int CLIENT_COUNT = 10;
    static int STEP_COUNT = 100_000;

    static int totalBorrowedSum = 0;

    static Set<Future<Integer>> futures = new HashSet<>();

    public static void main(String[] args) throws Exception {
        var pool = Executors.newFixedThreadPool(CLIENT_COUNT);
        for (int i = 0; i < CLIENT_COUNT; i++) {
            var clientIdx = i;
            Future<Integer> future = pool.submit(() -> {
                int clientBorrowedSum = 0;
                for (int j = 0; j < STEP_COUNT; j++) {
                    int sum = ThreadLocalRandom.current().nextInt(1, 11);
                    synchronized (BankClients2.class) {
                        totalBorrowedSum += sum;
                    }
                    clientBorrowedSum += sum;
                }

                System.out.printf("%d done%n", clientIdx);
                return clientBorrowedSum;
            });
            futures.add(future);
        }

        int totalSum = 0;
        for (var future : futures)   totalSum += future.get();

        pool.shutdown();

        System.out.printf("%d %d%n", totalSum, totalBorrowedSum);
    }
}
