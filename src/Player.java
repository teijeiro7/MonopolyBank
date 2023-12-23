import java.util.Objects;

public class Player {
    private Color color;
    private String name;
    private int balance;
    private boolean bankrupt;
    private Property[] properties;

    public Player(Color color, String name, int balance, boolean bankrupt, Property[] properties) {
        this.color = color;
        this.name = name;
        this.balance = balance;
        this.bankrupt = bankrupt;
        this.properties = properties;
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

    public Property getProperties() {
        for (int i = 0; i < properties.length; i++) {
            System.out.println(properties[i]);
        }

        return properties;
    }

    public void transferProperties() {
        // Transferir propiedades
    }

    public void setProperty() {
        // Establecer propiedad
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
