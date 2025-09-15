package auto;
import auto.utils.Color;


public class Auto {
    String plate;
    Color color;
    public int maxSpeed;
    int num;

    static int statNum= 0;
    public Auto(String _plate, Color _color, int _maxSpeed){
        this.plate = _plate;
        this.maxSpeed = _maxSpeed;
        this.color = _color;
        this.num = statNum++;
    }

    public Auto(){
        this.plate = "AAA-000";
        this.maxSpeed = 120;
        this.color = Color.BLUE;
    }

    public boolean isFaster(Auto a, Auto b){
        return a.maxSpeed > b.maxSpeed;
        
    }



}