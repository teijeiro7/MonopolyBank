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
        for (int i = 0; i < 4; i++) {
            this.costStaying[i] = Integer.parseInt(partes[i + 3]);
        }
    }

    public void doOperation(Player player) {
        TranslatorManager translatorManager = terminal.getTranslatorManager();
        Translator translator = translatorManager.getCurrentIdiom();

        if (getOwner() == null) {
            transportNullOperations(player);
        } else if (player == getOwner()) {
            doTransportOwnerOperations(player);
        } else if (player != getOwner()) {
            String translatedPayToOwner = translator.translate(Constants.payToOwner);
            terminal.show(String.format(translatedPayToOwner, player.getName(), getOwner().getName()));
            player.pay(costStaying[player.countTransportProperties()], true);
            getOwner().receiveMoney(costStaying[player.countTransportProperties()]);
        }
    }

    public void transportNullOperations(Player player) {
        TranslatorManager translatorManager = terminal.getTranslatorManager();
        Translator translator = translatorManager.getCurrentIdiom();

        String translatedAskForTransport = translator.translate(Constants.askForTransport);
        terminal.show(String.format(translatedAskForTransport, transportName, getPrice()));
        int transportOption = terminal.read();

        if (transportOption == 1) {
            String translatedConfirmBuyTransport = translator.translate(Constants.confirmationBuyTransport);
            terminal.show(String.format(translatedConfirmBuyTransport, getPrice(), transportName));
            int pBalance = player.getBalance() - getPrice();
            player.setBalance(pBalance);
        }
    }

    public void doTransportOwnerOperations(Player player) {
        TranslatorManager translatorManager = terminal.getTranslatorManager();
        Translator translator = translatorManager.getCurrentIdiom();

        String translatedAskForTransportMortgage = translator.translate(Constants.askForTransportMortgage);
        terminal.show(String.format(translatedAskForTransportMortgage, transportName));
        int mortgageServiceOption = terminal.read();

        if (mortgageServiceOption == 1) {
            mortgageTransport(player);
        } else {

        }
    }

    public void mortgageTransport(Player player) {
        terminal.show(String.format(Constants.confirmationMortgageTransport, getMortgageValue(), transportName));
        setMortgaged(true);
        int pBalance = player.getBalance() - getMortgageValue();
        player.setBalance(pBalance);
    }

    public int[] getCostStaying() {
        return costStaying;
    }

    public void setCostStaying(int[] costStaying) {
        this.costStaying = costStaying;
    }

}
