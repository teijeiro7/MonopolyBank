public abstract class Property extends MonopolyCode{
    private int price;
    private boolean mortgage;
    private int mortgageValue;
    private Player owner;

    public Property(int price, boolean mortgage, int mortgageValue) {
        super(partes[1], Integer.parseInt(partes[0]));
        this.price = price;
        this.mortgage = mortgage;
        this.mortgageValue = mortgageValue;
    }

    public abstract void getPaymentForRent();

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
