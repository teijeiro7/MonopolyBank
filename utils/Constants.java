package utils;

public class Constants {
    public static final int MIN_PLAYERS = 2;
    public static final int MAX_PLAYERS = 4;

    // -------------------------------------------------------GAME--------------------------------------------------------------

    // -------------------------------------------------------play--------------------------------------------------------------
    public static final String enterID = "Enter the ID of the card that has been drawn: ";
    public static final String whoseTurn = "Whose turn is it? (1-RED, 2-GREEN, 3-BLUE, 4-BLACK) ";

    // ----------------------------------------------------createPlayers--------------------------------------------------------
    public static final String numberPlayers = "Enter the number of players for the game: ";
    public static final String numberWarning = "The number of players must be between 2 and 4";

    // ---------------------------------------------------loadMonopolyCode------------------------------------------------------
    public static final String monopolyCodeFile = "config/MonopolyCode.txt";
    public static final String errorReadingFile = "Error reading the file ";

    // -----------------------------------------------------setGameName---------------------------------------------------------
    public static final String enterGameName = "Enter the game name: ";

    // -------------------------------------------------------saveGame----------------------------------------------------------
    public static final String[] menuInterface = { "Main Menu", "----------------", "1: New Game", "2: Load Game",
            "3: Exit", "----------------", };
    public static final String chooseOption = "Choose the option you want: ";
}