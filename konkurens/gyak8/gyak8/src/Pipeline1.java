import java.util.List;
import java.util.concurrent.*;

public class Pipeline1 {
    public static void main(String[] args) throws Exception {
        var NO_FURTHER_INPUT1 = "";
        var NO_FURTHER_INPUT2 = -1;

        // TODO create the queue
        var bq1 = new LinkedBlockingQueue<String>();
        var bq2 = new LinkedBlockingQueue<Integer>();

        var pool = Executors.newCachedThreadPool();

        pool.submit(() -> {
            bq1.addAll(List.of("a", "bb", "ccccccc", "ddd", "eeee", NO_FURTHER_INPUT1));
        });

        pool.submit(() -> {
            while (true) {
                for( var txt : bq1.toArray()){
                    if(txt.toString() == NO_FURTHER_INPUT1){
                        break;
                    }
                    bq2.add(txt.toString().length());
                }
                bq2.add(NO_FURTHER_INPUT2);

                // TODO queue #1 ====> txt  len ===> queue #2
                // TODO also handle NO_FURTHER_INPUTs
            }
        });

        pool.submit(() -> {
            while (true) {
                for (Object len : bq2.toArray()){
                    if(Integer.parseInt(len.toString()) == NO_FURTHER_INPUT2){
                        break;
                    }
                    System.out.println(len);
                }
                break;
                // TODO queue #2 ====> len ====> print it
                // TODO also handle NO_FURTHER_INPUTs
            }
        });

        pool.shutdown();
        pool.awaitTermination(10, TimeUnit.SECONDS);
    }
}
