import java.util.ArrayList;


public class Stacks<T> {
    private ArrayList<T> data;
    private int top;
    private int size;

    Stacks(int size_){
        this.size = size_;
        this.data = new ArrayList<T>(size);
        this.top = -1;
    }
    
    public void push(T value){
        if (this.top + 1 == this.size) {
            throw new IndexOutOfBoundsException("its full, so its a stack overflow");
        }
        else {
            this.top++;

            if (this.data.size() > this.top){
                this.data.set(this.top, value);
            }
 
            else{
                this.data.add(value);   
            }
        }
    }

    public T top(){
        if (this.top == -1) {
            throw new IndexOutOfBoundsException("stack is empty, aka stack underflow");
        }
        else
            return this.data.get(this.top);
    }

    public void pop(){
        if (this.top == -1) {
            throw new IndexOutOfBoundsException("stack is empty, aka stack underflow");
        }
        else
            this.data.remove(this.top);
            this.top = this.top - 1;
    }

    int getSize(){
        return this.size;
    }
}
