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
        this.terminal = terminal;
        this.color = Color.values()[id];
        terminal.show(String.format(Constants.enterNamePlayer, (id + 1)));
        String name = terminal.readString();
        this.name = name;
        this.balance = 1500;
        this.bankrupt = false;
        this.properties = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "------------" + "\nColor: " + color + "\nName: " + name + "\nBalance: " + balance
                + "\nBankrupt: " + bankrupt
                + "\nProperties: " + properties + "\n------------";
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
        if (mandatory) {
            if (balance >= amount) {
                terminal.show(Constants.whatAreYouGoingToPay + amount + " euros");
                balance -= amount;
            } else {
                sellActives(amount, mandatory);
                if (getBalance() < amount) {
                    setBankrupt(true);
                }
            }
        } else {
            terminal.show(String.format(Constants.askForPayment, amount));
            int response = terminal.read();
            if (response == 1) {
                if (balance >= amount) {
                    terminal.show(Constants.whatAreYouGoingToPay + amount + "€");
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

        if (mandatory) {
            while (this.balance < targetAmount) {
                terminal.show(Constants.showPropertiesToSell);
                for (Property property : getProperties()) {
                    terminal.show(property.toString()); // por cada propiedad del jugador la muestra
                }

                terminal.show(Constants.askPropertyToSell);
                int propertyIndex = terminal.read() - 1; // - 1 porque el array de propiedades empieza en 0

                while (propertyIndex < 0 || propertyIndex >= getProperties().size()) {
                    Property propertyToSell = properties.get(propertyIndex);
                    int sellPrice = propertyToSell.getPrice();
                    properties.remove(propertyIndex);
                    this.balance += sellPrice;
                    terminal.show(String.format(Constants.confirmSellActive, propertyToSell.toString(), sellPrice));
                }
            }
        } else {
            int sellMore;
            do {
                terminal.show(Constants.showPropertiesToSell);
                for (Property property : getProperties()) {
                    terminal.show(property.toString()); // por cada propiedad del jugador la muestra
                }

                terminal.show(Constants.askPropertyToSell);
                int propertyIndex = terminal.read() - 1; // - 1 porque el array de propiedades empieza en 0

                while (propertyIndex < 0 || propertyIndex >= getProperties().size()) {
                    Property propertyToSell = properties.get(propertyIndex);
                    String askToSell = String.format(Constants.askForSelling, propertyToSell.toString());
                    terminal.show(askToSell);
                    int confirmSell = terminal.read();

                    if (confirmSell == 1) {
                        int sellPrice = propertyToSell.getPrice();
                        properties.remove(propertyIndex);
                        this.balance += sellPrice;
                        String sellSummary = String.format(Constants.sellSummary, propertyToSell.toString(), sellPrice);
                        terminal.show(sellSummary);
                    }
                }
                terminal.show(Constants.askForMoreProperties);
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
