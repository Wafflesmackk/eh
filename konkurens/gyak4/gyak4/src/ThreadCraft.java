import java.util.ArrayList;
import java.util.List;

/**
 * Cheap version of Warcraft 3
 *
 * The simulation runs until the player builds the number of houses
 * specified in {@link Configuration}
 * Responsible for starting and ending threads, implementing
 * the logic of each kind of thread
 *
 * The threads to be implemented involves
 * 1, Builder - responsible for building the houses
 * 2, Miner - responsible for mining gold
 * 3, Logging - responsible for logging the state of the world
 *
 */
public class ThreadCraft {

    private static Resources resources = new Resources();

    // list for threads so we can access them when joining
    private static List<Thread> peons = new ArrayList<>();

    public static void main(String[] args) {

        // Start miner threads based on Configuration.NUMBER_OF_MINERS
        for (int i = 0; i < Configuration.NUMBER_OF_MINERS; ++i){
            Thread miner = new Thread(ThreadCraft::mineAction);
            peons.add(miner);
            miner.start();
        }

        // Start builder threads based on Configuration.NUMBER_OF_BUILDERS
        for (int i = 0; i < Configuration.NUMBER_OF_BUILDERS; ++i){
            Thread builder = new Thread(ThreadCraft::buildAction);
            peons.add(builder);
            builder.start();
        }
        // Start logging thread
        new Thread(ThreadCraft::loggingAction).start();

        // Wait for the threads to finish
        for(Thread peon: peons){
            try {
                peon.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Simulation over");
        logStatus();
    }

    /**
     * Should be used by miner threads
     *
     * Keeps mining until the goldmine runs out
     */
    private static void mineAction(){
        while(resources.getGoldmineCapacity() > 0){
            resources.tryToMineGold();
            sleepForMsec(Configuration.MINING_FREQUENCY);
        }
        System.out.println("Miner finished mining");
    }

    /**
     * Should be used by builder threads
     *
     * Keeps building houses when it has enough gold to do so
     * until reaches the house limit
     */
    private static void buildAction(){
        while(!isOver()){
            if(resources.tryToBuildHouse()){
                sleepForMsec(Configuration.BUILD_TIME);
            } else {
                sleepForMsec(Configuration.SLEEP_TIME);
            }
        }
        System.out.println("Builder finished building");
    }

    /**
     * Periodically logs the state of the world
     */
    private static void loggingAction(){
        while(!isOver()){
            logStatus();
            sleepForMsec(Configuration.LOGGING_FREQUENCY);
        }
    }

    /**
     * Logs current amount of gold, houses and state of goldmine
     */
    private static void logStatus(){
        System.out.println(
                "Gold: " + resources.getGold() +
                "\nHouses: " + resources.getHouses() +
                "\nGoldmine: " + resources.getGoldmineCapacity() +
                "\n**************");
    }

    /**
     * @return true if the simulation has reached its end
     */
    private static boolean isOver(){
        return resources.getHouses() == Configuration.HOUSE_LIMIT;
    }

    /**
     * Sleeping idle on the given thread for
     * @param msec milliseconds
     * If something interrupts this sleep, log the exception
     */
    public static void sleepForMsec(int msec){
        try {Thread.sleep(msec);}
        catch(Exception e) {e.printStackTrace();}
    }
}
