package vendingmachine;

public abstract class MoneySlot {
    protected double amount;

    public abstract boolean validate(double amount);

    public MoneySlot(double amount) {
        this.amount = amount;
    }

    public double acceptMoney(double insertedAmount) {
        if (validate(insertedAmount)) {
            amount += insertedAmount;
            return insertedAmount;
        }
        return 0.0;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void resetAmount() {
        amount = 0;
    }
}
