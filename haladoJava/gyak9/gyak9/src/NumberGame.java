import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NumberGame {

    private int playerNum;

    private int maxGuess;

    private ExecutorService executorService;
    private ConcurrentMap<Integer, Integer> numberMapConc = new ConcurrentHashMap<>();

    private HashMap<Integer,Integer> numberMap = new HashMap<>();

    public NumberGame() {
        this.playerNum = 5;
        this.maxGuess = 1000000;
        executorService = Executors.newFixedThreadPool(playerNum + 1);
    }

    public NumberGame(int playerNum, int maxGuess) {
        this.playerNum = playerNum;
        this.maxGuess = maxGuess;
        executorService = Executors.newFixedThreadPool(playerNum + 1);
    }

    public void startGame(){
        executorService.submit(this::controlGame);
        for (int i = 0; i < playerNum; i++) {
            final int playerId = i;
            executorService.submit(() -> playGame(playerId));
        }
    }

    public void controlGame(){
        while(true){

            try{
                Thread.sleep(1000);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(!numberMapConc.isEmpty()){
                int minNumber = Integer.MAX_VALUE;
                int minPlayerId = -1;

                boolean sameNumFlag = false;
                /*
                synchronized (numberMap) {
                    for (int id : numberMap.keySet()) {
                        int playerNumber = numberMap.get(id);
                        if (playerNumber < minNumber) {
                            minNumber = playerNumber;
                            minPlayerId = id;
                            sameNumFlag = false;
                        } else if (playerNumber == minNumber) {
                            sameNumFlag = true;
                        }
                    }
                }*/

                for (int id : numberMapConc.keySet()) {
                    int playerNumber = numberMapConc.get(id);
                    if (playerNumber < minNumber) {
                        minNumber = playerNumber;
                        minPlayerId = id;
                        sameNumFlag = false;
                    } else if (playerNumber == minNumber) {
                        sameNumFlag = true;
                    }
                }

                if (!sameNumFlag){
                    System.out.println("Min number: " + minNumber + ", Player ID: " + minPlayerId);
                }
                else{
                    System.out.println("No winners this round");
                }

            }

        }

    }

    private void playGame(int playerId) {
        Random random = new Random();
        while (!executorService.isShutdown()) {
            int randomNumber = random.nextInt(maxGuess + 1  );
           /*synchronized (numberMap) {
               numberMap.put(playerId, randomNumber);
           }*/
            numberMapConc.put(playerId, randomNumber);
        }

    }
}
