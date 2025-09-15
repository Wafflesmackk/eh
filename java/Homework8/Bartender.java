import javax.swing.plaf.basic.BasicTreeUI.TreeCancelEditingAction;
import javax.xml.crypto.dsig.keyinfo.RetrievalMethod;

public class Bartender {
    public boolean order(Beverage ord, Guest person){
        if(ord.getLegalAge() >= 18 && person.IsAdult()){
            return true;
        }
        else if(ord.getLegalAge() >= 18 && !person.IsAdult()){
            return false;
        }
        else{
            return true;
        }
    }
}
