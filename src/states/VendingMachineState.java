package states;

import enums.Note;
import models.Product;

import java.util.List;

public interface VendingMachineState {
    void selectProduct(int productCode) throws Exception;
    void insertNote(List<Note> notes) throws Exception;
    void dispenseProduct();
    void returnChange() throws Exception;
}
