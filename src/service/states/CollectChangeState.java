package service.states;

import enums.Note;
import models.VendingMachine;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class CollectChangeState implements VendingMachineState {

    private final VendingMachine vendingMachine;

    public CollectChangeState(VendingMachine vendingMachine) {this.vendingMachine = vendingMachine; }

    @Override
    public void selectProduct(List<Integer> selectedProducts) {
        System.out.println("Product is already selected, payment is processing");
    }

    @Override
    public void insertNote(List<Note> notes) {
        System.out.println("Payment is done, and it is processing");
    }

    @Override
    public Map<Note, Integer> returnChange() {
        int changeAmount = vendingMachine.getProvidedAmount() - vendingMachine.getTotalBillAmount();
        boolean hasSufficientChangeAmount = vendingMachine.getPaymentManager().hasSufficientChange(changeAmount);

        if(changeAmount == 0 || hasSufficientChangeAmount) {
            System.out.println("Please collect your change : " + changeAmount);
            vendingMachine.setState(new DispenseState(vendingMachine));
        }
        else {
            System.out.println("Machine doesn't have sufficient change to return");
            changeAmount = vendingMachine.getProvidedAmount();
            vendingMachine.resetVendingMachine();
            vendingMachine.setState(new IdleState(vendingMachine));
        }
        return vendingMachine.getPaymentManager().returnChange(changeAmount);
    }

    @Override
    public void dispenseProduct() {
        System.out.println("Product is already selected, payment is processing");
    }
}
