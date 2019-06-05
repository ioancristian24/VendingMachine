import java.util.*;

public class VendingMachineImpl implements VendingMachine {

    private Inventory<Coin> cashInventory = new Inventory<>();

    private Inventory<Item> itemInventory = new Inventory<>();

    private long totalSales;

    private Item currentItem;

    private long currentBalance;



    public VendingMachineImpl() {
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

        boolean hasChange = true;

        try {
            if (currentBalance > currentItem.getPrice()) {
                getChange(amount);
            }
        } catch (NotSufficientChangeException e) {
            System.out.println("We dont have enough change to gave you");
            return false;
        }
        return hasChange;
    }

    boolean hasSufficientChange() {
        if ((currentBalance - currentItem.getPrice()) > 0) {
            return true;
        } else return false;
    }

    public Item collectItem() throws NotSufficientChangeException, NotFullyPaidException {
        if (isFullyPaid()) {
            if (hasSufficientChange()) {
                itemInventory.decrease(currentItem);
                return currentItem;
            } else {
                throw new NotSufficientChangeException("We dont have enough change to give you");
            }
        } else throw new NotFullyPaidException("You did not paid enough for this product");
    }

    public List<Coin> collectChange() throws NotSufficientChangeException {

        long changeAmount = currentBalance - currentItem.getPrice();

        List<Coin> change = getChange(changeAmount);

        updateCashInventory(change);

        currentBalance = 0;

        currentItem = null;

        return change;
    }

    @Override
    public List<Coin> refund() throws NotSufficientChangeException {

        List<Coin> refund = getChange(currentBalance);

        updateCashInventory(refund);

        currentBalance = 0;

        currentItem = null;

        return refund;
    }

    @Override
    public PurchaseAndCoins<Item, List<Coin>> collectItemAndGetChange() throws NotSufficientChangeException, NotFullyPaidException {

        totalSales = totalSales + currentItem.getPrice();

        Item item = collectItem();

        List<Coin> change = collectChange();

        return new PurchaseAndCoins<>(item, change);
    }

    @Override
    public void reset() {

        itemInventory.clear();

        cashInventory.clear();

        totalSales = 0;

        currentItem = null;

        currentBalance = 0;

    }

    public long getTotalSales(){
        return totalSales;
    }


}
