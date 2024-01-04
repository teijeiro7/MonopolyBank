package src;

import java.util.List;
import java.util.regex.*;

public class RepairsCard extends MonopolyCode {
    private int amountForHouse;
    private int amountForHotel;
    private Terminal terminal;

    public RepairsCard(String[] parts, Terminal terminal) {
        super(parts[1], Integer.parseInt(parts[0]), terminal);

        Pattern pattern = Pattern.compile("(\\d+)(€)");
        Matcher matcher = pattern.matcher(parts[2]);

        if (matcher.find()) {
            amountForHouse = Integer.parseInt(matcher.group(1));
            amountForHotel = Integer.parseInt(matcher.group(2));
        }
    }

    public void doOperation(Player p, String parts[]) {
        List<Property> properties = p.getProperties();
        int pBalance = p.getBalance();

        for (Property property : properties) {
            if (property instanceof Street) {
                Street street = (Street) property;
                int numHouses = street.getBuiltHouses();
                int numHotels = street.getBuiltHotels();

                int totalPrice = numHouses * amountForHouse + numHotels * amountForHotel;
                pBalance -= totalPrice;

            }
        }

        p.setBalance(pBalance);

    }

    public String showSummary(Player player, int amount) {
        return "El jugador " + player.getName() + " ha pagado " + amount + "€";
    }
}