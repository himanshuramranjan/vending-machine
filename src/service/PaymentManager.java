package service;

import enums.Note;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class PaymentManager {

    private final Map<Note, Integer> cashBox;
    private int totalAmount;

    private PaymentManager() {
        cashBox = new ConcurrentHashMap<>();
        totalAmount = 0;
    }

    private static class PaymentManagerHelper {
        private static final PaymentManager INSTANCE = new PaymentManager();
    }


    public static PaymentManager getInstance() {
        return PaymentManagerHelper.INSTANCE;
    }

    public void receivePayment(List<Note> insertedNotes) {
        for(Note note : insertedNotes) {
            cashBox.put(note, cashBox.getOrDefault(note, 0) + 1);
        }
    }

    public void addNotes(Map<Note, Integer> insertedNotes) {
        for (Note note : insertedNotes.keySet()) {
            cashBox.put(note, cashBox.getOrDefault(note, 0) + insertedNotes.get(note));
        }
    }

    public int getTotalAmount() {
        for (Note note : cashBox.keySet()) {
            totalAmount += cashBox.get(note) * note.getValue();
        }
        return totalAmount;
    }

    public boolean hasSufficientChange(int amountNeeded) {
        try {
            return returnChange(amountNeeded) != null;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public Map<Note, Integer> returnChange(int amount) {
        List<Note> denominations = new ArrayList<>(cashBox.keySet());
        denominations.sort((a, b) -> Integer.compare(b.getValue(), a.getValue()));

        Map<Note, Integer> result = new HashMap<>();
        int remaining = amount;

        for (Note note : denominations) {
            int available = cashBox.getOrDefault(note, 0);
            int count = Math.min(available, remaining / note.getValue());
            if (count > 0) {
                result.put(note, count);
                remaining -= count * note.getValue();
            }
        }

        if (remaining > 0) {
            throw new IllegalArgumentException("Insufficient change available in the machine");
        }

        // Deduct notes from cashBox
        for (Map.Entry<Note, Integer> entry : result.entrySet()) {
            cashBox.put(entry.getKey(), cashBox.get(entry.getKey()) - entry.getValue());
        }

        return result;
    }

    public void reset() {
        cashBox.clear();
    }

    public Map<Note, Integer> getCashBox() {
        return Collections.unmodifiableMap(cashBox);
    }
}
