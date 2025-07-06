import enums.Note;
import models.Product;
import models.VendingMachine;
import service.PaymentManager;

import java.util.List;
import java.util.Map;

public class VendingMachineDemo {
    public static void main(String[] args) throws Exception {

        VendingMachine vendingMachine = VendingMachine.getInstance();
        PaymentManager paymentManager = PaymentManager.getInstance();

        // Add cash to Vending Machine
        paymentManager.addNotes(Map.of(
                Note.ONE, 20,
                Note.FIVE, 20,
                Note.TEN, 20,
                Note.FIFTY, 20,
                Note.HUNDRED, 20));

        // Add products to the inventory
        Product coke = new Product("Coke", 15);
        Product pepsi = new Product("Pepsi", 15);
        Product water = new Product("Water", 10);

        vendingMachine.getInventory().addProduct(coke, 5, 5);
        vendingMachine.getInventory().addProduct(pepsi, 3, 5);
        vendingMachine.getInventory().addProduct(water, 2, 5);

        // Select a product
        vendingMachine.getState().selectProduct(List.of(5, 2));

        // Insert a note
        vendingMachine.getState().insertNote(List.of(Note.FIFTY, Note.TWENTY));

        // Return change
        vendingMachine.getState().returnChange();

        // Dispense the product
        vendingMachine.getState().dispenseProduct();

        // Select another product
        vendingMachine.getState().selectProduct(List.of(3));

        // Insert insufficient payment
        vendingMachine.getState().insertNote(List.of(Note.TEN));

        // Try to dispense the product
        vendingMachine.getState().dispenseProduct();

        // Add more money
        vendingMachine.getState().insertNote(List.of(Note.FIVE));

        // Return change
        vendingMachine.getState().returnChange();

        // Dispense the product
        vendingMachine.getState().dispenseProduct();
    }
}