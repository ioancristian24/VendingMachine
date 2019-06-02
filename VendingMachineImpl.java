import java.util.List;
import java.util.Scanner;

public class VendingMachineImpl implements VendingMachine {

    private Inventory<Coin> cashInventory = new Inventory<>();

    private Inventory<Item> itemInventory = new Inventory<>();

    private long totalSales;

    private Item currentItem;

    private long currentBalance;

    public VendingMachineImpl(Inventory<Coin> cashInventory, Inventory<Item> itemInventory) {
        this.cashInventory = cashInventory;
        this.itemInventory = itemInventory;

    }

    public VendingMachineImpl() {
    }

    public void initialise (){

        for (Coin c : Coin.values()){
            cashInventory.put(c, 5);
        }

        for (Item i : Item.values()){
            itemInventory.put(i,5);
        }
    }


    public void printStats(){
        System.out.println("Total Sales" + totalSales);
        System.out.println("Current Item Inventory" + itemInventory);
        System.out.println("Current Cash Inventory" + cashInventory.toString());

    }

    @Override
    public long selectItemAndGetPrice(Item item)throws SoldOutException {
        if (itemInventory.hasItem(item)) {
            currentItem = item;
            return currentItem.getPrice();
        }
        throw new SoldOutException("This product is sold out! Please, choose another product! ");
    }

    @Override
    public void insertCoin(Coin coin) {
        currentBalance = currentBalance + coin.getValue();
        cashInventory.add(coin);
    }

    @Override
    public List<Coin> refund() {
        return null;
    }

    @Override
    public PurchaseAndCoins<Item, List<Coin>> colletItemAndGetChange() {
        return null;
    }

    @Override
    public void reset() {

    }


}
