package prog1.kotprog.dontstarve.solution.level;

import prog1.kotprog.dontstarve.solution.character.Player;
import prog1.kotprog.dontstarve.solution.inventory.items.*;

import java.util.ArrayList;

public class Field implements BaseField{

    private int x;
    private int y;
    private int time = 0;

    private int timeRemain;

    private FieldType type;

    private ArrayList<AbstractItem> items;

    public Field(int x_, int y_, FieldType type_, int time_){
        x = x_;
        y = y_;
        type = type_;
        items = new ArrayList<AbstractItem>();
        time = time_;
        timeRemain = time_;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public FieldType getType() { return type; }


    @Override
    public boolean isWalkable() {
        if(type == FieldType.WATER){
            return false;
        }
        return true;
    }

    @Override
    public boolean hasTree() {
        if(type != FieldType.TREE){
            return false;
        }
        return true;
    }

    @Override
    public boolean hasStone() {
        if(type != FieldType.STONE){
            return false;
        }
        return true;
    }

    @Override
    public boolean hasTwig() {
        if(type != FieldType.TWIG){
            return false;
        }
        return true;
    }

    @Override
    public boolean hasBerry() {
        if(type != FieldType.BERRY){
            return false;
        }
        return true;
    }

    @Override
    public boolean hasCarrot() {
        if(type != FieldType.CARROT){
            return false;
        }
        return true;
    }

    @Override
    public boolean hasFire() {
        return false;
    }

    public void useField(Player player){
        if(timeRemain > 0 && (type != FieldType.EMPTY && type != FieldType.WATER)){
            if(type == FieldType.CARROT || type == FieldType.TWIG || type == FieldType.BERRY){
                timeRemain--;
            }
            else if(type == FieldType.TREE && player.getInventory().equippedItem().getClass() == ItemAxe.class ){
                timeRemain--;
            }
            else if(type == FieldType.STONE && player.getInventory().equippedItem().getClass() == ItemPickaxe.class) {
                timeRemain--;
            }
        }
        if(timeRemain == 0 && (type != FieldType.EMPTY && type != FieldType.WATER)){
            timeRemain = time;
            if(type == FieldType.CARROT){
                if(player.getInventory().emptySlots() > 0) {
                    player.getInventory().addItem(new ItemRawCarrot(1));
                }
                else{
                    items.add(new ItemRawCarrot(1));
                }
            }
            else if (type == FieldType.BERRY) {
                if(player.getInventory().emptySlots() > 0) {
                    player.getInventory().addItem(new ItemRawBerry(1));
                }
                else{
                    items.add(new ItemRawBerry(1));
                }
            }
            else if(type == FieldType.TWIG){
                if(player.getInventory().emptySlots() > 0) {
                    player.getInventory().addItem(new ItemTwig(1));
                }
                else{
                    items.add(new ItemTwig(1));
                }
            }
            else if(type == FieldType.STONE && player.getInventory().equippedItem().getClass() == ItemPickaxe.class){
                type = FieldType.EMPTY;
                items.add(new ItemStone(3));
            }
            else if(type == FieldType.TREE && player.getInventory().equippedItem().getClass() == ItemAxe.class ){
                type = FieldType.EMPTY;
                items.add(new ItemLog(2));
            }
        }
    }

    @Override
    public AbstractItem[] items() {
        AbstractItem[] itemArray = new AbstractItem[items.size()];
        for (int i= 0; i < items.size(); i++){
            itemArray[i] = items.get(i);
        }
        return itemArray;
    }
}
