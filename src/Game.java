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
        play();
    }

    public void loadDataGame(Game game) {

    }

    public void play() {
        terminal.show("Enter the ID of the card that has been drawn:");
        int idCard = terminal.read();

        terminal.show("Whose turn is it? (1-RED, 2-GREEN, 3-BLUE, 4-BLACK) ");
        int playerTurn = terminal.read();
        Player player = players[playerTurn - 1];

        monopolyCodeArray[idCard].doOperation(player);

        terminal.show("It's " + player.getName() + "'s turn");
        terminal.closeScanner();
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

        play();
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
            terminal.show("Juego guardado exitosamente en: " + filePath);
        } catch (FileNotFoundException fileNotFound) {
            terminal.show("ERROR: No se pudo guardar el juego");
        }
    }

}
