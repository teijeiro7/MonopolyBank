import java.util.regex.*;

public class RepairsCard extends MonopolyCode {
    private int amountForHouse;
    private int amountForHotel;

    public RepairsCard(String[] partes, Terminal terminal) {
        super(Integer.parseInt(partes[0]), partes[2], terminal);

        Pattern pattern = Pattern.compile("(\\d+)(€)");
        Matcher matcher = pattern.matcher(segment[2]);

        if (matcher.find()) {
            amountForHouse = Integer.parseInt(matcher.group(1));
        }
    }

    public void doOperation(Player p) {
        // Operación de reparación
    }

    public String showSummary(Player player, int amount) {
        return "El jugador " + player.name + " ha pagado " + amount + "€";
    }
}