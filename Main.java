public class Main {

    public static void main(String[] args) throws SoldOutException {

        VendingMachineImpl vm = new VendingMachineImpl();


        vm.printStats();

        vm.selectItemAndGetPrice(Item.MARS);

        System.out.println(Item.MARS.getPrice());

    }
}
