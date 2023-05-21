package test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import vendingmachine.Snack;
import vendingmachine.SnackMachine;
import vendingmachine.SnackSlot;

public class SnackMachineTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SnackMachine machine = new SnackMachine();
        machine.setupSnacks(getSnackSlotsData());

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