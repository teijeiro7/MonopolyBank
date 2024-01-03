package src;

public class Transport extends Property {
    private int[] costStaying;

    public Transport(String[] partes, Terminal terminal) {
        super(partes[1], (Integer.parseInt(partes[7]) * 2), terminal, Integer.parseInt(partes[3]), false,
                Integer.parseInt(partes[7]));
        this.costStaying = new int[4];
    }

    public void getPaymentForRent() {
        // Placeholder
    }

    public void doOperation(Player player) {
        // Placeholder
    }

}
