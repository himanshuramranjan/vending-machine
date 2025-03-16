package states;

public interface VendingMachineStates {
    void selectProducts(Product product);
    void insertNote(Note note);

    void dispenseProduct();
    void returnChange();
}
