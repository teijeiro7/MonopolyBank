package src;

import java.util.ArrayList;
import java.util.List;

import utils.Constants;

public class Player {
    private Color color;
    private String name;
    private int balance;
    private boolean bankrupt;
    private ArrayList<Property> properties;
    private static Terminal terminal;

    public Player(int id, Terminal terminal) {
        this.color = Color.values()[id];
        System.out.print("Enter the name of player " + (id + 1) + ": ");
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

    public String getName() {
        return name;
    }

    public void pay(int amount, boolean mandatory) {
        if (mandatory) {
            if (balance >= amount) {
                terminal.show(Constants.whatAreYouGoingToPay + amount + "€");
                balance -= amount;
            } else {
                sellActives(amount, mandatory);
            }
        } else {
            terminal.show("Do you want to make the payment of " + amount + "€? (yes/no)");
            String response = terminal.readString();
            if (response.equalsIgnoreCase("yes")) {
                if (balance >= amount) {
                    terminal.show(Constants.whatAreYouGoingToPay + amount + "€");
                    balance -= amount;
                } else {
                    sellActives(amount, mandatory);
                }
            }
        }
    }

    public void setBankrupt(boolean bankrupt, int balance) {
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
                    terminal.show("You have sold " + propertyToSell.toString() + " for " + sellPrice);
                }
            }
        } else {
            String sellMore;
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
                sellMore = terminal.readString();
            } while (sellMore.equalsIgnoreCase("yes") || sellMore.equalsIgnoreCase("YES"));
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

    public void setProperties(ArrayList<Property> properties) {
        this.properties = properties;
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
}
