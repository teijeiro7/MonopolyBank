package src;

public class Street extends Property {

    private int builtHouses;
    private int builtHotels;
    private int housePrice;
    private int[] costStayingWithHouses;

    public Street(String[] partes, Terminal terminal) {
        super(partes[1], Integer.parseInt(partes[0]), terminal, Integer.parseInt(partes[3]), false,
                Integer.parseInt(partes[11]));
        this.builtHouses = 0;
        this.builtHotels = 0;
        this.housePrice = Integer.parseInt(partes[9]);
        this.costStayingWithHouses = new int[5];
    }

    public void getPaymentForRent() {

    }

    private String showPurchaseSummary(int amount, Player player) {
        return "El jugador " + player.getName() + " ha comprado por un total de " + amount + "€";
    }

    private String showPaymentSummary(int amount, Player player) {
        return "El jugador " + player.getName() + " ha pagado un total de " + amount + "€";
    }

    public int getBuiltHouses() {
        return builtHouses;
    }

    public int getBuiltHotels() {
        return builtHotels;
    }
}
