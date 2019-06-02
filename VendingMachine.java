import java.util.List;

public interface VendingMachine {

    long selectItemAndGetPrice(Item item) throws SoldOutException;

    void insertCoin (Coin coin);

    List<Coin> refund();

    PurchaseAndCoins<Item, List<Coin>> colletItemAndGetChange();

    void reset();

}
