import java.util.HashMap;

public class Bag<T> {
    private HashMap<T, Integer> data;

    public Bag(){
        this.data = new HashMap<T, Integer>();
    }
    
    public Bag(HashMap<T, Integer> initData){
        this.data = new HashMap<T, Integer>(initData);
    }

    public void add(T element) {
        /*if(this.data.containsKey(element)){
            this.data.replace(element, this.data.get(element)+1);
        }*/
        Integer currentElemCount = this.data.get(element);
        if(currentElemCount != null){
            this.data.replace(element, currentElemCount+1);
        }
        else{
            this.data.put(element, 1);
        }
        
    }

    public Integer countOf(T element){
        Integer currentElemCount = this.data.get(element);
        if(currentElemCount != null){
            return currentElemCount;
        }
        else{
            return 0;
        }
    }

    public void remove(T element) throws NotInBagException{
        Integer currentElemCount = this.data.get(element);
        if(currentElemCount == null){
            throw new NotInBagException("the element is not in the bag");
        }
        else if(currentElemCount > 1){
            this.data.replace(element, currentElemCount - 1);
        }
        else if(currentElemCount == 1){
            this.data.remove(element);
        }

    }
}
