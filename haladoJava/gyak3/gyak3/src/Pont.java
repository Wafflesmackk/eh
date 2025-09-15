import java.awt.*;

public class Pont {
    int x;
    int y;


    public Pont(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void moveX(int moveBy){
        this.x += moveBy;
    }

    public void moveY(int moveBy){
        this.y += moveBy;
    }

    public void move(int moveByX, int moveByY){
        this.x += moveByX;
        this.y += moveByY;
    }

    public void inverse(){
        this.x = -1 * this.x;
        this.y = -1 * this.y;
    }

    public double distanceFrom(Pont pont){
        return Math.sqrt(Math.pow(this.x - pont.getX(),2) + Math.pow(this.y - pont.getY(),2));
    }



    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
