package src;

import java.beans.XMLEncoder;
import java.io.*;
import utils.Constants;

public class Game implements Serializable {
    private MonopolyCode[] monopolyCodeArray;
    private Player[] players;
    private Terminal terminal;

    public Game() {
        this.terminal = new TextTerminal();
        this.monopolyCodeArray = new MonopolyCode[81];
        this.players = new Player[4];
    }

    public void newGame() {
        createPlayers();
        askForLanguage();
        String gameName = setGameName();
        saveGame(gameName);
        play(gameName);
    }

    public void loadDataGame(Game game) {

    }

    public void play(String gameName) {
        terminal.show("Enter the ID of the card that has been drawn:");
        int idCard = terminal.read();

        terminal.show("Whose turn is it? (1-RED, 2-GREEN, 3-BLUE, 4-BLACK) ");
        int playerTurn = terminal.read();
        Player player = players[playerTurn - 1];
        terminal.show("It's " + player.getName() + "'s turn");

        monopolyCodeArray[idCard].doOperation(player);

        terminal.closeScanner();
        saveGame(gameName);
    }

    public void createPlayers() {
        System.out.print("Enter the number of players for the game: ");
        int numPlayers = terminal.read();

        if (numPlayers < 2 || numPlayers > 4) {
            terminal.show("The number of players must be between 2 and 4");
            createPlayers();
        }

        players = new Player[numPlayers];

        for (int i = 0; i < numPlayers; i++) {
            players[i] = new Player(i, terminal);
            terminal.show(players[i].toString());
        }

        terminal.closeScanner();
    }

    public void loadMonopolyCodes() throws IOException {
        String file = "config/MonopolyCode.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                switch (parts[1]) {
                    case "STREET":
                        monopolyCodeArray[Integer.parseInt(parts[0])] = new Street(parts, terminal);
                        break;
                    case "REPAIRS_CARD":
                        monopolyCodeArray[Integer.parseInt(parts[0])] = new RepairsCard(parts, terminal);
                        break;
                    case "TRANSPORT":
                        monopolyCodeArray[Integer.parseInt(parts[0])] = new Transport(parts, terminal);
                        break;
                    case "PAYMENT_CHARGE_CARD":
                        monopolyCodeArray[Integer.parseInt(parts[0])] = new PaymentCharge(parts, terminal);
                        break;
                    case "SERVICE":
                        monopolyCodeArray[Integer.parseInt(parts[0])] = new Service(parts, terminal);
                        break;
                }
            }
        } catch (IOException e) {
            throw new IOException("Error reading the file " + file, e);
        }
    }

    public static void removePlayer(Player player) {
        boolean isBankrupt = player.getBankrupt();
        if (isBankrupt) {
            player = null;
        }
    }

    public int getId(int id) {
        return id;
    }

    public void setMonopolyCode(int id, MonopolyCode monopolyCode) {
        monopolyCodeArray[id] = monopolyCode;
    }

    public String setGameName() {
        terminal.show("Enter the game name");
        String gameName = terminal.readString();
        return gameName;
    }

    public void saveGame(String gameName) {
        try {
            String filePath = "config/oldGames/" + gameName + ".xml";
            XMLEncoder encoder = new XMLEncoder(
                    new BufferedOutputStream(
                            new FileOutputStream(filePath)));
            encoder.writeObject(this);
            encoder.close();
            terminal.show("Game saved successfully at: " + filePath);
        } catch (FileNotFoundException fileNotFound) {
            terminal.show("ERROR: Could not save the game");
        }
    }

    public void askForLanguage() {
        terminal.show("In which language do you want to play?");

        File languagesFolder = new File("config/languages");
        String[] listOfLanguages = languagesFolder.list();

        if (listOfLanguages.length == 0) {
            terminal.show("No languages available");
        }

        for (int i = 0; i < listOfLanguages.length; i++) {
            terminal.show((i + 1) + ": " + listOfLanguages[i]);
        }

        terminal.show("Enter the number of the language you want to play with: ");
        int languageNumber = terminal.read();

        terminal.show("You are going to play with " + listOfLanguages[languageNumber]);
    }

}
