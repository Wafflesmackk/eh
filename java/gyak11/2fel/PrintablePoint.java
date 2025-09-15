public class PrintablePoint implements Printable{
    private int x;
    private int y;
    public PrintablePoint(int x_, int y_){
        this.x = x_;
        this.y = y_;
    }
    public void print(){
        System.out.println("(" + this.x + ", " + this.y + ")");
    }


}
