public class Service extends Property {
    private int[] costStaying;

    public Service(String[] partes, Terminal terminal) {
        super(partes[1], Integer.parseInt(partes[0]), terminal, Integer.parseInt(partes[3]), false,
                Integer.parseInt(partes[11]));
        this.costStaying = new int[4];
    }

    public void getPaymentForRent() {
        // Placeholder
    }

    public void doOperation(Player player) {
        // Placeholder
    }
}
