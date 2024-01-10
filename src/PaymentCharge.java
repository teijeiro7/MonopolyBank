package src;

import java.util.regex.*;

public class PaymentCharge extends MonopolyCode {
    private int amount;

    public PaymentCharge() {
    }

    public PaymentCharge(String parts[], Terminal terminal) {
        super(parts[2], Integer.parseInt(parts[0]), terminal);

        Pattern pattern = Pattern.compile("(-?\\d+)(€)");
        Matcher matcher = pattern.matcher(parts[2]);

        if (matcher.find()) {
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

}
