package src;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import utils.Constants;

public class Player implements Serializable {
    private Color color;
    private String name;
    private int balance;
    private boolean bankrupt;
    private ArrayList<Property> properties;
    private Terminal terminal;

    public Player() {
    }

    public Player(int id, Terminal terminal) {
        TranslatorManager translatorManager = terminal.getTranslatorManager();
        Translator translator = translatorManager.getCurrentIdiom();

        this.terminal = terminal;
        this.color = Color.values()[id];

        String translatedEnterNamePlayer = translator.translate(Constants.enterNamePlayer);
        terminal.show(String.format(translatedEnterNamePlayer, (id + 1)));
        String name = terminal.readString();

        this.name = name;
        this.balance = 1500;
        this.bankrupt = false;
        this.properties = new ArrayList<>();
    }

    @Override
    public String toString() {
        TranslatorManager translatorManager = terminal.getTranslatorManager();
        Translator translator = translatorManager.getCurrentIdiom();

        String translatedColor = translator.translate("Color");
        String translatedName = translator.translate("Name");
        String translatedBalance = translator.translate("Balance");
        String translatedBankrupt = translator.translate("Bankrupt");
        String translatedProperties = translator.translate("Properties");

        return ("------------" + "\n" + translatedColor + ": " + color + "\n"
                + translatedName + ": " + name + "\n" + translatedBalance
                + ": " + balance + "\n" + translatedBankrupt + ": " + bankrupt + "\n"
                + translatedProperties + ": " + properties + "\n------------");
    }

    public int getBalance() {
        return balance; // Placeholder
    }

    public int countTransportProperties() {
        int count = 0;
        for (Property property : properties) {
            if ("Transport".equals(property.getDescription())) {
                count++;
            }
        }
        return count;
    }

    public int countServiceProperties() {
        int count = 0;
        for (Property property : properties) {
            if (property instanceof Service) {
                count++;
            }
        }
        return count;
    }

    public String getName() {
        return name;
    }

    public void pay(int amount, boolean mandatory) {
        TranslatorManager translatorManager = terminal.getTranslatorManager();
        Translator translator = translatorManager.getCurrentIdiom();

        if (mandatory) {
            if (balance >= amount) {
                String translatedWhatAreYouGoingToPay = translator.translate(Constants.whatAreYouGoingToPay);
                terminal.show(String.format(translatedWhatAreYouGoingToPay, amount));
                balance -= amount;
            } else {
                sellActives(amount, mandatory);
                if (getBalance() < amount) {
                    setBankrupt(true);
                }
            }
        } else {
            String translatedAskForPayment = translator.translate(Constants.askForPayment);
            terminal.show(String.format(translatedAskForPayment, amount));
            int response = terminal.read();
            if (response == 1) {
                if (balance >= amount) {
                    String translatedWhatAreYouGoingToPay = translator.translate(Constants.whatAreYouGoingToPay);
                    terminal.show(String.format(translatedWhatAreYouGoingToPay, amount));
                    balance -= amount;
                } else {
                    sellActives(amount, mandatory);
                }
            }
        }
    }

    public void receiveMoney(int amount) {
        this.balance += amount;
    }

    public void setBankrupt(boolean bankrupt) {
        if (balance <= 0) {
            this.bankrupt = true;
        }

        if (bankrupt) {
            Game.removePlayer(this);
        }
    }

    public void sellActives(int targetAmount, boolean mandatory) {
        TranslatorManager translatorManager = terminal.getTranslatorManager();
        Translator translator = translatorManager.getCurrentIdiom();

        if (mandatory) {
            while (this.balance < targetAmount) {
                String translatedShowPropertiesToSell = translator.translate(Constants.showPropertiesToSell);
                terminal.show(translatedShowPropertiesToSell);
                for (Property property : getProperties()) {
                    terminal.show(property.toString()); // por cada propiedad del jugador la muestra
                }

                String translatedAskPropertyToSell = translator.translate(Constants.askPropertyToSell);
                terminal.show(translatedAskPropertyToSell);
                int propertyIndex = terminal.read() - 1; // - 1 porque el array de propiedades empieza en 0

                while (propertyIndex < 0 || propertyIndex >= getProperties().size()) {
                    Property propertyToSell = properties.get(propertyIndex);
                    int sellPrice = propertyToSell.getPrice();
                    properties.remove(propertyIndex);
                    this.balance += sellPrice;

                    String translatedConfirmSellActive = translator.translate(Constants.confirmSellActive);
                    terminal.show(String.format(translatedConfirmSellActive, propertyToSell.toString(), sellPrice));
                }
            }
        } else {
            int sellMore;

            do {
                String translatedShowPropertiesToSell = translator.translate(Constants.showPropertiesToSell);
                terminal.show(translatedShowPropertiesToSell);
                for (Property property : getProperties()) {
                    terminal.show(property.toString()); // por cada propiedad del jugador la muestra
                }

                terminal.show(Constants.askPropertyToSell);
                int propertyIndex = terminal.read() - 1; // - 1 porque el array de propiedades empieza en 0

                while (propertyIndex < 0 || propertyIndex >= getProperties().size()) {
                    Property propertyToSell = properties.get(propertyIndex);

                    String translatedAskToSell = translator.translate(Constants.askForSelling);
                    String askToSell = String.format(translatedAskToSell, propertyToSell.toString());
                    terminal.show(askToSell);
                    int confirmSell = terminal.read();

                    if (confirmSell == 1) {
                        int sellPrice = propertyToSell.getPrice();
                        properties.remove(propertyIndex);
                        this.balance += sellPrice;

                        String translatedConfirmSellActive = translator.translate(Constants.sellSummary);
                        String sellSummary = String.format(
                                translatedConfirmSellActive, propertyToSell.toString(), sellPrice);
                        terminal.show(sellSummary);
                    }
                }

                String translatedAskForMoreProperties = translator.translate(Constants.askForMoreProperties);
                terminal.show(translatedAskForMoreProperties);
                sellMore = terminal.read();
            } while (sellMore == 1);
        }
    }

    public List<Property> getProperties() {
        return properties;
    }

    public void traspaseProperties(Player newOwner) {
        for (Property property : properties) { // traspasamos las propiedades y las deshipotecamos para el banco
            property.setOwner(newOwner);
            if (newOwner == null) {
                property.setMortgaged(false);
            }
            if (property instanceof Street) { // por si acaso quitamos las casas
                Street street = (Street) property;
                street.setBuiltHouses(0);
            }
        }
        newOwner.getProperties().addAll(properties);
    }

    public void setProperties(Property property) {
        properties.add(property);
    }

    public boolean thereAreThingsToSell() {
        return properties.size() > 0;
    }

    public boolean getBankrupt() {
        return bankrupt;
    }

    public void showResume() {
        // Placeholder
    }

    public void getPlayer(int id) {
        // Placeholder
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProperties(ArrayList<Property> properties) {
        this.properties = properties;
    }

    public Terminal getTerminal() {
        return terminal;
    }

    public void setTerminal(Terminal terminal) {
        this.terminal = terminal;
    }

}
