package src;

public class Property extends MonopolyCode {
    private int price;
    private boolean mortgage;
    private int mortgageValue;
    private Player owner;
    private Player player;

    public Property(String description, int id, Terminal terminal, int price, boolean mortgage, int mortgageValue) {
        super(description, id, terminal);
        this.price = price;
        this.mortgage = mortgage;
        this.mortgageValue = mortgageValue;
    }

    public void doOperation() {
    }

    public int getPrice() {
        return price;
    }

    public void getPaymentForRent() {
    };

    public void getOwner() {
        // Placeholder
    }

    public void setOwner() {
        // Placeholder
    }

    public void doOwnerOperation() {
        // Placeholder
    }

    public void showMortageSummary() {
        // Placeholder
    }

}
