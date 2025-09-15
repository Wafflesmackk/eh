import java.util.*;

public class BiMap<K extends Comparable<K>, V extends Comparable<V>> {
    private TreeMap<K, V> keyToValueMap;
    private TreeMap<V, K> valueToKeyMap;

    public BiMap(Comparator<K> keyComparator, Comparator<V> valueComparator) {
        keyToValueMap = new TreeMap<>(keyComparator);
        valueToKeyMap = new TreeMap<>(valueComparator);
    }

    public static <K extends Comparable<K>, V extends Comparable<V>> BiMap<K, V> create() {
        return new BiMap<>(null, null);
    }

    public static <K extends Comparable<K>, V extends Comparable<V>> BiMap<K, V> create(Comparator<K> keyComparator, Comparator<V> valueComparator) {
        return new BiMap<>(keyComparator, valueComparator);
    }

    public void insert(K key, V value) {
        keyToValueMap.put(key, value);
        valueToKeyMap.put(value, key);
    }

    public V findValueByKey(K key) {
        return keyToValueMap.get(key);
    }

    public K findKeyByValue(V value) {
        return valueToKeyMap.get(value);
    }

    public void insertAll(List<? extends K> keys, List<? extends V> values) {
        if (keys.size() != values.size()) {
            throw new IllegalArgumentException("Lists must have the same length");
        }
        for (int i = 0; i < keys.size(); i++) {
            insert(keys.get(i), values.get(i));
        }
    }

    public static void main(String[] args) {
        BiMap<String, Integer> biMap = BiMap.create();

        List<String> keys = Arrays.asList("apple", "banana", "cherry");
        List<Integer> values = Arrays.asList(1, 2, 3);

        biMap.insertAll(keys, values);

        System.out.println("Value for 'apple': " + biMap.findValueByKey("apple"));
        System.out.println("Key for value 2: " + biMap.findKeyByValue(2));
    }
}
