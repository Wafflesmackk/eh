import javax.xml.crypto.dsig.keyinfo.RetrievalMethod;

public class Beverage {
    private String name;
    private int legalAge;

    public Beverage(String _name, int _legalAge){
        if(_legalAge > 0 &&  _name != null ){

            this.legalAge = _legalAge;
            this.name = _name;
        }
        else{
            throw new IllegalArgumentException();
        }
    }

    public String getName(){
        return this.name;
    }

    public int getLegalAge(){
        return this.legalAge;
    }
    
}
