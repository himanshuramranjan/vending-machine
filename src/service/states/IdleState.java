package service.states;

import enums.Note;
import models.VendingMachine;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class IdleState implements VendingMachineState {

    private final VendingMachine vendingMachine;

    public IdleState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void selectProduct(List<Integer> selectedProducts) {
        try {
            vendingMachine.setSelectedProducts(selectedProducts);
            vendingMachine.setState(new PaymentState(vendingMachine));
        } catch (Exception e) {
            System.out.println("Exception occured : " + e.getMessage());
            vendingMachine.resetVendingMachine();
            vendingMachine.setState(new IdleState(vendingMachine));
        }
    }

    @Override
    public void insertNote(List<Note> notes) {
        System.out.println("Please select the product first");
    }

    @Override
    public void dispenseProduct() {
        System.out.println("Please select the product first");
    }

    @Override
    public Map<Note, Integer> returnChange() {
        System.out.println("Please select the product first");
        return Collections.EMPTY_MAP;
    }
}
