import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Stacks<Integer> trialStack = new Stacks<Integer>(3);
        trialStack.push(1);
        trialStack.push(2);
        trialStack.push(3);

        System.out.println(trialStack.top());

        trialStack.pop();
        trialStack.push(4);
        System.out.println(trialStack.top());
    }
}
