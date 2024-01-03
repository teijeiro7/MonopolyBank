package src;

public class Service extends Property {
    private int[] costStaying;

    public Service(String[] partes, Terminal terminal) {
        super(partes[1], (Integer.parseInt(partes[5]) * 2), terminal, Integer.parseInt(partes[3]), false,
                Integer.parseInt(partes[5]));
        this.costStaying = new int[4];
    }

    public void getPaymentForRent() {
        // Placeholder
    }

    public void doOperation(Player player) {
        // Placeholder
    }
}
