package prog1.kotprog.dontstarve.solution.inventory.items;

/**
 * Egy általános itemet leíró osztály.
 */
public abstract class AbstractItem {
    /**
     * Az item típusa.
     * @see ItemType
     */
    private final ItemType type;

    /**
     * Az item mennyisége.
     */
    private int amount;

    protected int stackAmount;

    /**
     * Konstruktor, amellyel a tárgy létrehozható.
     * @param type az item típusa
     * @param amount az item mennyisége
     */
    public AbstractItem(ItemType type, int amount) {
        this.type = type;
        this.amount = amount;
    }

    /**
     * A type gettere.
     * @return a tárgy típusa
     */
    public ItemType getType() {
        return type;
    }

    public int getStackAmount() {return stackAmount;}


    /**
     * Az amount gettere.
     * @return a tárgy mennyisége
     */
    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
