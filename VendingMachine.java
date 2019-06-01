import java.util.List;

public interface VendingMachine {

    long selectItemAndGetPrice(Item item);

    void insertCoin (Coin coin);

    List<Coin> refund();

    PurchaseAndCoins<Item, List<Coin>> colletItemAndGetChange();

    void reset();

}
