package states;

import enums.Note;
import models.VendingMachine;

import java.util.List;

public class PaymentState implements VendingMachineState{

    private final VendingMachine vendingMachine;

    public PaymentState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void selectProduct(int productCode) throws Exception {
        System.out.println("Please make the payment for selected product");
    }

    @Override
    public void insertNote(List<Note> notes) throws Exception {
        vendingMachine.addNotes(notes);
        int totalAmount = 0;
        for(Note note : notes) totalAmount += note.getValue();
        boolean isPaymentSuccess = checkPaymentStatus(totalAmount);

        if(isPaymentSuccess) {
            vendingMachine.setTotalAmount(totalAmount);
            vendingMachine.setState(new DispenseState(vendingMachine));
        }
    }

    @Override
    public void dispenseProduct() {
        System.out.println("Please make the payment first");
    }

    @Override
    public void returnChange() {
        System.out.println("Please make the payment first");
    }

    private boolean checkPaymentStatus(int amount) throws Exception {
        int productCode = this.vendingMachine.getProductCode();
        return amount >= this.vendingMachine.getInventory().getProduct(productCode).getPrice();
    }
}
