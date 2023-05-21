package vendingmachine;

public class CoinSlot extends MoneySlot {
    public CoinSlot(double amount) {
        super(amount);
    }

    @Override
    public boolean validate(double amount) {
        return amount == 0.1 || amount == 0.2 || amount == 0.5 || amount == 1.0;
    }
}
