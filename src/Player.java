package src;

import java.util.ArrayList;
import java.util.List;

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
            this.balance -= amount;
            if (this.balance < amount) {
                terminal.show("You don't have enough money to pay, therefore, you have to sell properties");
                boolean bProperties = thereAreThingsToSell();
                if (bProperties) {
                    sellActives(false, amount, mandatory);
                } else {
                    terminal.show("You don't have properties to sell");
                    setBankrupt(bankrupt, amount);
                }
            }
        } else {
            if (this.balance >= amount) {
                this.balance -= amount;
            } else {
                terminal.show("You don't have enough money to pay");
                terminal.show("Do you want to sell any property to be able to pay the next one?");
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

    public void sellActives(boolean target, int targetAmount, boolean mandatory) {
        if (!target) {
            while (this.balance < targetAmount) {
                terminal.show("Your properties are: ");
                for (Property property : getProperties()) {
                    terminal.show(property.toString());
                }
                terminal.show("Which property do you want to sell?");
                int propertyIndex = terminal.read() - 1;
                if (propertyIndex >= 0 && propertyIndex < properties.size()) {
                    Property propertyToSell = properties.get(propertyIndex);
                    terminal.show("You are about to sell " + propertyToSell.toString() + " for "
                            + propertyToSell.getPrice() / 2 + ". Do you want to proceed? (y/n)");
                    String confirmation = terminal.readString();
                    if (confirmation.equalsIgnoreCase("y")) {
                        properties.remove(propertyIndex);
                        this.balance += propertyToSell.getPrice();
                        terminal.show("You have sold " + propertyToSell.toString());
                    } else {
                        terminal.show("Sale canceled.");
                    }
                } else {
                    terminal.show("That index is not valid, try again");
                }
            }
        }
    }

    public List<Property> getProperties() {
        return properties;
    }

    public List<Property> transferProperties() {
        List<Property> transferredProperties = new ArrayList<>(properties);
        properties.clear();
        return transferredProperties;
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
