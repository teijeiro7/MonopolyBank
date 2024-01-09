package src;

public abstract class Property extends MonopolyCode {
    private int price;
    private boolean mortgage;
    private int mortgageValue;
    private Player owner;
    private Player player;

    public Property() {
    }

    public Property(String description, int id, Terminal terminal, int price, boolean mortgage, int mortgageValue) {
        super(description, id, terminal);
        this.price = price;
        this.mortgage = mortgage;
        this.mortgageValue = mortgageValue;
    }

    public abstract void doOperation(Player player);

    public int getPrice() {
        return price;
    }

    public int getMortgageValue() {
        return mortgageValue;
    }

    public Player getOwner() {
        return owner;
    }

    public Player getPlayer() {
        return player;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public void setMortgaged(boolean mortgage) {
        this.mortgage = true;
    }

    public void doOwnerOperation() {
        // Placeholder
    }

    public void showMortageSummary() {
        // Placeholder
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isMortgage() {
        return mortgage;
    }

    public void setMortgage(boolean mortgage) {
        this.mortgage = mortgage;
    }

    public void setMortgageValue(int mortgageValue) {
        this.mortgageValue = mortgageValue;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

}
