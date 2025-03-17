package models;

import enums.Note;
import states.IdleState;
import states.VendingMachineState;

import java.util.ArrayList;
import java.util.List;

public class VendingMachine {

    public static volatile VendingMachine vendingMachine;

    private VendingMachineState state;
    private final Inventory inventory;
    private final List<Note> notes;

    private VendingMachine() {
        state = new IdleState();
        inventory = Inventory.getInstance();
        notes = new ArrayList<>();
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

    public List<Note> getNotes() {
        return notes;
    }

    public VendingMachineState getState() {
        return state;
    }

    public void setState(VendingMachineState state) {
        this.state = state;
    }
}
