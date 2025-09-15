import java.util.function.BiFunction;

public class Main {
    public static void main(String[] args) {
        /*NumberGame numberGame = new NumberGame(2,10);
        numberGame.startGame();*/
            Integer[] array = {1, 2, 3, 4};
            BiFunction<Integer,Integer,Integer> lambda = (n, m) -> n + m;

            Assoc<Integer> assoc = new Assoc<>();

            Integer result = assoc.applyAssoc(lambda, array);
            System.out.println("Result: " + result);
    }
}