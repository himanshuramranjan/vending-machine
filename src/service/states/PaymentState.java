package service.states;

import enums.Note;
import models.VendingMachine;

import java.util.*;

public class PaymentState implements VendingMachineState{

    private final VendingMachine vendingMachine;

    public PaymentState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void selectProduct(List<Integer> selectedProducts) {
        System.out.println("Please make the payment for selected product");
    }

    @Override
    public void insertNote(List<Note> notes) {
        int totalAmount = 0;
        for(Note note : notes) totalAmount += note.getValue();

        vendingMachine.setProvidedAmount(totalAmount);

        if(checkIfSufficientAmount(vendingMachine.getProvidedAmount())) {
            vendingMachine.getPaymentManager().receivePayment(notes);
            vendingMachine.setState(new CollectChangeState(vendingMachine));
        } else {
            System.out.println("Amount is not enough please insert remaining amount : " + (vendingMachine.getTotalBillAmount() - totalAmount));
            vendingMachine.setState(new PaymentState(vendingMachine));
        }
    }

    @Override
    public void dispenseProduct() {
        System.out.println("Please make the payment first");
    }

    @Override
    public Map<Note, Integer> returnChange() {
        System.out.println("Please make the payment first");
        return Collections.EMPTY_MAP;
    }

    private boolean checkIfSufficientAmount(int amount) {
        return amount >= this.vendingMachine.getTotalBillAmount();
    }
}
