import java.util.HashMap;
import java.util.Map;

public class Inventory <T> {

    private Map<T,Integer> inventory;

    public Inventory(){
        inventory = new HashMap<>();
    }

    //int getQuantity(T item) - returns quantity for the given item;

    public int getQuantity (T Item){
        if (!inventory.isEmpty()){
            System.out.println(" Stoc existent: " + inventory.get(getQuantity(Item)));
        }
        int quantity = 0;
        return quantity;
    }

    //public void add(T Item){};


    //public boolean hasItem(Item){};

    //public void decrease (T, item){};

    //public void clear (){};

    //public void put(T item, int quantity){};


}
