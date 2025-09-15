import java.util.HashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    /* Keeps caller thread occupied for a given time */
    private static void work(int ms) {
        try {Thread.sleep(ms);}
        catch(Exception e) {e.printStackTrace();}
    }

    /* A type of building designed for storing supplies */
    private static class Depot {
        /* Timestamp for depot creation */
        private final long start = System.currentTimeMillis();

        /* Keep track of stored resources */
        private final HashMap<String, Integer> storage = new HashMap<>();

        private Lock l = new ReentrantLock();

        /* Load (add) newly arrrived items into storage */
        public /*synchronized*/ void load(String type, int amount) {
            work(2000); // prepare for operation (sleep)
            while(amount-- > 0) {
                work(1); // load one piece at a time
                l.lock();
                storage.merge(type, 1, Integer::sum); // update catalog
                l.unlock();
            }
        }

        /* Prints stored amounts and logging time */
        public void print() {
            long elapsed = System.currentTimeMillis() - start;
            System.out.println("-----");
            storage.forEach((type, amount)
                    -> System.out.println(String.format("%s: %d", type, amount)));
            System.out.println(String.format("Time: %d", elapsed));
        }
    }
    public static void main(String[] args) {
        /* Create depot where resources can be stored */
        Depot depot = new Depot();

        /* Delivery guy #1 */
        Thread t1 = new Thread(() -> {
            depot.load("wood", 1500);
            depot.load("food", 3500);
        });
        t1.start();

        /* Delivery guy #2 */
        Thread t2 = new Thread(() -> {
            depot.load("wood", 3500);
            depot.load("food", 1500);
        });
        t2.start();

        /* Logging while threads are active */
        for(; t1.isAlive() || t2.isAlive(); work(1000))
            depot.print();

        /* Log final state */
        depot.print();
    }
}