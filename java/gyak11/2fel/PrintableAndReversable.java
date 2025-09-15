public class PrintableAndReversable extends Point implements Reversable, Printable{


        private int prevX;
        private int prevY;
        public PrintableAndReversable(int x_, int y_){
           super(x_,y_);
        }
        public void print(){
            System.out.println("(" + this.x + ", " + this.y + ")");
        }
        public void reverse(){
            this.prevX = this.x;
            this.prevY= this.y;
            this.x = this.prevY;
            this.y = this.prevX;
    
        }
        public int getX() {return this.x;}
        public int getY(){return this.y;}
    
        public void setX(int x_){
            this.prevX = this.x;
            this.prevY = this.y;
            this.x = x_;
        }
    
        public void setY(int y_){
            this.prevX = this.x;
            this.prevY = this.y;
            this.y = y_;
        }
    
    
    }
    
