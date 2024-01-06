package utils;

public class Constants {
    public static final int MIN_PLAYERS = 2;
    public static final int MAX_PLAYERS = 4;

    // GAMEMANAGER----------------------------------------------------------------------------------------------------------

    // --------------------------------------------------------------------------------------------------------------------start
    public static final String[] menuInterface = { "Main Menu", "----------------", "1: New Game", "2: Load Game",
            "3: Exit", "----------------", };
    public static final String chooseOption = "Choose the option you want: ";

    // GAME--------------------------------------------------------------------------------------------------------------------

    // ---------------------------------------------------------------------------------------------------------------------play
    public static final String enterID = "Enter the ID of the card that has been drawn: ";
    public static final String whoseTurn = "Whose turn is it? (1-RED, 2-GREEN, 3-BLUE, 4-BLACK) ";
    public static final String turnSummary = "It's  %s's turn";

    // ------------------------------------------------------------------------------------------------------------createPlayers
    public static final String numberPlayers = "Enter the number of players for the game: ";
    public static final String numberWarning = "The number of players must be between 2 and 4";

    // ---------------------------------------------------------------------------------------------------------loadMonopolyCode
    public static final String monopolyCodeFile = "config/MonopolyCode.txt";
    public static final String errorReadingFile = "Error reading the file ";

    // --------------------------------------------------------------------------------------------------------------setGameName
    public static final String enterGameName = "Enter the game name: ";

    // -----------------------------------------------------------------------------------------------------------------saveGame
    public static final String oldGamesPath = "config/oldGames/%s.xml";
    public static final String savedGame = "You are going to save: ";
    public static final String errorSavedGame = "Can not save the game";

    // ----------------------------------------------------------------------------------------------------------askForLanguage
    public static final String selectLanguage = "Select the language you want to play with: ";
    public static final String languagesFolder = "config/languages";
    public static final String noLanguages = "There are not languages available";
    public static final String selectNumberLanguage = "Select the number of the language do you want to play with: ";
    public static final String confirmationLanguage = "You are going to play with the language: ";

    // -----------------------------------------------------------------------------------------------------------------gameMenu
    public static String[] gameMenu = { "Game Menu", "---------------", "1: Make transition", "2: Ask for game status",
            "3: Leave the game", "---------------", "Choose the option you want: " };
    public static String leavingGame = "Leaving the game...";

    // PLAYER-------------------------------------------------------------------------------------------------------------------

    // --------------------------------------------------------------------------------------------------------------sellActives
    public static final String showPropertiesToSell = "Your properties are: ";
    public static final String askPropertyToSell = "Which property do you want to sell? (Select with numbers)";
    public static final String askForMoreProperties = "Do you want to sell another property? (yes/no)";
    public static final String sellSummary = "You have sold  + %s +  for  + %i";
    public static final String askForSelling = "Do you want to sell %s? (Select 0 to cancel and 1 to confirm)";

    // ----------------------------------------------------------------------------------------------------------------------pay
    public static final String whatAreYouGoingToPay = "You are going to pay ";

}