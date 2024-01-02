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
        System.out.print("Introduzca el nombre del jugador " + (id + 1) + ": ");
        String name = terminal.readString();
        this.name = name;
        this.balance = 1500;
        this.bankrupt = false;
        this.properties = new ArrayList<>();
    }

    public String toString() {
        return "------------" + "\nColor: " + color + "\nNombre: " + name + "\nBalance: " + balance
                + "\nEn bancarrota: " + bankrupt
                + "\nPropiedades: " + properties + "\n------------";
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
                terminal.show("No tienes suficiente dinero para pagar, por tanto, tienes que vender propiedades");
                boolean bProperties = thereAreThingsToSell();
                if (bProperties) {
                    sellActives(false, amount, mandatory);
                } else {
                    terminal.show("No tienes propiedades para vender");
                    setBankrupt(bankrupt, amount);
                }

            }
        } else {
            if (this.balance >= amount) {
                this.balance -= amount;
            } else {
                terminal.show("No tienes suficiente dinero para pagar");
                terminal.show("¿Deseas vender alguna propiedad para poder pagar la siguiente?");
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
                terminal.show("Tus propiedades son: ");
                for (Property property : getProperties()) {
                    terminal.show(property.toString());
                }
                terminal.show("¿Qué propiedad quieres vender?");
                int propertyIndex = terminal.read() - 1;
                if (propertyIndex >= 0 && propertyIndex < properties.size()) {
                    Property propertyToSell = properties.remove(propertyIndex);
                    this.balance += propertyToSell.getPrice();
                    terminal.show("Has vendido " + propertyToSell.toString());
                } else {
                    terminal.show("Ese índice no es válido, inténtalo de nuevo");
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
        if (properties.size() > 0) {

        } else {
            Game.removePlayer(this);
        }
    }

    public boolean getBankrupt() {
        return bankrupt;
    }

    public void showResume() {

    }

    public void getPlayer(int id) {

    }

}
