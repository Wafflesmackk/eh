import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class BankClients {
    static int CLIENT_COUNT = 10;
    static int STEP_COUNT = 100_000;

    static int totalBorrowedSum = 0;
    static int[] borrowedSums = new int[CLIENT_COUNT];

    public static void main(String[] args) throws Exception {
        var pool = Executors.newFixedThreadPool(CLIENT_COUNT);
        for (int i = 0; i < CLIENT_COUNT; i++) {
            var clientIdx = i;
            pool.submit(() -> {
                int clientBorrowedSum = 0;
                for (int j = 0; j < STEP_COUNT; j++) {
                    int sum = ThreadLocalRandom.current().nextInt(1, 11);
                    synchronized (BankClients.class) {
                        totalBorrowedSum += sum;
                    }
                    clientBorrowedSum += sum;
                }

                borrowedSums[clientIdx] = clientBorrowedSum;
                System.out.printf("%d done%n", clientIdx);
            });
        }

        pool.shutdown();
        pool.awaitTermination(1, TimeUnit.DAYS);

        int totalSum = 0;
        for (int sum : borrowedSums)   totalSum += sum;

        System.out.printf("%d %d%n", totalSum, totalBorrowedSum);
    }
}
