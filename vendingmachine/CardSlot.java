package vendingmachine;

public class CardSlot extends MoneySlot {
    private String cardNumber;

    public CardSlot(String cardNumber, double amount) {
        super(amount);

        this.cardNumber = cardNumber;
    }

    @Override
    public boolean validate(double amount) {
        // Card validation logic would go here, for simplicity we assume all cards are
        // valid
        return true;
    }
}
