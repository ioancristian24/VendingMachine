import java.util.HashMap;
import java.util.Map;

public class Inventory<T> {

    private Map<T, Integer> inventory;

    public Inventory() {
        inventory = new HashMap<>();
    }

    //int getQuantity(T item) - returns quantity for the given item;

    public int getQuantity(T Item) {
        int quantity = getQuantity(Item);
        if (!inventory.isEmpty()) {
            System.out.println(" Stoc existent: " + inventory.get(quantity));
        }
        return quantity;
    }

    public void add(T Item) {
        int count = inventory.get(Item);
        inventory.put(Item, count + 1);
    }


    public boolean hasItem(T Item) {
        if (getQuantity(Item) >0 ) return true;
        else return false;
    }

    public void decrease (T Item){
        if (hasItem(Item)){
            int count = inventory.get(Item);
            inventory.put(Item, count - 1);
        }

    }

    public void clear () {
        inventory.clear();
        System.out.println("Finally the inventory look like this: " + inventory);
    }

    public void put(T item, int quantity){
        inventory.put(item, quantity);
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "inventory=" + inventory +
                '}';
    }
}