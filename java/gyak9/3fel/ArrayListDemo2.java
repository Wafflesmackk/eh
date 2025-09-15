import java.util.Collection;
import java.util.ArrayList;

public class ArrayListDemo2 {
    public static void minToFront(ArrayList<Integer> nums){
        if (nums == null){
            throw new IllegalArgumentException();
        } 
        else{
            Integer min = Collections.min(nums);
            nums.removeIf(elem -> (elem == min)); 
            nums.add(0, min);
        }
    }
}
