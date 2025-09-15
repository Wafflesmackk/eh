package posta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Posta {
    ArrayList<Postafiok> postafiokok;
    String cim;

    HashMap<String,Posta> tovabbitasBejegyzes;

    public Posta(ArrayList<Postafiok> postafiokok, String cim, HashMap<String, Posta> tovabbitasBejegyzes) {
        this.postafiokok = postafiokok;
        this.cim = cim;
        this.tovabbitasBejegyzes = tovabbitasBejegyzes;
    }

    public Posta(String cim){
        this.cim = cim;
    }

    public void valogat(Level level){
        level.utazas(this);
        if(!Objects.equals(level.getCelAllomas(), cim)){
            tovabbitasBejegyzes.get(level.getCelAllomas()).valogat(level);
        }
        else{
            postafiokok.get(level.getIranyitoszam() % postafiokok.size()).fogad(level);
        }
    }

    public String getCim() {
        return cim;
    }



}
