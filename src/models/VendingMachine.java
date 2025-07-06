package models;

import service.PaymentManager;
import service.states.IdleState;
import service.states.VendingMachineState;

import java.util.ArrayList;
import java.util.List;

public class VendingMachine {

    public static volatile VendingMachine vendingMachine;

    private VendingMachineState state;
    private final Inventory inventory;
    private final PaymentManager paymentManager;
    private List<Integer> selectedProducts;
    private int totalBillAmount;
    private int providedAmount;

    private VendingMachine() {
        state = new IdleState(this);
        inventory = Inventory.getInstance();
        paymentManager = PaymentManager.getInstance();
        selectedProducts = new ArrayList<>();
        totalBillAmount = 0;
        providedAmount = 0;
    }

    public int getTotalBillAmount() {
        return totalBillAmount;
    }

    public void setTotalBillAmount(int totalBillAmount) {
        this.totalBillAmount = totalBillAmount;
    }

    public List<Integer> getSelectedProducts() {
        return selectedProducts;
    }

    public void setSelectedProducts(List<Integer> selectedProducts) throws Exception {
        this.selectedProducts = selectedProducts;
        calculateTotalAmount();
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

    public VendingMachineState getState() {
        return state;
    }

    public void setState(VendingMachineState state) {
        this.state = state;
    }

    public PaymentManager getPaymentManager() {
        return paymentManager;
    }

    public int getProvidedAmount() {
        return providedAmount;
    }

    public void setProvidedAmount(int providedAmount) {
        this.providedAmount += providedAmount;
    }

    public void resetVendingMachine() {
        this.selectedProducts = new ArrayList<>();
        this.totalBillAmount = 0;
        this.providedAmount = 0;
    }

    private void calculateTotalAmount() throws Exception {
        for(int product : selectedProducts) {
            totalBillAmount += inventory.getProduct(product).getPrice();
        }
    }
}
