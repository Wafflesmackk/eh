import java.util.List;

public class PrimeFinder implements Runnable {
    private List primes;
    private volatile boolean isRunning;
    public PrimeFinder(List primes) {
        this.primes    = primes;
        this.isRunning = true;
    }
    public synchronized void stop() {
        isRunning = false;
    }
    @Override public void run() {
        primes.add(2);
        for (int p=3; isRunning; p+=2) {
            boolean isPrime = true;
            for (int i=2; (i<(int)Math.sqrt(p)+1) && isPrime; i++)
                if (p % i == 0)
                    isPrime = false;
            if (isPrime)
                primes.add(p);
        }
    }
}