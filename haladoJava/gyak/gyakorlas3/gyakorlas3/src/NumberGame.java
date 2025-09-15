import java.util.Random;
import java.util.concurrent.*;

public class NumberGame {

    private static final int NUM_PLAYERS = 5;
    private static final int MAX_NUMBER = 1000000;

    private ConcurrentMap<Integer, Integer> numberMap = new ConcurrentHashMap<>();
    private ExecutorService executorService = Executors.newFixedThreadPool(NUM_PLAYERS + 1);

    public void startGame() {
        // Start game controller thread
        executorService.submit(this::controlGame);

        // Start player threads
        for (int i = 1; i <= NUM_PLAYERS; i++) {
            final int playerId = i;
            executorService.submit(() -> playGame(playerId));
        }

        // Shutdown executorService after a delay (for demonstration purposes)
        try {
            Thread.sleep(10000); // Run the game for 10 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            executorService.shutdownNow();
        }
    }

    private void controlGame() {
        while (true) {
            try {
                Thread.sleep(1000); // Print results every 1 second
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (!numberMap.isEmpty()) {
                int minNumber = Integer.MAX_VALUE;
                int minPlayerId = -1;

                for (int playerId : numberMap.keySet()) {
                    int playerNumber = numberMap.get(playerId);

                    if (playerNumber < minNumber) {
                        minNumber = playerNumber;
                        minPlayerId = playerId;
                    }
                }

                System.out.println("Min number: " + minNumber + ", Player ID: " + minPlayerId);
            }
        }
    }

    private void playGame(int playerId) {
        Random random = new Random();

        while (!executorService.isShutdown()) {
            int randomNumber = random.nextInt(MAX_NUMBER + 1);
            numberMap.put(playerId, randomNumber);

            try {
                Thread.sleep(500); // Simulate some processing time before the next round
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        NumberGame numberGame = new NumberGame();
        numberGame.startGame();
    }
}
