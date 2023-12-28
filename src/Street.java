public class Street extends Property{

    int builtHouses;
    int housePrice;
    int[] costStayingWithHouses;

    public Street (String partes[], Terminal terminal){
        super(Integer.parseInt(partes[0]), false, terminal);
        this.builtHouses = 0;
        this.housePrice = 0;
        this.costStayingWithHouses = new int[5];
    }

    public void getPaymentForRent(){


        showPurchaseSummary(builtHouses, null)
    }

    private String showPurchaseSummary (int amount, Player player){
        return "El jugador " + player.getName() + " ha comprado por un total de " + amount + "€";
    }

    private String showPaymentSummary (int amount, Player player){
        return "El jugador " + player.getName() + " ha pagado un total de " + amount + "€";
    }
}
