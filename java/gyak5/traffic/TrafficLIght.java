public class TarfficLight{

    public Color currentColor;

    public TarfficLight(Color inintColor){
        this.currentColor = inintColor;
    }

    public String giveSignal(){
        if (this.currentColor == GREEN){
            System.out.println("go");
        }
        else if(this.currentColor == YELLOW){
            System.out.println("wait a bit");
        }
        else if(this.currentColor == RED){
            System.out.println("stop");
        }
    }
    
}