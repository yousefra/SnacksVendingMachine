package test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import vendingmachine.CoinSlot;
import vendingmachine.Keypad;
import vendingmachine.NoteSlot;
import vendingmachine.Snack;
import vendingmachine.SnackMachine;
import vendingmachine.SnackSlot;

public class SnackMachineTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SnackMachine machine = new SnackMachine();
        machine.setupSnacks(getSnackSlotsData());

        boolean snackSelected = false;
        Snack selectedSnack = null;

        while (true) {
            System.out.println();
            System.out.println("1. Show all snacks");
            System.out.println("2. Select a snack");
            System.out.println("3. Insert coins");
            System.out.println("4. Insert note");
            System.out.println("5. Insert card");
            System.out.println("6. Purchase");
            System.out.println("7. Exit");

            System.out.print("Please select an option: ");
            int option = scanner.nextInt();

            System.out.println();

            switch (option) {
                case 1:
                    System.out.println(machine.displayScreen());
                    break;
                case 2:
                    System.out.print("Enter 2 digit number on keypad: ");
                    int enteredNumber = scanner.nextInt();
                    selectedSnack = machine.selectSnack(enteredNumber);
                    break;
                case 3:
                    System.out.print("Insert a coin (Accepted coins: $0.1, $0.2, $0.5, $1.0): ");
                    double enteredCoin = scanner.nextDouble();
                    machine.insertCoin(enteredCoin);
                    break;
                case 4:
                    System.out.print("Insert a note (Accepted notes: $20, $50): ");
                    double enteredNote = scanner.nextDouble();
                    machine.insertNote(enteredNote);
                    break;
                case 5:
                    System.out.println("Inserted a card");
                    double enteredCardAmount = scanner.nextDouble();
                    machine.insertCard(enteredCardAmount);
                    break;
                case 6:
                    Snack snack = machine.dispenseSnack();
                    double change = machine.dispenseChange();
                    break;
                case 7:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please select a number between 1 and 7.");
                    break;
            }
        }

        // System.out.println(machine.selectSnack(45));

        // machine.insertCoin(1.0);
        // machine.insertNote(2.0);
        // machine.insertNote(20.0);
        // machine.insertCard(15.5);

        // Snack snack = machine.dispenseSnack();
        // System.out.println(snack);
        // double change = machine.dispenseChange();
        // System.out.println(change);

        // Snack snack1 = machine.dispenseSnack();
        // System.out.println(snack1);
        // Snack snack2 = machine.dispenseSnack();
        // System.out.println(snack2);
        // Snack snack3 = machine.dispenseSnack();
        // System.out.println(snack3);
        // Snack snack4 = machine.dispenseSnack();
        // System.out.println(snack4);
        // Snack snack5 = machine.dispenseSnack();
        // System.out.println(snack5);

        // Test selecting a snack
        // assert machine.selectSnack(11) == null;

        // Test inserting money
        // assert machine.insertMoney(new CoinSlot(1.00));

        // // Test dispensing a snack
        // Snack snack = machine.dispenseSnack();
        // assert snack != null;

        // // Test dispensing change
        // double change = machine.dispenseChange();
        // assert change >= 0;

        // // Setup Machine
        // // new object
        // // setup columns and rows

        // // This use case begins when the customer wants to purchase snacks.
        // // The customer selects a number by pressing on the keypad.
        // String message = machine.select(1);

        // // The VM displays a message that the snack is available for the selected
        // number and displays its price.
        // System.out.println(message);

        // // The customer inserts the money.
        // // The VM validates the money.
        // // The VM accepts the money.
        // // The VM displays the accumulated amount of money each time a new money is
        // entered.
        // float total = machine.insertMoney(new CoinSlot(10));
        // System.out.println(total);

        // total = machine.insertMoney(new CoinSlot(10));
        // System.out.println(total);

        // total = machine.insertMoney(new CoinSlot(10));
        // System.out.println(total);

        // // The VM monitors the amount of the accepted money, If the money is enough,
        // the VM dispenses the selected snack to the customer.
        // Snack snack = machine.dispenseSnack();

        // // The VM determines if any change should be sent back to customer.
        // double change = machine.dispenseChange();

        // // The VM displays the change at panel.
        // // Then, the VM dispenses change.

    }

    /**
     * Fetch data from source, and create a 2D array of SnackSlots
     * For simplicity, data is entered manually
     * 
     * @return List<List<SnackSlot>>
     */
    private static List<List<SnackSlot>> getSnackSlotsData() {
        // Data: [SnackName, SnackPrice, SnackQuantity]
        String[][][] data = {
                { { "Snack11", "5.0", "5" }, { "Snack12", "7.5", "3" }, { "Snack13", "4.0", "5" },
                        { "Snack14", "2.5", "2" }, { "Snack15", "2.2", "5" } },
                { { "Snack21", "5.0", "5" }, { "Snack22", "7.5", "3" }, { "Snack23", "4.0", "5" },
                        { "Snack24", "2.5", "2" }, { "Snack25", "2.2", "5" } },
                { { "Snack31", "5.0", "5" }, { "Snack32", "7.5", "3" }, { "Snack33", "4.0", "5" },
                        { "Snack34", "2.5", "2" }, { "Snack35", "2.2", "5" } },
                { { "Snack41", "5.0", "5" }, { "Snack42", "7.5", "3" }, { "Snack43", "4.0", "5" },
                        { "Snack44", "2.5", "2" }, { "Snack45", "2.2", "5" } },
                { { "Snack51", "5.0", "5" }, { "Snack52", "7.5", "3" }, { "Snack53", "4.0", "5" },
                        { "Snack54", "2.5", "2" }, { "Snack55", "2.2", "5" } },
        };

        List<List<SnackSlot>> initialSnackSlots = new ArrayList<List<SnackSlot>>(data.length);
        for (int i = 0; i < data.length; i++) {
            List<SnackSlot> snackSlots = new ArrayList<SnackSlot>(data[i].length);
            for (int j = 0; j < data[i].length; j++) {
                snackSlots.add(new SnackSlot(data[i][j][0], Double.parseDouble(data[i][j][1]),
                        Integer.parseInt(data[i][j][2])));
            }
            initialSnackSlots.add(snackSlots);
        }

        return initialSnackSlots;
    }
}