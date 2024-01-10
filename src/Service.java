package src;

import utils.Constants;

public class Service extends Property {
    private int costStaying;
    private String serviceName;
    private Terminal terminal;

    public Service() {
    }

    public Service(String[] partes, Terminal terminal) {
        super(partes[1], (Integer.parseInt(partes[0])), terminal, (Integer.parseInt(partes[5]) * 2), false,
                Integer.parseInt(partes[5]));
        this.serviceName = partes[2];
        this.terminal = terminal;
        this.costStaying = 0;
    }

    public void doOperation(Player player) {
        if (getOwner() == null) {
            serviceNullOperations(player);
        } else if (player == getOwner()) {
            doServiceOwnerOperations(player);
        } else if (player != getOwner()) {
            terminal.show(Constants.askForDiceNumber);
            int diceNumber = terminal.read();

            if (player.countServiceProperties() == 1) {
                costStaying = 4;
                terminal.show(String.format(Constants.payToOwner, player.getName(), getOwner().getName()));
                player.pay(costStaying * diceNumber, true);
                getOwner().receiveMoney(costStaying * diceNumber);
            } else if (player.countServiceProperties() == 2) {
                costStaying = 10;
                player.pay(costStaying * diceNumber, true);
                getOwner().receiveMoney(costStaying * diceNumber);
            }

        }
    }

    public void serviceNullOperations(Player player) {
        terminal.show(String.format(Constants.askForService, serviceName, getPrice()));
        int serviceOption = terminal.read();

        if (serviceOption == 1) {
            terminal.show(String.format(Constants.confirmationBuyService, getPrice(), serviceName));
            int pBalance = player.getBalance() - getPrice();
            player.setBalance(pBalance);
        }
    }

    public void doServiceOwnerOperations(Player player) {
        terminal.show(String.format(Constants.askForServiceMortgage, serviceName));
        int mortgageServiceOption = terminal.read();

        if (mortgageServiceOption == 1) {
            mortgageService(player);
        } else {

        }
    }

    public void mortgageService(Player player) {
        terminal.show(String.format(Constants.confirmationMortgageService, getMortgageValue(), serviceName));
        setMortgaged(true);
        int pBalance = player.getBalance() - getMortgageValue();
        player.setBalance(pBalance);
    }

    public int getCostStaying() {
        return costStaying;
    }

    public void setCostStaying(int costStaying) {
        this.costStaying = costStaying;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Terminal getTerminal() {
        return terminal;
    }

    public void setTerminal(Terminal terminal) {
        this.terminal = terminal;
    }

}
