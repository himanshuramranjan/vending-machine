package states;

import enums.Note;
import models.VendingMachine;

import java.util.List;

public class DispenseState implements VendingMachineState {

    private final VendingMachine vendingMachine;
    public DispenseState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }
    @Override
    public void selectProduct(int productCode) throws Exception {
        System.out.println("Product already selected. Please collect the dispensed product.");
    }

    @Override
    public void insertNote(List<Note> notes) {
        System.out.println("Product already selected. Please collect the dispensed product.");
    }

    @Override
    public void dispenseProduct() {
        System.out.println("Please collect your product from the bottom slot");
        int shelfCode = this.vendingMachine.getProductCode();
        this.vendingMachine.getInventory().dispenseProduct(shelfCode);
        this.vendingMachine.setState(new CollectChangeState(vendingMachine));
    }

    @Override
    public void returnChange() {

    }
}
