package vendingmachine;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SnackMachine {
    private List<List<SnackSlot>> snackSlots; // Store all Snack Slots as 2D array (rows & columns)
    private MoneySlot[] moneySlots;
    private Keypad keypad = new Keypad();

    protected static final int ROWS_NUMBER = 5; // Number of rows in the vending machine
    protected static final int COLUMNS_NUMBER = 5; // Number of columns in the vending machine

    public SnackMachine() {
        this.snackSlots = new ArrayList<>(ROWS_NUMBER);
        for (int i = 0; i < ROWS_NUMBER; i++) {
            this.snackSlots.add(new ArrayList<>(COLUMNS_NUMBER));
        }

        // Setup all money slots
        moneySlots = new MoneySlot[] { new CardSlot("1234-1234-1234-1234", 0.0), new NoteSlot(0.0), new CoinSlot(0.0) };
    }

    public void setupSnacks(List<List<SnackSlot>> snacks) {
        this.snackSlots = snacks;
    }

    public String displayScreen() {
        StringBuilder grid = new StringBuilder();

        for (List<SnackSlot> row : snackSlots) {
            for (SnackSlot snackSlot : row) {
                // Check if there is a snack in the slot
                if (snackSlot.isAvailable()) {
                    grid.append("|\t"); // Add a tab for spacing
                    grid.append(snackSlot.getSnack().getName());
                } else {
                    // If no snack is available, we can append a placeholder, e.g., "Empty"
                    grid.append("Empty");
                }
                grid.append("\t\t|"); // Add a tab for spacing
            }
            grid.append("\n"); // Add a newline at the end of each row
        }

        return grid.toString();
    }

    /**
     * Selects a snack from the vending machine.
     *
     * @param number the selection number
     * @return a message indicating whether the snack is available and its price
     */
    public Snack selectSnack(int number) {
        // Check if selection is valid
        if (!isValidSelection(number)) {
            System.out.println("Invalid value!");
            return null;
        }

        keypad.setSelection(number);

        // Check if the selected snack is available
        Snack snack = isSnackAvailable(keypad.getSelection());

        if (snack == null) {
            System.out.println("The selected snack is not available.");
            return null;
        }
        System.out.println(snack.getName() + " is available. The price is " + snack.getPrice());
        return snack;
    }

    /**
     * Validates the selection made on the vending machine.
     * 
     * The first digit represents the row, and the second digit represents the
     * column.
     * The valid range for both row and column is from 1 to {@link #ROWS_NUMBER} and
     * 1 to {@link #COLUMNS_NUMBER} respectively.
     *
     * @param selection The selection made on the vending machine's keypad. Must be
     *                  a two-digit number.
     * @return true if the selection corresponds to a valid snack slot in the
     *         vending machine, otherwise false.
     */
    private boolean isValidSelection(int selection) {
        if (selection > 99 || selection < 9) {
            return false;
        }

        int row = selection / 10;
        int column = selection % 10;

        return (row >= 1 && row <= ROWS_NUMBER && column >= 1 && column <= COLUMNS_NUMBER);
    }

    private Snack isSnackAvailable(Map<String, Integer> selection) {
        if (selection == null) {
            return null;
        }

        SnackSlot snackSlot = getSnackSlotFromSelection(selection);

        if (snackSlot.isAvailable()) {
            Snack chosenSnack = snackSlot.getSnack();
            return chosenSnack;
        } else {
            return null;
        }
    }

    private SnackSlot getSnackSlotFromSelection(Map<String, Integer> selection) {
        int row = selection.get("row") - 1;
        int column = selection.get("column") - 1;
        return this.snackSlots.get(row).get(column);
    }

    /**
     * Calculates the total amount of money in the money slots.
     *
     * @return the total amount of money in the money slots
     */
    private double totalMoneyInserted() {
        double total = 0;
        for (MoneySlot slot : moneySlots) {
            total += slot.getAmount();
        }
        return total;
    }

    /**
     * Resets the money slots by setting the amount of money in each slot to zero.
     */
    private void resetMoneySlots() {
        for (MoneySlot slot : moneySlots) {
            slot.resetAmount();
        }
    }

    /**
     * Decrements the snack price from the total amount in the money slots after a
     * snack purchase
     * 
     * @param price The price of the snack that has been purchased.
     */
    private void decrementAmount(double price) {
        for (MoneySlot slot : moneySlots) {
            double amount = slot.getAmount();

            if (amount >= price) {
                slot.setAmount(amount - price);
                return;
            }

            price -= amount;
            slot.resetAmount(); // Set to zero
        }
    }

    public double insertCoin(double amount) {
        return this.insertMoney(moneySlots[2], amount);
    }

    public double insertNote(double amount) {
        return this.insertMoney(moneySlots[1], amount);
    }

    public double insertCard(double amount) {
        return this.insertMoney(moneySlots[0], amount);
    }

    private double insertMoney(MoneySlot moneySlot, double amount) {
        if (isSnackAvailable(keypad.getSelection()) == null) {
            System.out.println("Choose a snack first!");
            return 0.0;
        }
        if (moneySlot.acceptMoney(amount) == 0) {
            System.out.println("$" + amount + " is not valid amount of money");
            return 0.0;
        }
        System.out.println("Total money inserted: $" + totalMoneyInserted());
        return amount;
    }

    public Snack dispenseSnack() {
        if (keypad.getSelection() == null) {
            System.out.println("Choose a snack first!");
            return null;
        }

        SnackSlot selectedSnackSlot = getSnackSlotFromSelection(keypad.getSelection());

        // Check if the selected snack is available
        if (!selectedSnackSlot.isAvailable()) {
            System.out.println("Out of Stock!");
            return null;
        }

        // Check if the inserted money is enough
        Snack selectedSnack = selectedSnackSlot.getSnack();
        if (totalMoneyInserted() < selectedSnack.getPrice()) {
            System.out.println("The inserted money is not enough to buy this item.");
            return null;
        }

        selectedSnackSlot.decrementStock();
        System.out.println("Thank your for purchasing " + selectedSnack.getName());

        decrementAmount(selectedSnack.getPrice());
        keypad.setSelection(-1); // Reset keypad

        return selectedSnack;
    }

    public double dispenseChange() {
        // Calculate the change and return

        double change = totalMoneyInserted();
        if (change >= 0) {
            // Reset the accepted money in each slot to zero
            resetMoneySlots();
            System.out.println("Please take your change: " + change);
            printChange(new double[] { 50, 20, 1.0, 0.5, 0.2, 0.1 }, change);
            return change;
        } else {
            System.out.println("No change to dispense.");
            return 0;
        }
    }

    public static void printChange(double[] coins, double change) {
        int[] counts = new int[coins.length];

        for (int i = 0; i < coins.length; i++) {
            if (change >= coins[i]) {
                counts[i] = (int) (change / coins[i]);
                change = change - counts[i] * coins[i];
                change = Math.round(change * 100.0) / 100.0; // to avoid floating point errors
            }
        }

        for (int i = 0; i < coins.length; i++) {
            if (counts[i] > 0) {
                System.out.println("$" + coins[i] + " x " + counts[i]);
            }
        }
    }
}