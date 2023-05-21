package vendingmachine;

public class NoteSlot extends MoneySlot {
    public NoteSlot(double amount) {
        super(amount);
    }

    @Override
    public boolean validate(double amount) {
        return amount == 20.0 || amount == 50.0;
    }
}
