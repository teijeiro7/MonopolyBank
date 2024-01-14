package src;

import java.util.regex.*;

import utils.Constants;

public class PaymentCharge extends MonopolyCode {
    private int amount;
    private Terminal terminal;

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

        this.terminal = terminal;
    }

    public void doOperation(Player p) {
        int pBalance = p.getBalance();
        pBalance += amount;
        terminal.show(showSummary(p, amount));
        p.setBalance(pBalance);
    }

    private String showSummary(Player player, int amount) {
        TranslatorManager translatorManager = terminal.getTranslatorManager();
        Translator translator = translatorManager.getCurrentIdiom();

        if (amount < 0) {
            String translatedShowSummaryPay = translator.translate(Constants.showSummaryPay);
            return String.format(translatedShowSummaryPay, player.getName(), amount);
        } else {
            String translatedShowSummaryEarn = translator.translate(Constants.showSummaryEarn);
            return String.format(translatedShowSummaryEarn, player.getName(), amount);
        }
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

}
