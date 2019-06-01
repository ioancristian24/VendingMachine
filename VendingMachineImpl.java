import java.util.List;

public class VendingMachineImpl implements VendingMachine {

    private Inventory<Coin> cashInventory;

    private Inventory<Item> itemInventory;

    private long totalSales;

    private Item currentItem;

    private long currentBalance;

    public VendingMachineImpl(Inventory<Coin> cashInventory, Inventory<Item> itemInventory, long totalSales, Item currentItem, long currentBalance) {
        this.cashInventory = cashInventory;
        this.itemInventory = itemInventory;
        this.totalSales = totalSales;
        this.currentItem = currentItem;
        this.currentBalance = currentBalance;
    }

    public void initialise (){
        
    }

    @Override
    public long selectItemAndGetPrice(Item item) {
        return 0;
    }

    @Override
    public void insertCoin(Coin coin) {

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
