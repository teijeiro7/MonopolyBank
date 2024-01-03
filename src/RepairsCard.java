package src;

import java.util.regex.*;

public class RepairsCard extends MonopolyCode {
    private int amountForHouse;
    private int amountForHotel;

    public RepairsCard(String[] partes, Terminal terminal) {
        super(partes[1], Integer.parseInt(partes[0]), terminal);

        Pattern pattern = Pattern.compile("(\\d+)(€)");
        Matcher matcher = pattern.matcher(partes[2]);

        if (matcher.find()) {
            amountForHouse = Integer.parseInt(matcher.group(1));
        }
    }

    public void doOperation(Player p) {
        // Operación de reparación
    }

    public String showSummary(Player player, int amount) {
        return "El jugador " + player.getName() + " ha pagado " + amount + "€";
    }
}