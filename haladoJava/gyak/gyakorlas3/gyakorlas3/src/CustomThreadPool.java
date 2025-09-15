import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;

public class CustomThreadPool {

    public static class CustomExecutorService {
        private final int nThreads;
        private final List<WorkerThread> threads;
        private final BlockingQueue<Runnable> taskQueue;

        public CustomExecutorService(int nThreads) {
            this.nThreads = nThreads;
            this.threads = new LinkedList<>();
            this.taskQueue = new LinkedBlockingQueue<>();

            for (int i = 0; i < nThreads; i++) {
                WorkerThread thread = new WorkerThread();
                thread.start();
                threads.add(thread);
            }
        }

        public void submit(Runnable task) {
            try {
                taskQueue.put(task);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        public void shutdown() {
            for (WorkerThread thread : threads) {
                thread.interrupt();
            }
        }

        private class WorkerThread extends Thread {
            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Runnable task = taskQueue.take();
                        task.run();
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public static void main(String[] args) {
        int n = Runtime.getRuntime().availableProcessors();
        CustomExecutorService customExecutorService = new CustomExecutorService(n);

        for (int i = 0; i < 10; i++) {
            int taskId = i;
            customExecutorService.submit(() -> {
                int randomSleepTime = ThreadLocalRandom.current().nextInt(1000, 5000);
                try {
                    Thread.sleep(randomSleepTime);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                System.out.println("Task " + taskId + " is done.");
            });
        }

        customExecutorService.shutdown();
    }
}
