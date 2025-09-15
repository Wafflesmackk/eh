public class ReversablePoint extends Point implements Reversable {
    private int prevX;
    private int prevY;

    public ReversablePoint(int x_, int y_){
        super(x_, y_);
    }


    public void reverse(){
        this.prevX = this.x;
        this.prevY= this.y;
        this.x = this.prevY;
        this.y = this.prevX;

    }

    @Override
    public void setX(int x_){
        this.prevX = super.getX();
        this.prevY = super.getY();
        this.x = x_;
    }

    @Override
    public void setY(int y_){
        this.prevX = this.x;
        this.prevY = this.y;
        this.y = y_;
    }

}
