package prog1.kotprog.dontstarve.solution.inventory;

import prog1.kotprog.dontstarve.solution.inventory.items.AbstractItem;
import prog1.kotprog.dontstarve.solution.inventory.items.EquippableItem;
import prog1.kotprog.dontstarve.solution.inventory.items.ItemType;

import javax.crypto.spec.IvParameterSpec;
import java.util.ArrayList;

public class Inventory implements BaseInventory{

    int maxSize = 10;


    private ArrayList<AbstractItem> items;

    public Inventory(){
        items = new ArrayList<>();
    }

    public ArrayList<AbstractItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<AbstractItem> items) {
        if(items.size() <=10) {
            this.items = items;
        }
        organize();
    }

    public void organize(){
        for(AbstractItem itemFirst : items){
            for(AbstractItem itemSecond : items){
                if(itemSecond.getType() == itemFirst.getType() && itemFirst.getAmount() < itemFirst.getStackAmount()){
                    int firstCount = itemFirst.getAmount();
                    int secondCount = itemSecond.getAmount();
                    int newSatckSize1 = firstCount + secondCount;
                    int newStackSize2 = 0;
                    if(newSatckSize1 > itemFirst.getStackAmount()){
                        newStackSize2 = newSatckSize1 - itemFirst.getStackAmount();
                        itemSecond.setAmount(newStackSize2);
                        newSatckSize1 = itemFirst.getAmount();
                    }
                    itemFirst.setAmount(newSatckSize1);
                    if(newStackSize2 == 0){
                        items.remove(itemSecond);
                    }
                }
            }
        }
    }

    @Override
    public boolean addItem(AbstractItem item) {
        for(AbstractItem currItem : items){
            if(item.getType() == currItem.getType() && currItem.getAmount() < currItem.getStackAmount()){
                if(currItem.getAmount() + item.getAmount() > currItem.getAmount()){
                    if(items.size() <= maxSize){
                        item.setAmount(currItem.getAmount() + item.getAmount() - currItem.getStackAmount());
                        items.add(item);
                    }
                    currItem.setAmount(currItem.getStackAmount());
                }
                else{
                    currItem.setAmount(currItem.getAmount() + item.getAmount());
                }
                return true;
            }
        }
        if(items.size() <= maxSize){
            items.add(item);
            return true;
        }
        return false;
    }

    @Override
    public AbstractItem dropItem(int index) {
        AbstractItem droppedItem = null;
        if(index - 1 < 10 && index - 1 > 0) {
            droppedItem = items.get(index-1);
            items.remove(index - 1);
        }
        return droppedItem;
    }

    @Override
    public boolean removeItem(ItemType type, int amount) {
        for(AbstractItem item : items){
            if(item.getType() == type){
                int currentAmount = item.getAmount();
                if(currentAmount - amount > 0) {
                    item.setAmount(currentAmount - amount);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean swapItems(int index1, int index2) {
        if(index1 -1 < 10 && index2  - 1 < 10 && index1 - 1 > 0 && index2 - 1 > 0 ){

        }
        return false;
    }

    @Override
    public boolean moveItem(int index, int newIndex) {
        return false;
    }

    @Override
    public boolean combineItems(int index1, int index2) {
        return false;
    }

    @Override
    public boolean equipItem(int index) {
        return false;
    }

    @Override
    public EquippableItem unequipItem() {
        return null;
    }

    @Override
    public ItemType cookItem(int index) {
        return null;
    }

    @Override
    public ItemType eatItem(int index) {
        return null;
    }

    @Override
    public int emptySlots() {
        return 0;
    }

    @Override
    public EquippableItem equippedItem() {
        return null;
    }

    @Override
    public AbstractItem getItem(int index) {
        return null;
    }
}
