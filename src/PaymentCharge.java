import java.util.regex.*;

public class PaymentCharge extends MonopolyCode {
    private int amount;

    public void doOperation(Player p) {
        // Operación de cobro
    }

    public PaymentCharge(String segment[], Terminal terminal) {
        super(Integer.parseInt(segment[0]), segment[2], terminal);

        Pattern pattern = Pattern.compile("(-?\\d+)(€)");
        Matcher matcher = pattern.matcher(segment[2]);

        String fNumber = matcher.group(1);

        if (matcher.find()) {
            this.amount = Integer.parseInt(fNumber);
        }
    }

    private String showSummary(Player player, int amount) {
        return "El jugador " + player.name + " ha pagado " + amount + "€";
    }

}
