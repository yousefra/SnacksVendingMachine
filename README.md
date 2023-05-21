# Snacks Vending Machine

## UML Design

![Image](https://drive.google.com/uc?export=view&id=1GKqpRhZJw6Sh_pIjVnD8rAnpEe0vTB08)

## Code Skeleton

```java
public class SnackMachine {
    private List<List<SnackSlot>> snackSlots;
    private MoneySlot[] moneySlots;
    private Keypad keypad;
}

public class SnackSlot {
    private Queue<Snack> snacks;
}

public class Keypad {
    private int selection;
}

public abstract class MoneySlot {
    protected double amount;
}

public class CoinSlot extends MoneySlot { }

public class NoteSlot extends MoneySlot { }

public class CardSlot extends MoneySlot {
    private String cardNumber;
}
```
