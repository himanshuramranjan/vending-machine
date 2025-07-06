package service.states;

import enums.Note;

import java.util.List;
import java.util.Map;

public interface VendingMachineState {
    void selectProduct(List<Integer> selectedProducts);
    void insertNote(List<Note> notes);
    Map<Note, Integer> returnChange();
    void dispenseProduct();
}
