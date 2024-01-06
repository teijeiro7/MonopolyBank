package src;

import utils.Constants;

public class Street extends Property {

    private String streetName;
    private int builtHouses;
    private int builtHotels;
    private int housePrice;
    private int hotelPrice;
    private int[] costStayingWithHouses;
    private Terminal terminal;

    public Street(String[] partes, Terminal terminal) {
        super(partes[2], Integer.parseInt(partes[0]), terminal, Integer.parseInt(partes[3]), false,
                Integer.parseInt(partes[11]));
        this.builtHouses = 0;
        this.builtHotels = 0;
        this.housePrice = Integer.parseInt(partes[9]);
        this.costStayingWithHouses = new int[5];
        this.streetName = partes[1];
        this.hotelPrice = Integer.parseInt(partes[10]);
        for (int i = 0; i < 6; i++) {
            this.costStayingWithHouses[i] = Integer.parseInt(partes[i + 3]);
        }
    }

    public void getPaymentForRent() {

    }

    public String getStreetName() {
        return streetName;
    }

    public int getBuiltHouses() {
        return builtHouses;
    }

    public int getBuiltHotels() {
        return builtHotels;
    }

    public int streetMenu(Player player) {
        for (int i = 0; i < Constants.optionsStreet.length; i++) {
            terminal.show(Constants.optionsStreet[i]);
        }

        if (player.equals(getOwner())) {
            for (int i = 0; i < Constants.ownerStreetOptions.length; i++) {
                terminal.show(Constants.ownerStreetOptions[i]);
            }
            int streetOwnerOption = terminal.read();
            return streetOwnerOption;
        } else if (getOwner() == null) {
            for (int i = 0; i < Constants.buyStreet.length; i++) {
                terminal.show(Constants.buyStreet[i]);
            }
            int streetBuyOption = terminal.read();
            return streetBuyOption;
        } else {
            terminal.show(String.format(Constants.payToOwner, player.toString(), getOwner().toString()));
            return -1; // return -1 to indicate that the player is not the owner and the property is
                       // not for sale
        }
    }

    public void doOperation(Player player) {
        int option = streetMenu(player);

        if (player.equals(getOwner())) {
            if (option == 1) {
                mortgageStreet();
            } else if (option == 2) {
                unmortgageStreet();
            } else if (option == 3) {
                buyHousesHotels();
            } else if (option == 4) {
                sellHousesHotels();
            }
        } else if (getOwner() == null) {
            if (option == 1) {
                buyProperty(player);
            } else if (option == 2) {
                // return to menu
            }
        } else {
            player.pay(getPrice(), true); // assuming calculateRent() calculates the rent that the player should
                                          // pay
        }
    }

    public void mortgageStreet() {
        if (builtHouses == 0 && builtHotels == 0) {
            terminal.show(String.format(Constants.askForMortgage, streetName, getMortgageValue()));
            int confirmMortgage = terminal.read();

            if (confirmMortgage == 1) {
                setMortgaged(true);
                int playerBalance = getPlayer().getBalance();
                playerBalance -= getMortgageValue();
                getPlayer().setBalance(playerBalance);
            }
        } else {
            terminal.show(Constants.canNotMortgage);
            int optionCantMortgage = terminal.read();

            if (optionCantMortgage == 1) {
                sellHousesHotels();
                mortgageStreet();
            }
        }

    }

    public void unmortgageStreet() {

    }

    public void buyHousesHotels() {
        terminal.show(Constants.showHousesHotelsBuilt);
        if (builtHouses < 4) {
            terminal.show(Constants.askBuyNumberHouses);
            int numberHouses = terminal.read();
            int totalHousePrice = housePrice * numberHouses;
            terminal.show(String.format(Constants.confirmBuyHouses, numberHouses, totalHousePrice));
            int playerBalance = getPlayer().getBalance();
            playerBalance -= totalHousePrice;
            getPlayer().setBalance(playerBalance);
            builtHouses += numberHouses;
        } else if (builtHouses == 4) {
            terminal.show(Constants.maxHousesBuilt);
            terminal.show(Constants.askForHotel);
            int askHotelOption = terminal.read();

            if (askHotelOption == 1) {
                int playerBalance = getPlayer().getBalance();
                playerBalance -= hotelPrice;
                builtHotels = 1;
                getPlayer().setBalance(playerBalance);

            }
        } else if (builtHotels == 1 && builtHouses == 4) {
            terminal.show(Constants.maxHousesHotelsBuilt);
        }
    }

    public void sellHousesHotels() {
        terminal.show(Constants.showHousesHotelsBuilt);
        terminal.show(Constants.askSellNumberHouses);
        int numberHouses = terminal.read();
        int totalPrice = (housePrice / 2) * numberHouses;

        if (builtHotels == 1) {
            terminal.show(Constants.askWhatToSell);
            int optionToSell = terminal.read();

            if (optionToSell == 0) {
                terminal.show(Constants.askSellNumberHouses);
                int numberHouses = terminal.read();
                int totalSellPrice = (housePrice / 2) * numberHouses;
                int playerBalance = getPlayer().getBalance();
                playerBalance += totalSellPrice;
                getPlayer().setBalance(playerBalance);
            } else {

            }

        }

    }
}
