import enums.Note;
import exceptions.DuplicateShelfCodeException;
import models.Product;
import models.VendingMachine;

import java.util.List;

public class VendingMachineDemo {
    public static void main(String[] args) throws Exception {

        VendingMachine vendingMachine = VendingMachine.getInstance();

        // Add products to the inventory
        Product coke = new Product("Coke", 15);
        Product pepsi = new Product("Pepsi", 15);
        Product water = new Product("Water", 10);

        vendingMachine.getInventory().addProduct(coke, 5, 5);
        vendingMachine.getInventory().addProduct(pepsi, 3, 5);
        vendingMachine.getInventory().addProduct(water, 2, 5);

        // Select a product
        vendingMachine.getState().selectProduct(5);

        // Insert a note
        vendingMachine.getState().insertNote(List.of(Note.TEN, Note.TWENTY));

        // Dispense the product
        vendingMachine.getState().dispenseProduct();

        // Return change
        vendingMachine.getState().returnChange();

        // Select another product
        vendingMachine.getState().selectProduct(3);

        // Insert insufficient payment
        vendingMachine.getState().insertNote(List.of(Note.TEN));

        // Try to dispense the product
        vendingMachine.getState().dispenseProduct();

        // Add more money
        vendingMachine.getState().insertNote(List.of(Note.FIVE));

        // Dispense the product
        vendingMachine.getState().dispenseProduct();

        // Return change
        vendingMachine.getState().returnChange();
    }
}