package states;

import enums.Note;
import models.Product;

public interface VendingMachineState {
    void selectProducts(int productCode);
    void insertNote(Note note);
    void dispenseProduct();
    void returnChange();
}
