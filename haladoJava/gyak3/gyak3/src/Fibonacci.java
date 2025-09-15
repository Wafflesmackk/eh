
public class Fibonacci {
    public int iterativeFibo(int n){
        int prev = 0, curr = 1, temp;
        if (n == 0)
            return prev;
        for (int i = 2; i <= n; i++) {
            temp = prev + curr;
            prev = curr;
            curr = temp;
        }
        return curr;
    }

    public int recursiveFibo(int n){
        if (n <= 1)
            return n;
        return recursiveFibo(n - 1) + recursiveFibo(n - 2);
    }


}
