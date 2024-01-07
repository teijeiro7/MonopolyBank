package utils;

public class Constants {
        public static final int MIN_PLAYERS = 2;
        public static final int MAX_PLAYERS = 4;
        public static final int MAX_HOUSES = 4;
        public static final int MAX_HOTELS = 1;

        // GAMEMANAGER
        // ----------------------------------------------------------------------------------------------------------

        // -------------------------------------------------------------------------------------------------------------------
        // start
        public static final String[] menuInterface = { "Main Menu", "----------------", "1: New Game", "2: Load Game",
                        "3: Exit", "----------------", };
        public static final String chooseOption = "Choose the option you want: ";

        // GAME
        // --------------------------------------------------------------------------------------------------------------------

        // --------------------------------------------------------------------------------------------------------------------
        // play
        public static final String enterID = "Enter the ID of the card that has been drawn: ";
        public static final String whoseTurn = "Whose turn is it? (1-RED, 2-GREEN, 3-BLUE, 4-BLACK) ";
        public static final String turnSummary = "It's  %s's turn";

        // -----------------------------------------------------------------------------------------------------------
        // createPlayers
        public static final String numberPlayers = "Enter the number of players for the game: ";
        public static final String numberWarning = "The number of players must be between 2 and 4";

        // --------------------------------------------------------------------------------------------------------
        // loadMonopolyCode
        public static final String monopolyCodeFile = "config/MonopolyCode.txt";
        public static final String errorReadingFile = "Error reading the file ";

        // -------------------------------------------------------------------------------------------------------------
        // setGameName
        public static final String enterGameName = "Enter the game name: ";

        // ----------------------------------------------------------------------------------------------------------------
        // saveGame
        public static final String oldGamesPath = "config/oldGames/%s.xml";
        public static final String savedGame = "You are going to save: ";
        public static final String errorSavedGame = "Can not save the game";

        // ---------------------------------------------------------------------------------------------------------
        // askForLanguage
        public static final String selectLanguage = "Select the language you want to play with: ";
        public static final String languagesFolder = "config/languages";
        public static final String noLanguages = "There are not languages available";
        public static final String selectNumberLanguage = "Select the number of the language do you want to play with: ";
        public static final String confirmationLanguage = "You are going to play with the language: ";

        // ----------------------------------------------------------------------------------------------------------------
        // gameMenu
        public static String[] gameMenu = { "Game Menu", "---------------", "1: Make transition",
                        "2: Ask for game status",
                        "3: Leave the game", "---------------", "Choose the option you want: " };
        public static String leavingGame = "Leaving the game...";

        // PLAYER
        // ------------------------------------------------------------------------------------------------------

        // -------------------------------------------------------------------------------------------------------------
        // sellActives
        public static final String showPropertiesToSell = "Your properties are: ";
        public static final String askPropertyToSell = "Which property do you want to sell? (Select with numbers): ";
        public static final String askForMoreProperties = "Do you want to sell another property? (yes/no)";
        public static final String sellSummary = "You have sold  + %s +  for  + %d";
        public static final String askForSelling = "Do you want to sell %s? (Select 0 to cancel and 1 to confirm): ";

        // ---------------------------------------------------------------------------------------------------------
        // pay
        public static final String whatAreYouGoingToPay = "You are going to pay ";

        // STREET
        // ------------------------------------------------------------------------------------------------------

        // --------------------------------------------------------------------------------------------------
        // streetMenu
        public static final String[] optionsStreet = { "STREET MENU", "------------" };
        public static final String[] ownerStreetOptions = { "1: Mortgage", "2: Unmortgage", "3: Buy houses/hotels",
                        "4: Sell houses/hotels", "5: Nothing" };
        public static final String[] buyStreet = { "1: Buy Street", "2: Nothing" };
        public static final String payToOwner = "The player %s has to pay to %s";

        // -----------------------------------------------------------------------------------------mortgageStreet
        public static final String askForMortgage = "Do you want to mortgage the property %s for %d€? (Select 0 to cancel and 1 to confirm): ";
        public static final String canNotMortgage = "You can not mortgage if you have any houses or hotels built. Do you want to sell them? (Select 0 to cancel and 1 to confirm): ";
        public static final String confirmationMortgage = "You are going to mortgage %s for %d euros";

        // -----------------------------------------------------------------------------------------buyHousesHotels
        public static final String askBuyNumberHouses = "How many houses do you want to buy? ";
        public static final String confirmBuyHouses = "Do you want to buy %d houses for %d? (Select 0 to cancel and 1 to confirm): ";
        public static final String maxHousesBuilt = "Cannot buy more than 4 houses, therefore, the only option is to buy a hotel";
        public static final String askForHotel = "Do you want to buy a hotel? (Select 0 to cancel and 1 to confirm): ";
        public static final String showHousesHotelsBuilt = "This property has %d houses and %d hotels built";
        public static final String maxHousesHotelsBuilt = "You can not buy anything more in this property";
        public static final String confirmationBuyHouse = "You have paid %d euros for %d houses";
        public static final String confirmationBuyHotel = "You have paid %d euros for the hotel";
        public static final String errorBuyHouses = "No puedes comprar más de %d casas.";

        // -----------------------------------------------------------------------------------------sellHousesHotels
        public static final String askSellNumberHouses = "How many houses do you want to sell? ";
        public static final String askWhatToSell = "What do you want to sell? (Select 0 for houses and 1 for hotel): ";
        public static final String confirmSellHouses = "Do you want to sell %d houses for %d euros? (Select 0 to cancel and 1 to confirm): ";
        public static final String confirmSellHotel = "Do you want to sell the hotel for %d euros? (Select 0 to cancel and 1 to confirm)";
        public static final String confirmationSellHouse = "You are going to receive %d euros for %d houses";
        public static final String confirmationSellHotel = "You are going to receive %d euros for the hotel";

        // -----------------------------------------------------------------------------------------------buyProperty
        public static final String confirmBuyProperty = "Do you want to buy %s for %d euros? (Select 0 for cancel and 1 to confirm): ";
        public static final String confirmationBuyProperty = "You are going to pay %d euros for %s";
}