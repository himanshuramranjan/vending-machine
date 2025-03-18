package states;

import enums.Note;
import models.VendingMachine;

import java.util.List;

public class CollectChangeState implements VendingMachineState {

    private final VendingMachine vendingMachine;

    public CollectChangeState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void selectProduct(int productCode) throws Exception {
        System.out.println("Please collect your change");
    }

    @Override
    public void insertNote(List<Note> notes) throws Exception {
        System.out.println("Please collect your change");
    }

    @Override
    public void dispenseProduct() {
        System.out.println("Please collect your change");
    }

    @Override
    public void returnChange() throws Exception {
        int productPrice = this.vendingMachine.getInventory().getProduct(vendingMachine.getProductCode()).getPrice();
        int extraAmount = this.vendingMachine.getTotalAmount() - productPrice;

        if(extraAmount > 0) {
            System.out.println("Please collect your change : " + extraAmount);
        } else {
            System.out.println("No change to return.");
        }
        this.vendingMachine.resetVendingMachine();
        this.vendingMachine.setState(new IdleState(vendingMachine));
    }
}
