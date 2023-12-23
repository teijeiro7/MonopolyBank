public abstract class Property {
    private int price;
    private boolean mortgage;
    private int mortgageValue;

    public Property(int price, boolean mortgage, int mortgageValue) {
        this.price = price;
        this.mortgage = mortgage;
        this.mortgageValue = mortgageValue;
    }

    public void getPaymentForRent() {
        // Placeholder
    }

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
