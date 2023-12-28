import java.util.regex.*;

public class PaymentCharge extends MonopolyCode {
    private int amount;

    public void doOperation(Player p) {
        // Operación de cobro
    }

    public PaymentCharge(String partes[], Terminal terminal) {
        super(Integer.parseInt(partes[0]), partes[2], terminal);

        Pattern pattern = Pattern.compile("(-?\\d+)(€)");
        Matcher matcher = pattern.matcher(segment[2]);

        String fNumber = matcher.group(1);

        if (matcher.find()) {
            this.amount = Integer.parseInt(fNumber);
        }
    }

    private String showSummary(Player player, int amount) {
        return "El jugador " + player.getName() + " ha pagado " + amount + "€";
    }
}
