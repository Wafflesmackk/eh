import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.ThreadLocalRandom;

public class DataMover2 {

    public static AtomicInteger arrivalCount = new AtomicInteger(0);
    public static AtomicInteger totalSent = new AtomicInteger(0);
    public static AtomicInteger totalArrived = new AtomicInteger(0);
    public static ExecutorService pool = Executors.newFixedThreadPool(100);
    public static List<BlockingQueue<Integer>> queues = new ArrayList<>();
    public static List<Future<DataMover2Result>> moverResults = new ArrayList<>();

    private static int[] waitTimes;

    public static class DataMover2Result {
        public int count;
        public int data;
        public int forwarded;
        public List<Integer> discards;

        public DataMover2Result() {
            this.count = 0;
            this.data = 0;
            this.forwarded = 0;
            this.discards = new ArrayList<>();
        }
    }

    private static DataMover2Result computeResult(int taskNumber) {
        DataMover2Result result = new DataMover2Result();

        while (arrivalCount.get() < 5 * queues.size()) {
            try {
                int sendValue = ThreadLocalRandom.current().nextInt(10001);
                System.out.println("total   " + arrivalCount + "/" + (5 * queues.size()) + " | #" + taskNumber
                        + " sends " + sendValue);
                totalSent.addAndGet(sendValue);
                queues.get((taskNumber + 1) % queues.size()).put(sendValue);

                int waitTime = ThreadLocalRandom.current().nextInt(701) + 300;
                Integer receivedValue = queues.get((taskNumber) % queues.size()).poll(waitTime, TimeUnit.MILLISECONDS);

                if (receivedValue == null) {
                    System.out.println("total   " + arrivalCount + "/" + (5 * queues.size()) + " | #" + taskNumber
                            + " got nothing...");
                    continue;
                }

                if (receivedValue % queues.size() == taskNumber) {
                    System.out.println("total   " + arrivalCount.incrementAndGet() + "/" + (5 * queues.size()) + " | #"
                            + taskNumber + " got " + receivedValue);
                    result.count++;
                    result.data += receivedValue;
                } else {
                    int newTaskNum = (taskNumber + 1) % queues.size();
                    System.out.println("total   " + arrivalCount + "/" + (5 * queues.size()) + " | #" + taskNumber
                            + " forwards " + receivedValue + " [" + newTaskNum + "]");
                    result.forwarded++;
                    queues.get(newTaskNum).put(receivedValue - 1);
                }
                try {
                    Thread.sleep(waitTimes[taskNumber]);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    public static void main(String[] args) {

        if (args.length > 0) {
            waitTimes = new int[args.length];
            for (int i = 1; i < args.length; i++) {
                waitTimes[i] = Integer.parseInt(args[i]);
            }

        } else {
            waitTimes = new int[] { 123, 111, 256, 404 };
        }

        int n = waitTimes.length;

        for (int i = 0; i < n; i++) {
            queues.add(new LinkedBlockingQueue<>());
        }

        for (int i = 0; i < n; i++) {
            int finalI = i;
            moverResults.add(pool.submit(() -> computeResult(finalI)));
        }

        pool.shutdown();
        try {
            pool.awaitTermination(30, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (Future<DataMover2Result> result : moverResults) {
            try {
                DataMover2Result r = result.get();
                totalArrived.addAndGet(r.data + r.forwarded);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        List<Integer> discards = new ArrayList<>();
        int discardValue = 0;
        for (BlockingQueue<Integer> queue : queues) {
            for (Integer value : queue) {
                discards.add(value);
                discardValue += value;
            }
        }
        int total = discardValue + totalArrived.get();
        System.out.println("discarded " + discards + " = " + discardValue);
        if (totalSent.get() == total) {
            System.out.println("sent " + totalSent + " === got " + total + " = " + totalArrived.get() + " + discarded "
                    + discardValue);
        } else {
            System.out.println("WRONG sent " + totalSent + " !== got " + total + " = " + totalArrived.get()
                    + " + discarded " + discardValue);
        }

    }

}
