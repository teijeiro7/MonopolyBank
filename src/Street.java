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

    public Street() {
    }

    public Street(String[] partes, Terminal terminal) {
        super(partes[2], Integer.parseInt(partes[0]), terminal, (Integer.parseInt(partes[11]) * 2), false,
                Integer.parseInt(partes[11]));
        this.builtHouses = 0;
        this.builtHotels = 0;
        this.housePrice = Integer.parseInt(partes[9]);
        this.costStayingWithHouses = new int[6];
        this.streetName = partes[2];
        this.hotelPrice = Integer.parseInt(partes[10]);
        for (int i = 0; i < 6; i++) {
            this.costStayingWithHouses[i] = Integer.parseInt(partes[i + 3]);
        }
        this.terminal = terminal;
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
            terminal.show(String.format(Constants.payToOwner, player.getName().toUpperCase(),
                    getOwner().getName().toUpperCase()));
            return -1; // return -1 to indicate that the player is not the owner and the property is
                       // not for sale
        }
    }

    public void doOperation(Player player) {
        int option = streetMenu(player);

        if (player.equals(getOwner())) {
            if (option == 1) {
                mortgageStreet(player);
            } else if (option == 2) {
                unmortgageStreet(player);
            } else if (option == 3) {
                buyHousesHotels(player);
            } else if (option == 4) {
                sellHousesHotels(player);
            }
        } else if (getOwner() == null) {
            if (option == 1) {
                buyProperty(player);
            } else if (option == 2) {
                // return to menu
            }
        } else {
            if (builtHotels == 0) {
                player.pay(costStayingWithHouses[builtHouses], true);
                getOwner().receiveMoney(costStayingWithHouses[builtHouses]);
            } else {
                player.pay(costStayingWithHouses[5], true);
                getOwner().receiveMoney(costStayingWithHouses[5]);
            } // assuming calculateRent() calculates the
              // rent that the player should
              // pay

            // tener en cuenta cuanto hay que pagar si tiene un hotel
        }
    }

    public void mortgageStreet(Player player) {
        if (builtHouses == 0 && builtHotels == 0) {
            terminal.show(String.format(Constants.askForMortgage, streetName, getMortgageValue()));
            int confirmMortgage = terminal.read();

            if (confirmMortgage == 1) {
                setMortgaged(true);
                int playerBalance = player.getBalance();
                playerBalance -= getMortgageValue();
                player.setBalance(playerBalance);
                terminal.show(String.format(Constants.confirmationMortgage, getStreetName()));
            }
        } else {
            terminal.show(Constants.canNotMortgage);
            int optionCantMortgage = terminal.read();

            if (optionCantMortgage == 1) {
                sellHousesHotels(player);
                mortgageStreet(player);
            }
        }

    }

    public void unmortgageStreet(Player player) {

    }

    public void buyHousesHotels(Player player) {
        terminal.show(String.format(Constants.showHousesHotelsBuilt, builtHouses, builtHotels));
        if (builtHouses < Constants.MAX_HOUSES) {
            terminal.show(Constants.askBuyNumberHouses);
            int numberHouses = terminal.read();
            if (builtHouses + numberHouses > Constants.MAX_HOUSES) {
                terminal.show(String.format(Constants.errorBuyHouses, Constants.MAX_HOUSES));
                buyHousesHotels(player); // Vuelve a preguntar
            } else {
                int totalHousePrice = housePrice * numberHouses;
                terminal.show(String.format(Constants.confirmBuyHouses, numberHouses, totalHousePrice));
                int cBuyHouse = terminal.read();

                if (cBuyHouse == 1) {
                    int playerBalance = player.getBalance();
                    playerBalance -= totalHousePrice;
                    player.setBalance(playerBalance);
                    builtHouses += numberHouses;
                    terminal.show(String.format(Constants.confirmationBuyHouse, totalHousePrice, numberHouses));
                }
            }
        } else if (builtHouses == Constants.MAX_HOUSES) {
            terminal.show(Constants.maxHousesBuilt);
            terminal.show(Constants.askForHotel);
            int askHotelOption = terminal.read();

            if (askHotelOption == 1) {
                int playerBalance = player.getBalance();
                playerBalance -= hotelPrice;
                builtHotels = 1;
                player.setBalance(playerBalance);
                terminal.show(String.format(Constants.confirmationBuyHotel, hotelPrice));
            }
        } else if (builtHotels == Constants.MAX_HOTELS && builtHouses == Constants.MAX_HOUSES) {
            terminal.show(Constants.maxHousesHotelsBuilt);
        }
    }

    public void sellHousesHotels(Player player) {
        terminal.show(Constants.showHousesHotelsBuilt);

        if (builtHotels == 1) {
            terminal.show(Constants.askWhatToSell);
            int optionToSell = terminal.read();

            if (optionToSell == 0) {
                terminal.show(Constants.askSellNumberHouses);
                int numberHouses = terminal.read();
                int totalSellPrice = (housePrice / 2) * numberHouses;
                terminal.show(String.format(Constants.confirmSellHouses, numberHouses, totalSellPrice));
                int confirmSHouses = terminal.read();

                if (confirmSHouses == 1) {
                    int playerBalance = player.getBalance();
                    playerBalance += totalSellPrice;
                    player.setBalance(playerBalance);
                    builtHouses -= numberHouses;
                    terminal.show(String.format(Constants.confirmationSellHouse, totalSellPrice, numberHouses));
                }

            } else {
                terminal.show(String.format(Constants.confirmSellHotel, hotelPrice));
                int playerBalance = player.getBalance();
                playerBalance += hotelPrice;
                builtHotels = 0;
                player.setBalance(playerBalance);
                terminal.show(String.format(Constants.confirmationSellHotel, hotelPrice));
            }

        } else {
            terminal.show(Constants.askSellNumberHouses);
            int numberHouses = terminal.read();
            int totalSellPrice = (housePrice / 2) * numberHouses;
            terminal.show(String.format(Constants.confirmSellHouses, numberHouses, totalSellPrice));
            int confirmSHouses = terminal.read();

            if (confirmSHouses == 1) {
                int playerBalance = player.getBalance();
                playerBalance += totalSellPrice;
                player.setBalance(playerBalance);
                builtHouses -= numberHouses;
            }
        }

    }

    public void buyProperty(Player player) {
        terminal.show(String.format(Constants.confirmBuyProperty, getStreetName(), getPrice()));
        int confirmProperty = terminal.read();

        if (confirmProperty == 1) {
            player.setProperties(this);
            setOwner(player);
            int playerBalance = player.getBalance();
            playerBalance -= getPrice();
            player.setBalance(playerBalance);
            terminal.show(String.format(Constants.confirmationBuyProperty, getPrice(), streetName));
        } else {

        }
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public void setBuiltHouses(int builtHouses) {
        this.builtHouses = builtHouses;
    }

    public void setBuiltHotels(int builtHotels) {
        this.builtHotels = builtHotels;
    }

    public int getHousePrice() {
        return housePrice;
    }

    public void setHousePrice(int housePrice) {
        this.housePrice = housePrice;
    }

    public int getHotelPrice() {
        return hotelPrice;
    }

    public void setHotelPrice(int hotelPrice) {
        this.hotelPrice = hotelPrice;
    }

    public int[] getCostStayingWithHouses() {
        return costStayingWithHouses;
    }

    public void setCostStayingWithHouses(int[] costStayingWithHouses) {
        this.costStayingWithHouses = costStayingWithHouses;
    }

    public Terminal getTerminal() {
        return terminal;
    }

    public void setTerminal(Terminal terminal) {
        this.terminal = terminal;
    }

}
