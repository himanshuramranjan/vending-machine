package states;

import enums.Note;
import models.Product;

public interface VendingMachineState {
    void selectProducts(Product product);
    void insertNote(Note note);
    void dispenseProduct();
    void returnChange();
}
