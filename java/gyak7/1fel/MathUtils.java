
public class MathUtils {
    public static double power(int base, int exponent){
        double result = 1;
        for(int i = 1; i <= Math.abs(exponent); i++){
            result *= base;
        }
        if ( exponent <= 0){
            if(exponent == 0){
                result = 1;
                }
            result = 1 / result;
        }

        return result;
    }
}
