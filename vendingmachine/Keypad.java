package vendingmachine;

import java.util.HashMap;
import java.util.Map;

public class Keypad {
    private int selection = -1;

    public Map<String, Integer> getSelection() {
        if (selection == -1)
            return null;

        Map<String, Integer> point = new HashMap<>();

        point.put("row", selection / 10);
        point.put("column", selection % 10);

        return point;
    }

    public void setSelection(int selection) {
        this.selection = selection;
    }
}
