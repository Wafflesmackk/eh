import java.util.ArrayList;

public class LinkedList {
    public ArrayList<Integer> number = new ArrayList<Integer>(); 
    
    public LinkedList() {
        this.number = new ArrayList<Integer>();
    }


    public void divisors(int a){
        for(int i = 1; i <= a; i++){
            if(a % i == 0 ){
                this.number.add(i);
            }   
        }
    }

    public static void main(String[] args) {
        LinkedList idk = new LinkedList();
        int a = 60;
        idk.divisors(a);
        System.out.println(idk.number);
    }
}
