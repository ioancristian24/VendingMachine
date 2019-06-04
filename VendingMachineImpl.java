import java.util.*;

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

    public void initialise() {

        for (Coin c : Coin.values()) {
            cashInventory.put(c, 5);
        }

        for (Item i : Item.values()) {
            itemInventory.put(i, 5);
        }
    }


    public void printStats() {
        System.out.println("Total Sales" + totalSales);
        System.out.println("Current Item Inventory" + itemInventory);
        System.out.println("Current Cash Inventory" + cashInventory.toString());

    }

    @Override
    public long selectItemAndGetPrice(Item item) throws SoldOutException {
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

    public void updateCashInventory(List<Coin> change) {
        for (Coin c : change) {
            cashInventory.decrease(c);
        }
    }

    public boolean isFullyPaid() {
        if (currentBalance >= currentItem.getPrice()) {
            return true;
        } else return false;
    }

    public List<Coin> getChange(long amount) throws NotSufficientChangeException {
        List<Coin> changes = Collections.EMPTY_LIST;
        long balance = amount;

        while (balance > 0) {
            if (balance >= Coin.FIFTY.getValue() && cashInventory.hasItem(Coin.FIFTY)) {
                changes.add(Coin.FIFTY);
                balance = balance - Coin.FIFTY.getValue();
                continue;

            } else if (balance >= Coin.TWENTY.getValue() && cashInventory.hasItem(Coin.TWENTY)) {
                changes.add(Coin.TWENTY);
                balance = balance - Coin.TWENTY.getValue();
                continue;

            } else if (balance >= Coin.TEN.getValue() && cashInventory.hasItem(Coin.TEN)) {
                changes.add(Coin.TEN);
                balance = balance - Coin.TEN.getValue();
                continue;

            } else if (balance >= Coin.FIVE.getValue() && cashInventory.hasItem(Coin.FIVE)) {
                changes.add(Coin.FIVE);
                balance = balance - Coin.FIVE.getValue();
                continue;
            } else {
                throw new NotSufficientChangeException(" Not sufficient change !");
            }
        }

        return changes;
    }

    boolean hasSufficientChangeForAmount(long amount) throws NotSufficientChangeException {
        try {
            if (currentBalance > currentItem.getPrice()) {
                getChange(amount);
            }
        } catch (NotSufficientChangeException e) {
            e.printStackTrace();
        }
        return true;
    }

    boolean hasSufficientChange() {
        if ((currentBalance - currentItem.getPrice()) > 0) {
            return true;
        } else return false;
    }

    public Item collectItem() throws NotSufficientChangeException, NotFullyPaidException {
        if (isFullyPaid() && hasSufficientChange()) {
            itemInventory.decrease(currentItem);
            throw new NotSufficientChangeException("We dont have enough change to gave you");
        } else if (currentBalance < currentItem.getPrice()) {
            throw new NotFullyPaidException("You did not paid enough for this product");
        }
        return currentItem;
    }

    public List<Coin> collectChange()throws NotSufficientChangeException{
        return null;
    }

    @Override
    public List<Coin> refund() {
        return null;
    }

    @Override
    public PurchaseAndCoins<Item, List<Coin>> collectItemAndGetChange() {
        return null;
    }

    @Override
    public void reset() {

    }


}
