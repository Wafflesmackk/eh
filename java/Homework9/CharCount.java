import java.util.ArrayList;
import java.util.HashMap;

public class CharCount{
        private HashMap<Character, Integer> data;
    
        public CharCount(){
            this.data = new HashMap<Character, Integer>();
        }
    
        public CharCount(HashMap<Character, Integer> initalData){
            this.data = new HashMap<Character, Integer>(initalData);
        }
    
        public HashMap<Character, Integer> getData(){
            return new HashMap<Character, Integer>(this.data);
        }

        public void countChars(String str){
            char[] chars = str.toCharArray();
            for(char key : chars){
                if(this.getData().containsKey(key)){
                    Integer mult = this.data.get(key) + 1;
                    this.data.replace(key, mult);
                }
                else{
                    this.data.put(key, 1);
                }
            }
        }

}