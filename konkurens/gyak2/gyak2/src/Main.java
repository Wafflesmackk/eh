import java.util.ArrayList;
import java.util.List;

public class Main {
    /*public static void main(String[] args) {
        String msg = "I am a custom thread with a large scope.";

        new Thread(() -> {
            System.out.println(msg);
        }).start();
    }*/

   /*public static void main(String[] args) {
        ToiToi toilet = new ToiToi();

        new Thread(() -> {
            toilet.use("Alice");
        }).start();

        new Thread(() -> {
            toilet.use("Bob");
        }).start();
    }*/
    //*
    public static void main(String[] args) {
        // Initiate all required objects
        List primes = new ArrayList<>();
        PrimeFinder pf = new PrimeFinder(primes);
        Thread t = new Thread(pf);

        // Start searching for primes on new thread
        t.start();

        // Wait for 1000 ms, hopefully it is enough to find primes
        try {Thread.sleep(1000);}
        catch(InterruptedException ie) {ie.printStackTrace();}

        // Signal the end of prime search
        pf.stop();

        // Wait until helper thread successfully terminates
        try {t.join();}
        catch (InterruptedException ie) {ie.printStackTrace();}

        // Check the amount of primes found during the search
        System.out.println(primes.size());
    }//*/
}