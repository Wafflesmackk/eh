public class PointDummy {
    public int x;
    public int y;

    public PointDummy(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object object){
        if(object == this){
            return true;
        }
        else if (!(object instanceof PointDummy other)) {
            return false;
        }
        else{
            return (other.x == this.x) && (other.y == this.y);
        }

    }
}
