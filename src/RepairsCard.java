package src;

import java.util.List;
import java.util.regex.*;

import utils.Constants;

public class RepairsCard extends MonopolyCode {
    private int amountForHouse;
    private int amountForHotel;
    private Terminal terminal;

    public RepairsCard() {
    }

    public RepairsCard(String[] parts, Terminal terminal) {
        super(parts[2], Integer.parseInt(parts[0]), terminal);

        Pattern pattern = Pattern.compile("(\\d+)(€)");
        Matcher matcher = pattern.matcher(parts[2]);

        if (matcher.find()) {
            amountForHouse = Integer.parseInt(matcher.group(1));
            amountForHotel = Integer.parseInt(matcher.group(1));
        }
    }

    public void doOperation(Player p) {
        int totalPrice;

        List<Property> properties = p.getProperties();
        int pBalance = p.getBalance();

        for (Property property : properties) {
            if (property instanceof Street) {
                Street street = (Street) property;
                int numHouses = street.getBuiltHouses();
                int numHotels = street.getBuiltHotels();

                totalPrice = numHouses * amountForHouse + numHotels * amountForHotel;
                pBalance -= totalPrice;

            }
        }

        p.setBalance(pBalance);
        terminal.show(showSummary(p, pBalance));

    }

    public String showSummary(Player player, int amount) {
        return String.format(Constants.showRepairSummary, player.getName(), amount);
    }

    public int getAmountForHouse() {
        return amountForHouse;
    }

    public void setAmountForHouse(int amountForHouse) {
        this.amountForHouse = amountForHouse;
    }

    public int getAmountForHotel() {
        return amountForHotel;
    }

    public void setAmountForHotel(int amountForHotel) {
        this.amountForHotel = amountForHotel;
    }

    public Terminal getTerminal() {
        return terminal;
    }

    public void setTerminal(Terminal terminal) {
        this.terminal = terminal;
    }

}