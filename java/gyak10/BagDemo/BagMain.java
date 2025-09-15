import java.util.HashMap;

public class BagMain {
    public static void main(String[] args){
        HashMap<String, Integer> initData1 = new HashMap<String, Integer>();
        initData1.put("idk", 23);
        initData1.put("eh", 5);
        initData1.put("help", 8);
        initData1.put("k", 1);
        initData1.put("AAAA", 9);

        HashMap<Integer, Integer> initData2 = new HashMap<Integer, Integer>();
        initData2.put(2, 2);
        initData2.put(4, 5);
        initData2.put(8, 8);
        initData2.put(16, 88);
        initData2.put(32, 9);

        Bag<String> bag1 = new Bag<String>(initData1);
        Bag<Integer> bag2 = new Bag<Integer>(initData2);

        System.out.println(bag1.countOf("idk"));
        System.out.println(bag2.countOf(16));
    }
    
}
