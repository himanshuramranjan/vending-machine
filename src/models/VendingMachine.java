package models;

import enums.Note;
import states.IdleState;
import states.VendingMachineState;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VendingMachine {

    public static volatile VendingMachine vendingMachine;

    private VendingMachineState state;
    private final Inventory inventory;
    private final Map<Note, Integer> notes;
    private int productCode;

    private int totalAmount;

    private VendingMachine() {
        state = new IdleState(this);
        inventory = Inventory.getInstance();
        notes = new HashMap<>();
        productCode = -1;
        totalAmount = 0;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getProductCode() {
        return productCode;
    }

    public void setProductCode(int productCode) {
        this.productCode = productCode;
    }

    public static VendingMachine getInstance() {
        if(vendingMachine == null) {
            synchronized (VendingMachine.class) {
                if(vendingMachine == null) {
                    vendingMachine = new VendingMachine();
                }
            }
        }
        return vendingMachine;
    }

    public static VendingMachine getVendingMachine() {
        return vendingMachine;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public Map<Note, Integer> getNotes() {
        return notes;
    }

    public void addNotes(List<Note> notes) {
        for(Note note : notes) {
            this.notes.put(note, this.notes.getOrDefault(note, 0) + 1);
        }
    }

    public VendingMachineState getState() {
        return state;
    }

    public void setState(VendingMachineState state) {
        this.state = state;
    }

    public void resetVendingMachine() {
        this.productCode = -1;
        this.totalAmount = 0;
    }
}
