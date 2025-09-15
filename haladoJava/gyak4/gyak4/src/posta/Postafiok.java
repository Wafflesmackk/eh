package posta;

import java.util.ArrayList;

public class Postafiok {

    private ArrayList<Level> levelek;



    public Postafiok(){
       this.levelek = new ArrayList<Level>();
    }

    public void fogad(Level level){
        levelek.add(level);
    }
    public ArrayList<Level> getLevelek() {
        return levelek;
    }
}
