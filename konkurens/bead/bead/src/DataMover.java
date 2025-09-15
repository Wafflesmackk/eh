import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataMover implements Runnable {
    private static final List<Thread> movers = new ArrayList<>();
    private static int[] data = new int[0];
    private static final int iterations = 10;
    private static int[] waitTimes;
    private static int waitTime = 0;

    private final int threadIndex;

    public DataMover(int threadIndex, int waitTime) {
        this.threadIndex = threadIndex;
        DataMover.waitTime = waitTime;
    }

    @Override
    public void run() {
        for (int i = 0; i < iterations; i++) {

            try {
                Thread.sleep(waitTimes[threadIndex]);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (data) {
                data[threadIndex] -= threadIndex;

                System.out.println("#" + threadIndex + ": data " + threadIndex + " == " + data[threadIndex]);
                try {
                    Thread.sleep(waitTime);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                int nextIndex = (threadIndex + 1) %  data.length;
                data[nextIndex] += threadIndex;
                System.out.println("#" + threadIndex + ": data " + nextIndex + " -> " + data[nextIndex]);
            }
        }
    }


    public static void main(String[] args) {
        int waitTime = 123;
        waitTimes = new int[]{111, 256, 404};

        if(args.length > 0){
            waitTime = Integer.parseInt(args[0]);
            waitTimes = new int[args.length - 1];
            for(int i = 0; i < args.length - 1; i++){
                waitTimes[i] = Integer.parseInt(args[i + 1]);

            }
        }

        System.out.println(Arrays.toString(waitTimes));

        data = new int[waitTimes.length];
        for (int i = 0; i < waitTimes.length; i++) {
            data[i] = i * 1000;
        }

        for (int i = 0; i < waitTimes.length; i++) {
            DataMover mover = new DataMover(i, waitTime);
            Thread thread = new Thread(mover);
            movers.add(thread);
            thread.start();
        }

        for (Thread thread : movers) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Végső tömb tartalom: " + java.util.Arrays.toString(data));
    }
}
