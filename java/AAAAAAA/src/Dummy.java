import java.util.ArrayList;

public class Dummy {

    public Dummy(){}

    public boolean moveItem(int index, int newIndex, ArrayList<Integer> items) {
        if (index - 1 > 0 && index - 1 < 10 && newIndex - 1 > 0 && newIndex - 1 < 10) {
                int toMove = items.get(index - 1);
                items.remove(index - 1);
                items.add(newIndex - 1, toMove);
        }
        return false;
    }
}
