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

    public void pay(int amount, boolean mandatory) {
        // Realizar un pago
    }

    public void setBankrupt(boolean bankrupt, int balance) {
        if (balance <= 0) {
            this.bankrupt = true;
        }

        if (bankrupt) {
            this.color = null;
            this.name = null;
            this.balance = 0;
            this.properties = null;
        }
    }

    public void sellActives(boolean target, boolean mandatory) {
        // Vender activos)
    }

    public List<Property> getProperties() {
        for (Property property : properties) {
            System.out.println(property);
        }

        return properties;
    }

    public List<Property> transferProperties() {
        // Transferir propiedades
        List<Property> transferredProperties = new ArrayList<>(properties);
        properties.clear();
        return transferredProperties;
    }

    public void setProperties(List<Property> newProperties) {
        this.properties = newProperties;
    }

    public boolean thereAreThingsToSell() {
        // Comprobar si hay cosas que vender
        return false; // Placeholder
    }

    public boolean getBankrupt() {
        return bankrupt; // Placeholder
    }

    public void showResume() {
        // Mostrar resumen
    }

}
