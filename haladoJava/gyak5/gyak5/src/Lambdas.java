import java.util.ArrayList;
import java.util.List;
import java.util.function.*;

public class Lambdas {
    //int numStart = 0;
   //public static IntFunction<Integer> posNumFun = n -> n + 1;
    //System.out.println(posNumFun.apply(0));

    Supplier<Integer> f = posNumLambda();
    public static Consumer<Integer> writeOut = (n) -> {
        for(int i = 0; i < n; i++){
            System.out.println(n);
        }
    };

    public static Supplier<Integer> posNumLambda() {
        final int[] counter = new int[1];
        return (() -> ++counter[0]);
    }

    public static Predicate<Integer> isPrime = n -> {
        if (n <= 1) return false;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    };

    static final int[] statePrimes = new int[1];
    public static Supplier<Integer> primeNums = () -> {
        Predicate<Integer> primeTester = isPrime;
        while (true){
            ++statePrimes[0];
            if(primeTester.test(statePrimes[0])){
                return statePrimes[0];
            }
        }
    };

    static final PointDummy[] statePoints = {new PointDummy(0,0)};

    public static Supplier<PointDummy> pointsOnPlane = () ->{
            statePoints[0].x++;
            if(statePoints[0].x == Integer.MAX_VALUE){
                statePoints[0].x = 0;
                if(statePoints[0].y != Integer.MAX_VALUE){
                    statePoints[0].y++;
                }
            }
            return statePoints[0];

    };

}
