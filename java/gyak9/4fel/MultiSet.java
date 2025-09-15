import java.util.ArrayList;
import java.util.HashMap;

public class MultiSet {
    private HashMap<String, Integer> data;

    public MultiSet(){
        this.data = new HashMap<String, Integer>();
    }

    public MultiSet(HashMap<String, Integer> initalData){
        this.data = new HashMap<String, Integer>(initalData);
    }

    public HashMap<String, Integer> getData(){
        return new HashMap<String, Integer>(this.data);
    }

    public void put(String str){   ///MultiSetnek a cuccosa
        if (this.data.containsKey(str)){
            Integer mult = this.data.get(str) + 1;
            this.data.replace(str, mult);
        }
        else{
            this.data.put(str, 1);  //Hashmap putja
        }
    }

    public MultiSet intersect(MultiSet other){
        MultiSet result = new MultiSet();
        Integer min;

        for(String key : this.data.keySet())
        {
           if(other.getData().containsKey(key)){
            min = Math.min(this.data.get(key), other.getData().get(key));
            result.getData().put(key, min);
           }
        }
        return result;
    }

}

