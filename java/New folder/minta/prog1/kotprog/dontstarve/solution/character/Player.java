package prog1.kotprog.dontstarve.solution.character;

import prog1.kotprog.dontstarve.solution.character.actions.Action;
import prog1.kotprog.dontstarve.solution.inventory.BaseInventory;
import prog1.kotprog.dontstarve.solution.inventory.Inventory;
import prog1.kotprog.dontstarve.solution.inventory.items.AbstractItem;
import prog1.kotprog.dontstarve.solution.level.Level;
import prog1.kotprog.dontstarve.solution.level.Field;
import prog1.kotprog.dontstarve.solution.utility.Position;

import java.util.ArrayList;

import static java.lang.Math.round;

public class Player implements BaseCharacter{
    protected String name;
    protected int speed;
    protected int hunger;
    protected int hp;

    protected Inventory inventory;
    protected Position position;
    protected Action lastAction;

    protected Field currField;

    public Player(String name_, Position pos_){
        this.name = name_;
        this.position = pos_;
        inventory = new Inventory();
        speed = 1;
        hunger = 100;
        hp = 100;
    }

    @Override
    public float getSpeed() {
        float multipliers = 1;
        if(hp < 50 && hp >= 30){
            multipliers = (float) (multipliers * 0.9);
        }
        else if(hp < 30 && hp >= 10){
            multipliers = (float) (multipliers * 0.75);
        }
        else if(hp < 10){
            multipliers = (float) (multipliers * 0.6);
        }

        if(hunger < 50 && hunger >= 20){
            multipliers = (float) (multipliers * 0.9);
        }
        else if(hunger < 20 && hunger > 0){
            multipliers = (float) (multipliers * 0.8);
        }
        else if(hunger == 0){
            multipliers = (float) (multipliers * 0.5);
        }

        return speed * multipliers;
    }

    @Override
    public float getHunger() {
        return hunger;
    }

    @Override
    public float getHp() {
        return hp;
    }

    @Override
    public BaseInventory getInventory() {
        return inventory;
    }

    @Override
    public Position getCurrentPosition() {
        return position;
    }

    @Override
    public Action getLastAction() {
        return lastAction;
    }

    public void setToField(Level level){
        int x = round(position.getX());
        int y = round(position.getY());

        if(x > level.getWidth()){x = level.getWidth();}

        if (y > level.getHeight()){y = level.getHeight();}

        if (x < 0){ x= 0;}

        if(y < 0) {y = 0;}

        if(level.getField(x,y).isWalkable()) {
            currField = level.getField(x, y);
        }
    }

    public void setToField(int x, int y, Level level){
        if(level.getField(x,y).isWalkable()) {
            currField = level.getField(x, y);
        }
    }

    public void setItems(ArrayList <AbstractItem> items){
        inventory.setItems(items);
    }

    public void organizeInventory(){
        inventory.organize();
    }

    @Override
    public String getName() {
        return name;
    }
}
