package src;

import utils.Constants;

public class Transport extends Property {
    private int[] costStaying;
    private Terminal terminal;
    private String transportName;

    public Transport() {
    }

    public Transport(String[] partes, Terminal terminal) {
        super(partes[1], (Integer.parseInt(partes[7]) * 2), terminal, Integer.parseInt(partes[3]), false,
                Integer.parseInt(partes[7]));
        this.costStaying = new int[4];
        this.terminal = terminal;
        this.transportName = partes[2];
    }

    public void getPaymentForRent() {
        // Placeholder
    }

    public void doOperation(Player player) {
        if (getOwner() == null) {
            transportNullOperations(player);
        } else if (player == getOwner()) {
            doTransportOwnerOperations(player);
        } else if (player != getOwner()) {

        }
    }

    public void transportNullOperations(Player player) {
        terminal.show(String.format(Constants.askForTransport, transportName, getPrice()));
        int transportOption = terminal.read();

        if (transportOption == 1) {
            terminal.show(String.format(Constants.confirmationBuyTransport, getPrice(), transportName));
            int pBalance = player.getBalance() - getPrice();
            player.setBalance(pBalance);
        }
    }

    public void doTransportOwnerOperations(Player player) {
        terminal.show(transportName);
    }

    public int[] getCostStaying() {
        return costStaying;
    }

    public void setCostStaying(int[] costStaying) {
        this.costStaying = costStaying;
    }

}
