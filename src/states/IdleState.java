package states;

import enums.Note;
import models.Product;
import models.VendingMachine;

public class IdleState implements VendingMachineState {

    private final VendingMachine vendingMachine;

    public IdleState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void selectProducts(int productCode) throws Exception {
        Product product = vendingMachine.getInventory().getProduct(productCode);
        vendingMachine.setState(new PaymentState(vendingMachine));
    }

    @Override
    public void insertNote(Note note) {
        System.out.println("Please select the product first");
    }

    @Override
    public void dispenseProduct() {
        System.out.println("Please select the product first");
    }

    @Override
    public void returnChange() {
        System.out.println("Please select the product first");
    }
}
