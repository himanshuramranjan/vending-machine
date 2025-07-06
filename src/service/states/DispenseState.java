package service.states;

import enums.Note;
import models.VendingMachine;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class DispenseState implements VendingMachineState {

    private final VendingMachine vendingMachine;
    public DispenseState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }
    @Override
    public void selectProduct(List<Integer> selectedProducts) {
        System.out.println("Product already selected. Please collect the dispensed product.");
    }

    @Override
    public void insertNote(List<Note> notes) {
        System.out.println("Product already selected. Please collect the dispensed product.");
    }

    @Override
    public void dispenseProduct() {
        this.vendingMachine.getInventory().dispenseProducts(vendingMachine.getSelectedProducts());
        this.vendingMachine.resetVendingMachine();

        System.out.println("Please collect your product(s) from the bottom slot");
        this.vendingMachine.setState(new IdleState(vendingMachine));
    }

    @Override
    public Map<Note, Integer> returnChange() {
        return Collections.EMPTY_MAP;
    }
}
