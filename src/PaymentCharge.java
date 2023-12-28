import java.util.regex.*;

public class PaymentCharge extends MonopolyCode {
    private int amount;

    public void doOperation(Player p) { 
        int pBalance = p.getBalance();
        pBalance += amount; 
        showSummary(p, amount);
    }


    public PaymentCharge(String partes[], Terminal terminal) {
        // Llama al constructor de la clase padre con ciertos parámetros
        // partes[2] contiene la información relevante para el constructor padre
        super(partes[2], Integer.parseInt(partes[0]), terminal);

        // Patrón para buscar un número seguido por el símbolo '€' en partes[2]
        Pattern pattern = Pattern.compile("(-?\\d+)(€)");
        Matcher matcher = pattern.matcher(partes[2]);

        // Verifica si el matcher encuentra una coincidencia en partes[2] antes de
        // intentar obtener el grupo 1
        if (matcher.find()) {
            // Si hay una coincidencia, obtiene el grupo 1 del matcher, que es el número, y
            // lo asigna a la variable amount
            String fNumber = matcher.group(1);
            this.amount = Integer.parseInt(fNumber);
        }
    }

    private String showSummary(Player player, int amount) {
        return "El jugador " + player.getName() + " ha pagado " + amount + "€";
    }
}
