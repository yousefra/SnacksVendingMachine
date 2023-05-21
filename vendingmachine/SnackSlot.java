package vendingmachine;

import java.util.LinkedList;
import java.util.Queue;

public class SnackSlot {
    private Queue<Snack> snacks;
    protected static final int SLOT_SIZE = 5; // Available number of snacks in the snack slot

    public SnackSlot() {
    }

    public SnackSlot(String snackName, double snackPrice, int quantity) {
        if (quantity > SLOT_SIZE) {
            throw new IllegalArgumentException("Snack Slot accepts a maximum of " + SLOT_SIZE + " snacks");
        }
        this.snacks = new LinkedList<>();
        for (int i = 0; i < quantity; i++) {
            snacks.offer(new Snack(snackName, snackPrice));
        }
    }

    private boolean validateQuantity() {
        return snacks.size() < SLOT_SIZE;
    }

    public boolean addSnack(Snack snack) {
        if (!validateQuantity()) {
            return false;
        }
        snacks.offer(snack);
        return true;
    }

    public boolean isAvailable() {
        return !snacks.isEmpty();
    }

    public Snack getSnack() {
        if (snacks.isEmpty()) {
            throw new IllegalArgumentException("Out of stock.");
        }
        return snacks.peek();
    }

    public void decrementStock() {
        if (!snacks.isEmpty()) {
            snacks.poll();
        }
    }
}
