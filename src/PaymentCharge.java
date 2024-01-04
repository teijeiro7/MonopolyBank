package src;

import java.util.regex.*;

public class PaymentCharge extends MonopolyCode {
    private int amount;

    public PaymentCharge(String parts[], Terminal terminal) {
        super(parts[2], Integer.parseInt(parts[0]), terminal); // Call the constructor of MonopolyCode

        // Pattern to find a number followed by the '€' symbol in parts[2]
        Pattern pattern = Pattern.compile("(-?\\d+)(€)");
        Matcher matcher = pattern.matcher(parts[2]);

        if (matcher.find()) { // If it finds something equal to what was mentioned before in parts[2], then
            // If there is a match, get group 1 from the matcher, which is the number, and
            // assign it to the variable amount
            String foundNumber = matcher.group(1);
            this.amount = Integer.parseInt(foundNumber);
        }
    }

    public void doOperation(Player p) {
        int pBalance = p.getBalance();
        pBalance += amount;
        showSummary(p, amount);
        p.setBalance(pBalance);
    }

    private String showSummary(Player player, int amount) {
        if (amount < 0) {
            return "Player " + player.getName() + " has paid " + amount + "€";
        } else {
            return "Player" + player.getName() + " has earned " + amount + "€";
        }
    }
}
