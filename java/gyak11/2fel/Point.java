public class Point {
    protected int x;
    protected int y;

    public Point(int x_, int y_){
        this.x = x_;
        this.y = y_;
    }

    public int getX() {return this.x;}
    public int getY() {return this.y;}

    public void setX(int x_){
        this.x = x_;
    }

    public void setY(int y_){
        this.y = y_;
    }

    @Override
    public String toString(){
        return "(" + this.x + ", " + this.y + ")";
    }
}
