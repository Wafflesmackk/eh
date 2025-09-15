public class Point{
    private int x;
    private int y;


    public Point(int x_, int y_){
        this.x = x_;
        this.y = y_;
    }

    public String toString(){
        return "(" + this.x + ", " + this.y + ")"; 
    }

}